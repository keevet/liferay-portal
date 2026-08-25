# Blog Statistics

Extensão do Liferay Portal 7.3 que registra e expõe a contagem de visualizações das entradas de blog.

- API REST em /o/blog-statistics/, implementada com JAX-RS
- Persistência via Service Builder
- Incremento automático a cada render de uma entrada de blog
- Ordenação, paginação e filtro por faixa de visualizações


## Módulos

Para optar em dividir em -rest e -web, pensei no conjuntos de dependências diferentes baseado em suas responsabilidades, (JAX-RS de um lado, javax.portlet do outro) e redeploy independente.

Então ficou assim:

modules/blog-statistics/
  blog-statistics-api/      interfaces e modelo (gerado pelo Service Builder)
  blog-statistics-service/  service.xml, LocalServiceImpl, FinderImpl
  blog-statistics-rest/     aplicação JAX-RS e endpoints HTTP
  blog-statistics-web/      RenderFilter que conta as visualizações


## Ambiente

Liferay Portal   CE 7.3 GA8 (portal-7.3-ga8)
Tomcat           9.0.43 (do bundle)
JDK              11 (Temurin 11.0.32)
Gradle           8.9 (wrapper do workspace)
Blade CLI        8.0.2

## A API e Auth

Todos os endpoints ficam sob /o/blog-statistics/. Requisições em /o/ já chegam com o PermissionChecker preenchido pelos auth verifiers do portal, então Basic auth funciona sem configuração adicional, e nesse ponto não precisei mexer.

  GET     /                          lista as contagens
  GET     /{blogsEntryId}            contagem de uma entrada
  POST    /{blogsEntryId}/increment  soma uma visualização
  DELETE  /{blogsEntryId}            apaga a estatística da entrada
  DELETE  /                          apaga todas as estatísticas

### Parâmetros da listagem

  page          default 1     base 1; valor inválido cai na primeira
  pageSize      default 20    acima de 200 é reduzido a 200
  sort          viewCount:desc   campo ou campo:asc|desc
  minViewCount  —             limite inferior
  maxViewCount  —             limite superior

Campos ordenáveis: viewCount, blogsEntryId, createDate, modifiedDate.

### Exemplos

  AUTH='test@liferay.com:test'
  BASE='http://localhost:8080/o/blog-statistics'

  curl -u "$AUTH" "$BASE/?pageSize=10&sort=viewCount:desc"
  curl -u "$AUTH" "$BASE/?minViewCount=100"
  curl -u "$AUTH" "$BASE/42"
  curl -X POST -u "$AUTH" "$BASE/42/increment"
  curl -X DELETE -u "$AUTH" "$BASE/42"

Resposta da listagem:

  {
    "items": [{"blogsEntryId": 42, "viewCount": 1337}],
    "page": 1,
    "pageSize": 20,
    "totalCount": 1,
    "totalPages": 1,
    "hasNext": false
  }


## Decisões

### JAX-RS pelo template rest

Conforme o enunciado pediu explicitamente o REST Template.

### Serialização JSON: nada a configurar

O portal 7.3 já publica a extensão Apache Aries JAX-RS Jackson com

  osgi.jaxrs.application.select="(!(liferay.jackson=false))"

### DTOs em vez do modelo do Service Builder

Decidi fazer via DTO, para evitar que mudanças em campos diretamente no service.xml do service builder
quebrassem o que já tava funcionando.

E usei um a classe Page que expõe totalPages e hasNext como campos derivados — o
Jackson os serializa a partir dos getters, e cada cliente deixa de
repetir a aritmética.

### Incremento atômico

Um dos problemas que eu enfrentei pela solução gerada pelo Service Builder foi por que ele oferece um read-modify-write:

  BlogStatistic stat = persistence.fetchByBlogsEntryId(id);  // -> 42
  stat.setViewCount(stat.getViewCount() + 1);
  persistence.update(stat);                                  // SET 43

No isolamento READ_COMMITTED, default do Liferay, duas visualizações simultâneas leem 42, ambas gravam 43 e uma desaparece, então pra esse caso de um contador de views ele se perde. 

Resolvi com um SQL Nativo:

  UPDATE BlogStats_BlogStatistic
     SET viewCount = viewCount + ?, modifiedDate = ?
   WHERE blogsEntryId = ?

