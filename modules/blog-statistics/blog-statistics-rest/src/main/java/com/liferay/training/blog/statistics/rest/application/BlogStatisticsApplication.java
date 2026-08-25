package com.liferay.training.blog.statistics.rest.application;

import com.liferay.training.blog.statistics.service.BlogStatisticLocalService;
import com.liferay.training.blog.statistics.rest.internal.query.StatisticsQuery;
import com.liferay.training.blog.statistics.rest.dto.BlogStatisticDTO;
import com.liferay.training.blog.statistics.rest.dto.Page;
import com.liferay.training.blog.statistics.rest.internal.query.InvalidQueryException;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.QueryParam;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalService;

import javax.ws.rs.NotFoundException;

import javax.ws.rs.POST;

import com.liferay.training.blog.statistics.model.BlogStatistic;

import javax.ws.rs.DELETE;
import javax.ws.rs.core.Response;


/**
 * @author kevin
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/blog-statistics",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=Blog.Statistics.Rest"
	},
	service = Application.class
)
public class BlogStatisticsApplication extends Application {

	@Override
	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Path("/{blogsEntryId}")
	@Produces(MediaType.APPLICATION_JSON)
	public BlogStatisticDTO getViewCount(
		@PathParam("blogsEntryId") long blogsEntryId) {

		return BlogStatisticDTO.of(blogsEntryId,_blogStatisticLocalService.getViewCount(blogsEntryId));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Page<BlogStatisticDTO> getBlogStatistics(
			@DefaultValue("1") @QueryParam("page") int page,
			@DefaultValue("20") @QueryParam("pageSize") int pageSize,
			@QueryParam("sort") String sort,
			@QueryParam("minViewCount") Long minViewCount,
			@QueryParam("maxViewCount") Long maxViewCount)
		throws InvalidQueryException {

		StatisticsQuery query = StatisticsQuery.of(
			page, pageSize, sort, minViewCount, maxViewCount);

		long companyId =
			PermissionThreadLocal.getPermissionChecker().getCompanyId();

		List<BlogStatisticDTO> items = new ArrayList<>();

		for (BlogStatistic blogStatistic :
				_blogStatisticLocalService.getBlogStatistics(
					companyId, query.getMinViewCount(), query.getMaxViewCount(),
					query.getStart(), query.getEnd(), query.getSortField(),
					query.isAscending())) {

			items.add(BlogStatisticDTO.of(blogStatistic));
		}

		return new Page<>(items, query.getPage(), query.getPageSize(),
			_blogStatisticLocalService.getBlogStatisticsCount(
			companyId, query.getMinViewCount(), query.getMaxViewCount()));
	}


	@Reference
	private BlogStatisticLocalService _blogStatisticLocalService;

	@Reference
	private BlogsEntryLocalService _blogsEntryLocalService;


	@POST
	@Path("/{blogsEntryId}/increment")
	@Produces(MediaType.APPLICATION_JSON)
	public BlogStatisticDTO incrementViewCount(
		@PathParam("blogsEntryId") long blogsEntryId) {

		BlogsEntry blogsEntry = _blogsEntryLocalService.fetchBlogsEntry(
			blogsEntryId);

		if (blogsEntry == null) {
			throw new NotFoundException("Nao existe entrada de blog " + blogsEntryId);
		}

		long viewCount = _blogStatisticLocalService.incrementViewCount(
			blogsEntryId, blogsEntry.getCompanyId(), blogsEntry.getGroupId());

		return BlogStatisticDTO.of(blogsEntryId, viewCount);
	}

	@DELETE
	@Path("/{blogsEntryId}")
	public Response deleteBlogStatistic(
		@PathParam("blogsEntryId") long blogsEntryId) {

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin()) {
			throw new ForbiddenException(
				"Esta operacao exige administrador da instancia");
		}

		BlogStatistic blogStatistic =
			_blogStatisticLocalService.deleteBlogStatisticByBlogsEntryId(
				blogsEntryId);

		if (blogStatistic == null) {
			throw new NotFoundException(
				"Nao existe estatistica para a entrada " + blogsEntryId);
		}

		return Response.noContent(
		).build();
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> deleteAllBlogStatistics() {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin()) {
			throw new ForbiddenException(
				"Esta operacao exige administrador da instancia");
		}

		int deleted = _blogStatisticLocalService.deleteBlogStatistics(
			permissionChecker.getCompanyId());

		return Collections.singletonMap("deleted", deleted);
	}
}