package com.liferay.training.blog.statistics.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.training.blog.statistics.model.BlogStatistic;
import com.liferay.training.blog.statistics.model.BlogStatisticField;
import com.liferay.training.blog.statistics.model.impl.BlogStatisticModelImpl;
import com.liferay.training.blog.statistics.service.base.BlogStatisticLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * Contagem de visualizacoes por entrada de blog.
 *
 * @author kevin
 */
@Component(
	property = "model.class.name=com.liferay.training.blog.statistics.model.BlogStatistic",
	service = AopService.class
)
public class BlogStatisticLocalServiceImpl
	extends BlogStatisticLocalServiceBaseImpl {

	public long incrementViewCount(
		long blogsEntryId, long companyId, long groupId) {

		Date now = new Date();

		int rowsUpdated = blogStatisticFinder.incrementViewCount(
			blogsEntryId, 1, now);

		if (rowsUpdated == 0) {

			// Primeira visualizacao desta entrada cria a linha na mesma transaction.

			addBlogStatistic(blogsEntryId, companyId, groupId);

			_clearCacheAfterCommit();

			return 1;
		}

		_clearCacheAfterCommit();

		return blogStatisticFinder.fetchViewCount(blogsEntryId);
	}

	public BlogStatistic addBlogStatistic(
		long blogsEntryId, long companyId, long groupId) {

		BlogStatistic blogStatistic = blogStatisticPersistence.create(
			counterLocalService.increment(BlogStatistic.class.getName()));

		Date now = new Date();

		blogStatistic.setGroupId(groupId);
		blogStatistic.setCompanyId(companyId);
		blogStatistic.setCreateDate(now);
		blogStatistic.setModifiedDate(now);
		blogStatistic.setBlogsEntryId(blogsEntryId);
		blogStatistic.setViewCount(1);

		return blogStatisticPersistence.update(blogStatistic);
	}

	public BlogStatistic fetchBlogStatisticByBlogsEntryId(long blogsEntryId) {
		return blogStatisticPersistence.fetchByBlogsEntryId(blogsEntryId);
	}

	public long getViewCount(long blogsEntryId) {
		BlogStatistic blogStatistic =
			blogStatisticPersistence.fetchByBlogsEntryId(blogsEntryId);

		if (blogStatistic == null) {
			return 0;
		}

		return blogStatistic.getViewCount();
	}

	public List<BlogStatistic> getBlogStatistics(
		long companyId, Long minViewCount, Long maxViewCount, int start,
		int end, String sortField, boolean ascending) {

		OrderByComparator<BlogStatistic> orderByComparator =
			_toOrderByComparator(sortField, ascending);

		if (_isMinOnly(minViewCount, maxViewCount)) {

			return blogStatisticPersistence.findByC_GtViewCount(
				companyId, minViewCount - 1, start, end, orderByComparator);
		}

		return dynamicQuery(
			_buildDynamicQuery(companyId, minViewCount, maxViewCount), start,
			end, orderByComparator);
	}

	public int getBlogStatisticsCount(
		long companyId, Long minViewCount, Long maxViewCount) {

		if (_isMinOnly(minViewCount, maxViewCount)) {
			return blogStatisticPersistence.countByC_GtViewCount(
				companyId, minViewCount - 1);
		}

		return (int)dynamicQueryCount(
			_buildDynamicQuery(companyId, minViewCount, maxViewCount));
	}

	public BlogStatistic deleteBlogStatisticByBlogsEntryId(long blogsEntryId) {
		BlogStatistic blogStatistic =
			blogStatisticPersistence.fetchByBlogsEntryId(blogsEntryId);

		if (blogStatistic == null) {
			return null;
		}

		return blogStatisticPersistence.remove(blogStatistic);
	}

	public int deleteBlogStatistics(long companyId) {
		int count = blogStatisticPersistence.countByCompanyId(companyId);

		blogStatisticPersistence.removeByCompanyId(companyId);

		return count;
	}

	private DynamicQuery _buildDynamicQuery(
		long companyId, Long minViewCount, Long maxViewCount) {

		DynamicQuery dynamicQuery = dynamicQuery();

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyId", companyId));

		if (minViewCount != null) {
			dynamicQuery.add(
				RestrictionsFactoryUtil.ge("viewCount", minViewCount));
		}

		if (maxViewCount != null) {
			dynamicQuery.add(
				RestrictionsFactoryUtil.le("viewCount", maxViewCount));
		}

		return dynamicQuery;
	}

	private void _clearCacheAfterCommit() {
		TransactionCommitCallbackUtil.registerCallback(
			() -> {
				blogStatisticPersistence.clearCache();

				return null;
			});
	}

	private OrderByComparator<BlogStatistic> _toOrderByComparator(
		String sortField, boolean ascending) {

		if (!BlogStatisticField.isSortable(sortField)) {
			throw new IllegalArgumentException(
				"Campo de ordenacao nao permitido: " + sortField);
		}

		return OrderByComparatorFactoryUtil.create(
			BlogStatisticModelImpl.TABLE_NAME, sortField, ascending);
	}

	private boolean _isMinOnly(Long minViewCount, Long maxViewCount) {
		return (minViewCount != null) && (maxViewCount == null);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BlogStatisticLocalServiceImpl.class);

}