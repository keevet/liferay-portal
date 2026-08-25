/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.training.blog.statistics.service;

import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link BlogStatisticLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see BlogStatisticLocalService
 * @generated
 */
public class BlogStatisticLocalServiceWrapper
	implements BlogStatisticLocalService,
			   ServiceWrapper<BlogStatisticLocalService> {

	public BlogStatisticLocalServiceWrapper(
		BlogStatisticLocalService blogStatisticLocalService) {

		_blogStatisticLocalService = blogStatisticLocalService;
	}

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
	@Override
	public com.liferay.training.blog.statistics.model.BlogStatistic
		addBlogStatistic(
			com.liferay.training.blog.statistics.model.BlogStatistic
				blogStatistic) {

		return _blogStatisticLocalService.addBlogStatistic(blogStatistic);
	}

	@Override
	public com.liferay.training.blog.statistics.model.BlogStatistic
		addBlogStatistic(long blogsEntryId, long companyId, long groupId) {

		return _blogStatisticLocalService.addBlogStatistic(
			blogsEntryId, companyId, groupId);
	}

	/**
	 * Creates a new blog statistic with the primary key. Does not add the blog statistic to the database.
	 *
	 * @param blogStatisticId the primary key for the new blog statistic
	 * @return the new blog statistic
	 */
	@Override
	public com.liferay.training.blog.statistics.model.BlogStatistic
		createBlogStatistic(long blogStatisticId) {

		return _blogStatisticLocalService.createBlogStatistic(blogStatisticId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _blogStatisticLocalService.createPersistedModel(primaryKeyObj);
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
	@Override
	public com.liferay.training.blog.statistics.model.BlogStatistic
		deleteBlogStatistic(
			com.liferay.training.blog.statistics.model.BlogStatistic
				blogStatistic) {

		return _blogStatisticLocalService.deleteBlogStatistic(blogStatistic);
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
	@Override
	public com.liferay.training.blog.statistics.model.BlogStatistic
			deleteBlogStatistic(long blogStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _blogStatisticLocalService.deleteBlogStatistic(blogStatisticId);
	}

	@Override
	public com.liferay.training.blog.statistics.model.BlogStatistic
		deleteBlogStatisticByBlogsEntryId(long blogsEntryId) {

		return _blogStatisticLocalService.deleteBlogStatisticByBlogsEntryId(
			blogsEntryId);
	}

	@Override
	public int deleteBlogStatistics(long companyId) {
		return _blogStatisticLocalService.deleteBlogStatistics(companyId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _blogStatisticLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _blogStatisticLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _blogStatisticLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _blogStatisticLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _blogStatisticLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _blogStatisticLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _blogStatisticLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.training.blog.statistics.model.BlogStatistic
		fetchBlogStatistic(long blogStatisticId) {

		return _blogStatisticLocalService.fetchBlogStatistic(blogStatisticId);
	}

	@Override
	public com.liferay.training.blog.statistics.model.BlogStatistic
		fetchBlogStatisticByBlogsEntryId(long blogsEntryId) {

		return _blogStatisticLocalService.fetchBlogStatisticByBlogsEntryId(
			blogsEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _blogStatisticLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the blog statistic with the primary key.
	 *
	 * @param blogStatisticId the primary key of the blog statistic
	 * @return the blog statistic
	 * @throws PortalException if a blog statistic with the primary key could not be found
	 */
	@Override
	public com.liferay.training.blog.statistics.model.BlogStatistic
			getBlogStatistic(long blogStatisticId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _blogStatisticLocalService.getBlogStatistic(blogStatisticId);
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
	@Override
	public java.util.List
		<com.liferay.training.blog.statistics.model.BlogStatistic>
			getBlogStatistics(int start, int end) {

		return _blogStatisticLocalService.getBlogStatistics(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.training.blog.statistics.model.BlogStatistic>
			getBlogStatistics(
				long companyId, Long minViewCount, Long maxViewCount, int start,
				int end, String sortField, boolean ascending) {

		return _blogStatisticLocalService.getBlogStatistics(
			companyId, minViewCount, maxViewCount, start, end, sortField,
			ascending);
	}

	/**
	 * Returns the number of blog statistics.
	 *
	 * @return the number of blog statistics
	 */
	@Override
	public int getBlogStatisticsCount() {
		return _blogStatisticLocalService.getBlogStatisticsCount();
	}

	@Override
	public int getBlogStatisticsCount(
		long companyId, Long minViewCount, Long maxViewCount) {

		return _blogStatisticLocalService.getBlogStatisticsCount(
			companyId, minViewCount, maxViewCount);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _blogStatisticLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _blogStatisticLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _blogStatisticLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public long getViewCount(long blogsEntryId) {
		return _blogStatisticLocalService.getViewCount(blogsEntryId);
	}

	@Override
	public long incrementViewCount(
		long blogsEntryId, long companyId, long groupId) {

		return _blogStatisticLocalService.incrementViewCount(
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
	@Override
	public com.liferay.training.blog.statistics.model.BlogStatistic
		updateBlogStatistic(
			com.liferay.training.blog.statistics.model.BlogStatistic
				blogStatistic) {

		return _blogStatisticLocalService.updateBlogStatistic(blogStatistic);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _blogStatisticLocalService.getBasePersistence();
	}

	@Override
	public BlogStatisticLocalService getWrappedService() {
		return _blogStatisticLocalService;
	}

	@Override
	public void setWrappedService(
		BlogStatisticLocalService blogStatisticLocalService) {

		_blogStatisticLocalService = blogStatisticLocalService;
	}

	private BlogStatisticLocalService _blogStatisticLocalService;

}
// LIFERAY-SERVICE-BUILDER-HASH:300112960