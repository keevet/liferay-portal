/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.training.blog.statistics.service;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.training.blog.statistics.model.BlogStatistic;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for BlogStatistic. This utility wraps
 * <code>com.liferay.training.blog.statistics.service.impl.BlogStatisticLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see BlogStatisticLocalService
 * @generated
 */
public class BlogStatisticLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.training.blog.statistics.service.impl.BlogStatisticLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the blog statistic to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BlogStatisticLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param blogStatistic the blog statistic
	 * @return the blog statistic that was added
	 */
	public static BlogStatistic addBlogStatistic(BlogStatistic blogStatistic) {
		return getService().addBlogStatistic(blogStatistic);
	}

	public static BlogStatistic addBlogStatistic(
		long blogsEntryId, long companyId, long groupId) {

		return getService().addBlogStatistic(blogsEntryId, companyId, groupId);
	}

	/**
	 * Creates a new blog statistic with the primary key. Does not add the blog statistic to the database.
	 *
	 * @param blogStatisticId the primary key for the new blog statistic
	 * @return the new blog statistic
	 */
	public static BlogStatistic createBlogStatistic(long blogStatisticId) {
		return getService().createBlogStatistic(blogStatisticId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the blog statistic from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BlogStatisticLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param blogStatistic the blog statistic
	 * @return the blog statistic that was removed
	 */
	public static BlogStatistic deleteBlogStatistic(
		BlogStatistic blogStatistic) {

		return getService().deleteBlogStatistic(blogStatistic);
	}

	/**
	 * Deletes the blog statistic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BlogStatisticLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param blogStatisticId the primary key of the blog statistic
	 * @return the blog statistic that was removed
	 * @throws PortalException if a blog statistic with the primary key could not be found
	 */
	public static BlogStatistic deleteBlogStatistic(long blogStatisticId)
		throws PortalException {

		return getService().deleteBlogStatistic(blogStatisticId);
	}

	public static BlogStatistic deleteBlogStatisticByBlogsEntryId(
		long blogsEntryId) {

		return getService().deleteBlogStatisticByBlogsEntryId(blogsEntryId);
	}

	public static int deleteBlogStatistics(long companyId) {
		return getService().deleteBlogStatistics(companyId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.training.blog.statistics.model.impl.BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.training.blog.statistics.model.impl.BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static BlogStatistic fetchBlogStatistic(long blogStatisticId) {
		return getService().fetchBlogStatistic(blogStatisticId);
	}

	public static BlogStatistic fetchBlogStatisticByBlogsEntryId(
		long blogsEntryId) {

		return getService().fetchBlogStatisticByBlogsEntryId(blogsEntryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the blog statistic with the primary key.
	 *
	 * @param blogStatisticId the primary key of the blog statistic
	 * @return the blog statistic
	 * @throws PortalException if a blog statistic with the primary key could not be found
	 */
	public static BlogStatistic getBlogStatistic(long blogStatisticId)
		throws PortalException {

		return getService().getBlogStatistic(blogStatisticId);
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
	public static List<BlogStatistic> getBlogStatistics(int start, int end) {
		return getService().getBlogStatistics(start, end);
	}

	public static List<BlogStatistic> getBlogStatistics(
		long companyId, Long minViewCount, Long maxViewCount, int start,
		int end, String sortField, boolean ascending) {

		return getService().getBlogStatistics(
			companyId, minViewCount, maxViewCount, start, end, sortField,
			ascending);
	}

	/**
	 * Returns the number of blog statistics.
	 *
	 * @return the number of blog statistics
	 */
	public static int getBlogStatisticsCount() {
		return getService().getBlogStatisticsCount();
	}

	public static int getBlogStatisticsCount(
		long companyId, Long minViewCount, Long maxViewCount) {

		return getService().getBlogStatisticsCount(
			companyId, minViewCount, maxViewCount);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static long getViewCount(long blogsEntryId) {
		return getService().getViewCount(blogsEntryId);
	}

	public static long incrementViewCount(
		long blogsEntryId, long companyId, long groupId) {

		return getService().incrementViewCount(
			blogsEntryId, companyId, groupId);
	}

	/**
	 * Updates the blog statistic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect BlogStatisticLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param blogStatistic the blog statistic
	 * @return the blog statistic that was updated
	 */
	public static BlogStatistic updateBlogStatistic(
		BlogStatistic blogStatistic) {

		return getService().updateBlogStatistic(blogStatistic);
	}

	public static BlogStatisticLocalService getService() {
		return _service;
	}

	public static void setService(BlogStatisticLocalService service) {
		_service = service;
	}

	private static volatile BlogStatisticLocalService _service;

}
// LIFERAY-SERVICE-BUILDER-HASH:1966274031