/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.training.blog.statistics.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.training.blog.statistics.model.BlogStatistic;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the blog statistic service. This utility wraps <code>com.liferay.training.blog.statistics.service.persistence.impl.BlogStatisticPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BlogStatisticPersistence
 * @generated
 */
public class BlogStatisticUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(BlogStatistic blogStatistic) {
		getPersistence().clearCache(blogStatistic);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, BlogStatistic> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<BlogStatistic> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BlogStatistic> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BlogStatistic> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<BlogStatistic> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static BlogStatistic update(BlogStatistic blogStatistic) {
		return getPersistence().update(blogStatistic);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static BlogStatistic update(
		BlogStatistic blogStatistic, ServiceContext serviceContext) {

		return getPersistence().update(blogStatistic, serviceContext);
	}

	/**
	 * Returns the blog statistic where blogsEntryId = &#63; or throws a <code>NoSuchBlogStatisticException</code> if it could not be found.
	 *
	 * @param blogsEntryId the blogs entry ID
	 * @return the matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	public static BlogStatistic findByBlogsEntryId(long blogsEntryId)
		throws com.liferay.training.blog.statistics.exception.
			NoSuchBlogStatisticException {

		return getPersistence().findByBlogsEntryId(blogsEntryId);
	}

	/**
	 * Returns the blog statistic where blogsEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param blogsEntryId the blogs entry ID
	 * @return the matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	public static BlogStatistic fetchByBlogsEntryId(long blogsEntryId) {
		return getPersistence().fetchByBlogsEntryId(blogsEntryId);
	}

	/**
	 * Returns the blog statistic where blogsEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param blogsEntryId the blogs entry ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	public static BlogStatistic fetchByBlogsEntryId(
		long blogsEntryId, boolean useFinderCache) {

		return getPersistence().fetchByBlogsEntryId(
			blogsEntryId, useFinderCache);
	}

	/**
	 * Removes the blog statistic where blogsEntryId = &#63; from the database.
	 *
	 * @param blogsEntryId the blogs entry ID
	 * @return the blog statistic that was removed
	 */
	public static BlogStatistic removeByBlogsEntryId(long blogsEntryId)
		throws com.liferay.training.blog.statistics.exception.
			NoSuchBlogStatisticException {

		return getPersistence().removeByBlogsEntryId(blogsEntryId);
	}

	/**
	 * Returns the number of blog statistics where blogsEntryId = &#63;.
	 *
	 * @param blogsEntryId the blogs entry ID
	 * @return the number of matching blog statistics
	 */
	public static int countByBlogsEntryId(long blogsEntryId) {
		return getPersistence().countByBlogsEntryId(blogsEntryId);
	}

	/**
	 * Returns all the blog statistics where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching blog statistics
	 */
	public static List<BlogStatistic> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	 * Returns a range of all the blog statistics where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.training.blog.statistics.model.impl.BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @return the range of matching blog statistics
	 */
	public static List<BlogStatistic> findByCompanyId(
		long companyId, int start, int end) {

		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the blog statistics where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.training.blog.statistics.model.impl.BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching blog statistics
	 */
	public static List<BlogStatistic> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<BlogStatistic> orderByComparator) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the blog statistics where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.training.blog.statistics.model.impl.BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching blog statistics
	 */
	public static List<BlogStatistic> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<BlogStatistic> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCompanyId(
			companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first blog statistic in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	public static BlogStatistic findByCompanyId_First(
			long companyId, OrderByComparator<BlogStatistic> orderByComparator)
		throws com.liferay.training.blog.statistics.exception.
			NoSuchBlogStatisticException {

		return getPersistence().findByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the first blog statistic in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	public static BlogStatistic fetchByCompanyId_First(
		long companyId, OrderByComparator<BlogStatistic> orderByComparator) {

		return getPersistence().fetchByCompanyId_First(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last blog statistic in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	public static BlogStatistic findByCompanyId_Last(
			long companyId, OrderByComparator<BlogStatistic> orderByComparator)
		throws com.liferay.training.blog.statistics.exception.
			NoSuchBlogStatisticException {

		return getPersistence().findByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the last blog statistic in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	public static BlogStatistic fetchByCompanyId_Last(
		long companyId, OrderByComparator<BlogStatistic> orderByComparator) {

		return getPersistence().fetchByCompanyId_Last(
			companyId, orderByComparator);
	}

	/**
	 * Returns the blog statistics before and after the current blog statistic in the ordered set where companyId = &#63;.
	 *
	 * @param blogStatisticId the primary key of the current blog statistic
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next blog statistic
	 * @throws NoSuchBlogStatisticException if a blog statistic with the primary key could not be found
	 */
	public static BlogStatistic[] findByCompanyId_PrevAndNext(
			long blogStatisticId, long companyId,
			OrderByComparator<BlogStatistic> orderByComparator)
		throws com.liferay.training.blog.statistics.exception.
			NoSuchBlogStatisticException {

		return getPersistence().findByCompanyId_PrevAndNext(
			blogStatisticId, companyId, orderByComparator);
	}

	/**
	 * Removes all the blog statistics where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	 * Returns the number of blog statistics where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching blog statistics
	 */
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	 * Returns all the blog statistics where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching blog statistics
	 */
	public static List<BlogStatistic> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the blog statistics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.training.blog.statistics.model.impl.BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @return the range of matching blog statistics
	 */
	public static List<BlogStatistic> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the blog statistics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.training.blog.statistics.model.impl.BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching blog statistics
	 */
	public static List<BlogStatistic> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<BlogStatistic> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the blog statistics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.training.blog.statistics.model.impl.BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching blog statistics
	 */
	public static List<BlogStatistic> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<BlogStatistic> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first blog statistic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	public static BlogStatistic findByGroupId_First(
			long groupId, OrderByComparator<BlogStatistic> orderByComparator)
		throws com.liferay.training.blog.statistics.exception.
			NoSuchBlogStatisticException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first blog statistic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	public static BlogStatistic fetchByGroupId_First(
		long groupId, OrderByComparator<BlogStatistic> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last blog statistic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	public static BlogStatistic findByGroupId_Last(
			long groupId, OrderByComparator<BlogStatistic> orderByComparator)
		throws com.liferay.training.blog.statistics.exception.
			NoSuchBlogStatisticException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last blog statistic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	public static BlogStatistic fetchByGroupId_Last(
		long groupId, OrderByComparator<BlogStatistic> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the blog statistics before and after the current blog statistic in the ordered set where groupId = &#63;.
	 *
	 * @param blogStatisticId the primary key of the current blog statistic
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next blog statistic
	 * @throws NoSuchBlogStatisticException if a blog statistic with the primary key could not be found
	 */
	public static BlogStatistic[] findByGroupId_PrevAndNext(
			long blogStatisticId, long groupId,
			OrderByComparator<BlogStatistic> orderByComparator)
		throws com.liferay.training.blog.statistics.exception.
			NoSuchBlogStatisticException {

		return getPersistence().findByGroupId_PrevAndNext(
			blogStatisticId, groupId, orderByComparator);
	}

	/**
	 * Removes all the blog statistics where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of blog statistics where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching blog statistics
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns all the blog statistics where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @return the matching blog statistics
	 */
	public static List<BlogStatistic> findByC_GtViewCount(
		long companyId, long viewCount) {

		return getPersistence().findByC_GtViewCount(companyId, viewCount);
	}

	/**
	 * Returns a range of all the blog statistics where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.training.blog.statistics.model.impl.BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @return the range of matching blog statistics
	 */
	public static List<BlogStatistic> findByC_GtViewCount(
		long companyId, long viewCount, int start, int end) {

		return getPersistence().findByC_GtViewCount(
			companyId, viewCount, start, end);
	}

	/**
	 * Returns an ordered range of all the blog statistics where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.training.blog.statistics.model.impl.BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching blog statistics
	 */
	public static List<BlogStatistic> findByC_GtViewCount(
		long companyId, long viewCount, int start, int end,
		OrderByComparator<BlogStatistic> orderByComparator) {

		return getPersistence().findByC_GtViewCount(
			companyId, viewCount, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the blog statistics where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.training.blog.statistics.model.impl.BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching blog statistics
	 */
	public static List<BlogStatistic> findByC_GtViewCount(
		long companyId, long viewCount, int start, int end,
		OrderByComparator<BlogStatistic> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_GtViewCount(
			companyId, viewCount, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first blog statistic in the ordered set where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	public static BlogStatistic findByC_GtViewCount_First(
			long companyId, long viewCount,
			OrderByComparator<BlogStatistic> orderByComparator)
		throws com.liferay.training.blog.statistics.exception.
			NoSuchBlogStatisticException {

		return getPersistence().findByC_GtViewCount_First(
			companyId, viewCount, orderByComparator);
	}

	/**
	 * Returns the first blog statistic in the ordered set where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	public static BlogStatistic fetchByC_GtViewCount_First(
		long companyId, long viewCount,
		OrderByComparator<BlogStatistic> orderByComparator) {

		return getPersistence().fetchByC_GtViewCount_First(
			companyId, viewCount, orderByComparator);
	}

	/**
	 * Returns the last blog statistic in the ordered set where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	public static BlogStatistic findByC_GtViewCount_Last(
			long companyId, long viewCount,
			OrderByComparator<BlogStatistic> orderByComparator)
		throws com.liferay.training.blog.statistics.exception.
			NoSuchBlogStatisticException {

		return getPersistence().findByC_GtViewCount_Last(
			companyId, viewCount, orderByComparator);
	}

	/**
	 * Returns the last blog statistic in the ordered set where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	public static BlogStatistic fetchByC_GtViewCount_Last(
		long companyId, long viewCount,
		OrderByComparator<BlogStatistic> orderByComparator) {

		return getPersistence().fetchByC_GtViewCount_Last(
			companyId, viewCount, orderByComparator);
	}

	/**
	 * Returns the blog statistics before and after the current blog statistic in the ordered set where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * @param blogStatisticId the primary key of the current blog statistic
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next blog statistic
	 * @throws NoSuchBlogStatisticException if a blog statistic with the primary key could not be found
	 */
	public static BlogStatistic[] findByC_GtViewCount_PrevAndNext(
			long blogStatisticId, long companyId, long viewCount,
			OrderByComparator<BlogStatistic> orderByComparator)
		throws com.liferay.training.blog.statistics.exception.
			NoSuchBlogStatisticException {

		return getPersistence().findByC_GtViewCount_PrevAndNext(
			blogStatisticId, companyId, viewCount, orderByComparator);
	}

	/**
	 * Removes all the blog statistics where companyId = &#63; and viewCount &gt; &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 */
	public static void removeByC_GtViewCount(long companyId, long viewCount) {
		getPersistence().removeByC_GtViewCount(companyId, viewCount);
	}

	/**
	 * Returns the number of blog statistics where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @return the number of matching blog statistics
	 */
	public static int countByC_GtViewCount(long companyId, long viewCount) {
		return getPersistence().countByC_GtViewCount(companyId, viewCount);
	}

	/**
	 * Caches the blog statistic in the entity cache if it is enabled.
	 *
	 * @param blogStatistic the blog statistic
	 */
	public static void cacheResult(BlogStatistic blogStatistic) {
		getPersistence().cacheResult(blogStatistic);
	}

	/**
	 * Caches the blog statistics in the entity cache if it is enabled.
	 *
	 * @param blogStatistics the blog statistics
	 */
	public static void cacheResult(List<BlogStatistic> blogStatistics) {
		getPersistence().cacheResult(blogStatistics);
	}

	/**
	 * Creates a new blog statistic with the primary key. Does not add the blog statistic to the database.
	 *
	 * @param blogStatisticId the primary key for the new blog statistic
	 * @return the new blog statistic
	 */
	public static BlogStatistic create(long blogStatisticId) {
		return getPersistence().create(blogStatisticId);
	}

	/**
	 * Removes the blog statistic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param blogStatisticId the primary key of the blog statistic
	 * @return the blog statistic that was removed
	 * @throws NoSuchBlogStatisticException if a blog statistic with the primary key could not be found
	 */
	public static BlogStatistic remove(long blogStatisticId)
		throws com.liferay.training.blog.statistics.exception.
			NoSuchBlogStatisticException {

		return getPersistence().remove(blogStatisticId);
	}

	public static BlogStatistic updateImpl(BlogStatistic blogStatistic) {
		return getPersistence().updateImpl(blogStatistic);
	}

	/**
	 * Returns the blog statistic with the primary key or throws a <code>NoSuchBlogStatisticException</code> if it could not be found.
	 *
	 * @param blogStatisticId the primary key of the blog statistic
	 * @return the blog statistic
	 * @throws NoSuchBlogStatisticException if a blog statistic with the primary key could not be found
	 */
	public static BlogStatistic findByPrimaryKey(long blogStatisticId)
		throws com.liferay.training.blog.statistics.exception.
			NoSuchBlogStatisticException {

		return getPersistence().findByPrimaryKey(blogStatisticId);
	}

	/**
	 * Returns the blog statistic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param blogStatisticId the primary key of the blog statistic
	 * @return the blog statistic, or <code>null</code> if a blog statistic with the primary key could not be found
	 */
	public static BlogStatistic fetchByPrimaryKey(long blogStatisticId) {
		return getPersistence().fetchByPrimaryKey(blogStatisticId);
	}

	/**
	 * Returns all the blog statistics.
	 *
	 * @return the blog statistics
	 */
	public static List<BlogStatistic> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the blog statistics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.training.blog.statistics.model.impl.BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @return the range of blog statistics
	 */
	public static List<BlogStatistic> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the blog statistics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.training.blog.statistics.model.impl.BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of blog statistics
	 */
	public static List<BlogStatistic> findAll(
		int start, int end,
		OrderByComparator<BlogStatistic> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the blog statistics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.training.blog.statistics.model.impl.BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of blog statistics
	 */
	public static List<BlogStatistic> findAll(
		int start, int end, OrderByComparator<BlogStatistic> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the blog statistics from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of blog statistics.
	 *
	 * @return the number of blog statistics
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static BlogStatisticPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(BlogStatisticPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile BlogStatisticPersistence _persistence;

}
// LIFERAY-SERVICE-BUILDER-HASH:801647699