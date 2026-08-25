/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.training.blog.statistics.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.training.blog.statistics.exception.NoSuchBlogStatisticException;
import com.liferay.training.blog.statistics.model.BlogStatistic;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the blog statistic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BlogStatisticUtil
 * @generated
 */
@ProviderType
public interface BlogStatisticPersistence
	extends BasePersistence<BlogStatistic> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BlogStatisticUtil} to access the blog statistic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the blog statistic where blogsEntryId = &#63; or throws a <code>NoSuchBlogStatisticException</code> if it could not be found.
	 *
	 * @param blogsEntryId the blogs entry ID
	 * @return the matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	public BlogStatistic findByBlogsEntryId(long blogsEntryId)
		throws NoSuchBlogStatisticException;

	/**
	 * Returns the blog statistic where blogsEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param blogsEntryId the blogs entry ID
	 * @return the matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	public BlogStatistic fetchByBlogsEntryId(long blogsEntryId);

	/**
	 * Returns the blog statistic where blogsEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param blogsEntryId the blogs entry ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	public BlogStatistic fetchByBlogsEntryId(
		long blogsEntryId, boolean useFinderCache);

	/**
	 * Removes the blog statistic where blogsEntryId = &#63; from the database.
	 *
	 * @param blogsEntryId the blogs entry ID
	 * @return the blog statistic that was removed
	 */
	public BlogStatistic removeByBlogsEntryId(long blogsEntryId)
		throws NoSuchBlogStatisticException;

	/**
	 * Returns the number of blog statistics where blogsEntryId = &#63;.
	 *
	 * @param blogsEntryId the blogs entry ID
	 * @return the number of matching blog statistics
	 */
	public int countByBlogsEntryId(long blogsEntryId);

	/**
	 * Returns all the blog statistics where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching blog statistics
	 */
	public java.util.List<BlogStatistic> findByCompanyId(long companyId);

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
	public java.util.List<BlogStatistic> findByCompanyId(
		long companyId, int start, int end);

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
	public java.util.List<BlogStatistic> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
			orderByComparator);

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
	public java.util.List<BlogStatistic> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first blog statistic in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	public BlogStatistic findByCompanyId_First(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
				orderByComparator)
		throws NoSuchBlogStatisticException;

	/**
	 * Returns the first blog statistic in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	public BlogStatistic fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
			orderByComparator);

	/**
	 * Returns the last blog statistic in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	public BlogStatistic findByCompanyId_Last(
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
				orderByComparator)
		throws NoSuchBlogStatisticException;

	/**
	 * Returns the last blog statistic in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	public BlogStatistic fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
			orderByComparator);

	/**
	 * Returns the blog statistics before and after the current blog statistic in the ordered set where companyId = &#63;.
	 *
	 * @param blogStatisticId the primary key of the current blog statistic
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next blog statistic
	 * @throws NoSuchBlogStatisticException if a blog statistic with the primary key could not be found
	 */
	public BlogStatistic[] findByCompanyId_PrevAndNext(
			long blogStatisticId, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
				orderByComparator)
		throws NoSuchBlogStatisticException;

	/**
	 * Removes all the blog statistics where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	public void removeByCompanyId(long companyId);

	/**
	 * Returns the number of blog statistics where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching blog statistics
	 */
	public int countByCompanyId(long companyId);

	/**
	 * Returns all the blog statistics where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching blog statistics
	 */
	public java.util.List<BlogStatistic> findByGroupId(long groupId);

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
	public java.util.List<BlogStatistic> findByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<BlogStatistic> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
			orderByComparator);

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
	public java.util.List<BlogStatistic> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first blog statistic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	public BlogStatistic findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
				orderByComparator)
		throws NoSuchBlogStatisticException;

	/**
	 * Returns the first blog statistic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	public BlogStatistic fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
			orderByComparator);

	/**
	 * Returns the last blog statistic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	public BlogStatistic findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
				orderByComparator)
		throws NoSuchBlogStatisticException;

	/**
	 * Returns the last blog statistic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	public BlogStatistic fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
			orderByComparator);

	/**
	 * Returns the blog statistics before and after the current blog statistic in the ordered set where groupId = &#63;.
	 *
	 * @param blogStatisticId the primary key of the current blog statistic
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next blog statistic
	 * @throws NoSuchBlogStatisticException if a blog statistic with the primary key could not be found
	 */
	public BlogStatistic[] findByGroupId_PrevAndNext(
			long blogStatisticId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
				orderByComparator)
		throws NoSuchBlogStatisticException;

	/**
	 * Removes all the blog statistics where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of blog statistics where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching blog statistics
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns all the blog statistics where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @return the matching blog statistics
	 */
	public java.util.List<BlogStatistic> findByC_GtViewCount(
		long companyId, long viewCount);

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
	public java.util.List<BlogStatistic> findByC_GtViewCount(
		long companyId, long viewCount, int start, int end);

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
	public java.util.List<BlogStatistic> findByC_GtViewCount(
		long companyId, long viewCount, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
			orderByComparator);

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
	public java.util.List<BlogStatistic> findByC_GtViewCount(
		long companyId, long viewCount, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first blog statistic in the ordered set where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	public BlogStatistic findByC_GtViewCount_First(
			long companyId, long viewCount,
			com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
				orderByComparator)
		throws NoSuchBlogStatisticException;

	/**
	 * Returns the first blog statistic in the ordered set where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	public BlogStatistic fetchByC_GtViewCount_First(
		long companyId, long viewCount,
		com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
			orderByComparator);

	/**
	 * Returns the last blog statistic in the ordered set where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	public BlogStatistic findByC_GtViewCount_Last(
			long companyId, long viewCount,
			com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
				orderByComparator)
		throws NoSuchBlogStatisticException;

	/**
	 * Returns the last blog statistic in the ordered set where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	public BlogStatistic fetchByC_GtViewCount_Last(
		long companyId, long viewCount,
		com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
			orderByComparator);

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
	public BlogStatistic[] findByC_GtViewCount_PrevAndNext(
			long blogStatisticId, long companyId, long viewCount,
			com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
				orderByComparator)
		throws NoSuchBlogStatisticException;

	/**
	 * Removes all the blog statistics where companyId = &#63; and viewCount &gt; &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 */
	public void removeByC_GtViewCount(long companyId, long viewCount);

	/**
	 * Returns the number of blog statistics where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @return the number of matching blog statistics
	 */
	public int countByC_GtViewCount(long companyId, long viewCount);

	/**
	 * Caches the blog statistic in the entity cache if it is enabled.
	 *
	 * @param blogStatistic the blog statistic
	 */
	public void cacheResult(BlogStatistic blogStatistic);

	/**
	 * Caches the blog statistics in the entity cache if it is enabled.
	 *
	 * @param blogStatistics the blog statistics
	 */
	public void cacheResult(java.util.List<BlogStatistic> blogStatistics);

	/**
	 * Creates a new blog statistic with the primary key. Does not add the blog statistic to the database.
	 *
	 * @param blogStatisticId the primary key for the new blog statistic
	 * @return the new blog statistic
	 */
	public BlogStatistic create(long blogStatisticId);

	/**
	 * Removes the blog statistic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param blogStatisticId the primary key of the blog statistic
	 * @return the blog statistic that was removed
	 * @throws NoSuchBlogStatisticException if a blog statistic with the primary key could not be found
	 */
	public BlogStatistic remove(long blogStatisticId)
		throws NoSuchBlogStatisticException;

	public BlogStatistic updateImpl(BlogStatistic blogStatistic);

	/**
	 * Returns the blog statistic with the primary key or throws a <code>NoSuchBlogStatisticException</code> if it could not be found.
	 *
	 * @param blogStatisticId the primary key of the blog statistic
	 * @return the blog statistic
	 * @throws NoSuchBlogStatisticException if a blog statistic with the primary key could not be found
	 */
	public BlogStatistic findByPrimaryKey(long blogStatisticId)
		throws NoSuchBlogStatisticException;

	/**
	 * Returns the blog statistic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param blogStatisticId the primary key of the blog statistic
	 * @return the blog statistic, or <code>null</code> if a blog statistic with the primary key could not be found
	 */
	public BlogStatistic fetchByPrimaryKey(long blogStatisticId);

	/**
	 * Returns all the blog statistics.
	 *
	 * @return the blog statistics
	 */
	public java.util.List<BlogStatistic> findAll();

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
	public java.util.List<BlogStatistic> findAll(int start, int end);

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
	public java.util.List<BlogStatistic> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
			orderByComparator);

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
	public java.util.List<BlogStatistic> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BlogStatistic>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the blog statistics from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of blog statistics.
	 *
	 * @return the number of blog statistics
	 */
	public int countAll();

}
// LIFERAY-SERVICE-BUILDER-HASH:-263024471