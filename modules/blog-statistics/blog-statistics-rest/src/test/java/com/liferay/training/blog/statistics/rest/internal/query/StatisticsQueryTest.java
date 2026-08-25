package com.liferay.training.blog.statistics.rest.internal.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class StatisticsQueryTest {

	@Test
	public void testDefaultSortIsMostViewedFirst() throws Exception {
		StatisticsQuery query = StatisticsQuery.of(1, 20, null, null, null);

		assertEquals("viewCount", query.getSortField());
		assertFalse(query.isAscending());
	}

	@Test
	public void testOmittedDirectionMeansDescending() throws Exception {
		StatisticsQuery query = StatisticsQuery.of(
			1, 20, "blogsEntryId", null, null);

		assertEquals("blogsEntryId", query.getSortField());
		assertFalse(query.isAscending());
	}

	@Test
	public void testSortDirectionIsCaseInsensitive() throws Exception {
		assertTrue(
			StatisticsQuery.of(1, 20, "createDate:ASC", null, null
			).isAscending());
	}

	@Test
	public void testUnknownSortFieldIsRejected() {
		assertRejected(1, 20, "totallyMadeUpField", null, null);
	}

	@Test
	public void testSortWithSqlIsRejected() {
		assertRejected(1, 20, "viewCount; DROP TABLE users", null, null);
	}

	@Test
	public void testOversizedPageSizeIsClamped() throws Exception {
		assertEquals(
			StatisticsQuery.MAX_PAGE_SIZE,
			StatisticsQuery.of(1, 5000, null, null, null).getPageSize());
	}

	@Test
	public void testNonPositivePageSizeFallsBackToDefault() throws Exception {
		assertEquals(
			StatisticsQuery.DEFAULT_PAGE_SIZE,
			StatisticsQuery.of(1, 0, null, null, null).getPageSize());
		assertEquals(
			StatisticsQuery.DEFAULT_PAGE_SIZE,
			StatisticsQuery.of(1, -3, null, null, null).getPageSize());
	}

	@Test
	public void testNonPositivePageFallsBackToFirst() throws Exception {
		assertEquals(1, StatisticsQuery.of(0, 20, null, null, null).getPage());
		assertEquals(1, StatisticsQuery.of(-7, 20, null, null, null).getPage());
	}

	@Test
	public void testPaginationTranslatesToServiceBuilderIndexes()
		throws Exception {

		StatisticsQuery query = StatisticsQuery.of(3, 10, null, null, null);

		assertEquals(20, query.getStart());
		assertEquals(30, query.getEnd());
	}

	@Test
	public void testMinGreaterThanMaxIsRejected() {
		assertRejected(1, 20, null, 100L, 10L);
	}

	@Test
	public void testEqualMinAndMaxIsAValidRange() throws Exception {
		StatisticsQuery query = StatisticsQuery.of(1, 20, null, 10L, 10L);

		assertEquals(Long.valueOf(10), query.getMinViewCount());
		assertEquals(Long.valueOf(10), query.getMaxViewCount());
	}

	@Test
	public void testUnboundedMaxIsAllowed() throws Exception {
		StatisticsQuery query = StatisticsQuery.of(1, 20, null, 100L, null);

		assertEquals(Long.valueOf(100), query.getMinViewCount());
		assertNull(query.getMaxViewCount());
	}

	private void assertRejected(
		int page, int pageSize, String sort, Long minViewCount,
		Long maxViewCount) {

		try {
			StatisticsQuery.of(page, pageSize, sort, minViewCount, maxViewCount);

			fail(
				"Deveria ter recusado sort=" + sort + " min=" + minViewCount +
					" max=" + maxViewCount);
		}
		catch (InvalidQueryException invalidQueryException) {
			assertTrue(
				"A excecao precisa explicar o que esta errado",
				(invalidQueryException.getMessage() != null) &&
					!invalidQueryException.getMessage().trim().isEmpty());
		}
	}

}