A linha é travada pela duração do statement e o incremento parte do
valor corrente, não de um valor lido antes.

### Finder pra métodos

create unique index IX_191A0402 on BlogStats_BlogStatistic (blogsEntryId);
create index IX_D46EF1A4 on BlogStats_BlogStatistic (companyId, viewCount);
create index IX_D034E738 on BlogStats_BlogStatistic (groupId);

No Service Builder eu declarei meus métodos como <finder> e o índice já veio junto, o que acabou transformando a listagem ordenada em index scan em vez de sort em memória.


### Exceção própria e ExceptionMapper

A validação lançava IllegalArgumentException, e o resultado observado
era HTTP 200 com corpo vazio — o AuthVerifierFilter engolia a exceção
antes de ela virar resposta de erro.

A correção que implementei foi um tipo dedicado (InvalidQueryException) e um
ExceptionMapper registrado como extensão JAX-RS.

### Endpoints

GET     /                          lista as contagens da instância
GET     /{blogsEntryId}            contagem de uma entrada
POST    /{blogsEntryId}/increment  soma uma visualização
DELETE  /{blogsEntryId}            apaga a estatística de uma entrada
DELETE  /                          apaga todas as estatísticas


## Testes

### Unitários — ./gradlew test

12 testes sobre a StatisticsQuery, que concentra a leitura e validação
dos parâmetros da listagem. Rodam sem portal: a classe foi mantida livre
de estática do Liferay para permitir isso.

Cobrem:

- pageSize acima do teto é reduzido a 200; zero ou negativo cai no default
- page zero ou negativa cai na primeira
- sort desconhecido é recusado com 400, inclusive tentativa de SQL no ORDER BY
- direção de ordenação omitida assume desc; asc/ASC é aceito
- faixa min/max invertida é recusada
- page e pageSize são convertidos nos índices start/end do Service Builder

## Performance da listagem

Sobre a pergunta de lentidão do enunciado. O que
fazer, do lado da API e do banco:

### Do lado da API

Paginação obrigatória, nunca lista ilimitada. Já implementado, com teto
de 200, evitando de haver um carregamento gigante de uma lista.

Trocar OFFSET por paginação por cursor (keyset). start/end do Service
Builder viram LIMIT ... OFFSET ..., e o custo do OFFSET cresce
linearmente: a página 5.000 obriga o banco a percorrer e descartar
100.000 linhas.

Cache para as consultas quentes. "Top N mais vistos" é a consulta mais
provável e a que melhor tolera estar alguns segundos desatualizada. Um
PortalCache com TTL de 30–60 s absorve praticamente toda a carga de
leitura. No HTTP, ETag e Cache-Control evitam até serializar a resposta.

Não buscar o BlogsEntry de cada linha. O DTO expõe só o blogsEntryId
por isso. Enriquecer a listagem com título ou autor por linha seria um
N+1: 20 itens, 21 consultas. Se o título for necessário, as saídas são
desnormalizar (guardar o título na própria linha, atualizado por
ModelListener) ou fazer uma consulta em lote pelos ids.

Desacoplar escrita de leitura: incremento assíncrono. Cada render gera
hoje um UPDATE síncrono. Agregando em memória e fazendo flush em lote,
mil visualizações viram um UPDATE. O Liferay oferece isso como anotação:
@BufferedIncrement(incrementClass = NumberIncrement.class), que é o que
o ViewCountEntryLocalService nativo usa.

### Do lado do banco

Índice composto na direção da consulta. (companyId, viewCount) permite
que a listagem ordenada por viewCount seja um index scan. Sem ele, o
banco lê a tabela toda e ordena em memória — provavelmente a explicação
para a lentidão descrita na pergunta.

Covering index. Se o índice contiver todas as colunas retornadas, a
consulta é respondida só pelo índice, sem tocar na tabela. Elimina a
busca aleatória por linha, que costuma dominar o tempo de uma listagem
ordenada.

Também daria pra pensar em mover o contador para fora do banco relacional. Um INCR no Redis
é ordens de magnitude mais barato que um UPDATE transacional, com flush
periódico. Faz sentido quando o volume de visualizações passa a dominar
a carga de escrita.
