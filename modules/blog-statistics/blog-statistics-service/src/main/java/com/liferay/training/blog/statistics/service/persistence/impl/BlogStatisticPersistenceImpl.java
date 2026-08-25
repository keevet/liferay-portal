/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.training.blog.statistics.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.training.blog.statistics.exception.NoSuchBlogStatisticException;
import com.liferay.training.blog.statistics.model.BlogStatistic;
import com.liferay.training.blog.statistics.model.impl.BlogStatisticImpl;
import com.liferay.training.blog.statistics.model.impl.BlogStatisticModelImpl;
import com.liferay.training.blog.statistics.service.persistence.BlogStatisticPersistence;
import com.liferay.training.blog.statistics.service.persistence.BlogStatisticUtil;
import com.liferay.training.blog.statistics.service.persistence.impl.constants.BlogStatsPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the blog statistic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = BlogStatisticPersistence.class)
public class BlogStatisticPersistenceImpl
	extends BasePersistenceImpl<BlogStatistic>
	implements BlogStatisticPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>BlogStatisticUtil</code> to access the blog statistic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		BlogStatisticImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByBlogsEntryId;
	private FinderPath _finderPathCountByBlogsEntryId;

	/**
	 * Returns the blog statistic where blogsEntryId = &#63; or throws a <code>NoSuchBlogStatisticException</code> if it could not be found.
	 *
	 * @param blogsEntryId the blogs entry ID
	 * @return the matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	@Override
	public BlogStatistic findByBlogsEntryId(long blogsEntryId)
		throws NoSuchBlogStatisticException {

		BlogStatistic blogStatistic = fetchByBlogsEntryId(blogsEntryId);

		if (blogStatistic == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("blogsEntryId=");
			sb.append(blogsEntryId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchBlogStatisticException(sb.toString());
		}

		return blogStatistic;
	}

	/**
	 * Returns the blog statistic where blogsEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param blogsEntryId the blogs entry ID
	 * @return the matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	@Override
	public BlogStatistic fetchByBlogsEntryId(long blogsEntryId) {
		return fetchByBlogsEntryId(blogsEntryId, true);
	}

	/**
	 * Returns the blog statistic where blogsEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param blogsEntryId the blogs entry ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	@Override
	public BlogStatistic fetchByBlogsEntryId(
		long blogsEntryId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {blogsEntryId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByBlogsEntryId, finderArgs, this);
		}

		if (result instanceof BlogStatistic) {
			BlogStatistic blogStatistic = (BlogStatistic)result;

			if (blogsEntryId != blogStatistic.getBlogsEntryId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_BLOGSTATISTIC_WHERE);

			sb.append(_FINDER_COLUMN_BLOGSENTRYID_BLOGSENTRYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(blogsEntryId);

				List<BlogStatistic> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByBlogsEntryId, finderArgs, list);
					}
				}
				else {
					BlogStatistic blogStatistic = list.get(0);

					result = blogStatistic;

					cacheResult(blogStatistic);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (BlogStatistic)result;
		}
	}

	/**
	 * Removes the blog statistic where blogsEntryId = &#63; from the database.
	 *
	 * @param blogsEntryId the blogs entry ID
	 * @return the blog statistic that was removed
	 */
	@Override
	public BlogStatistic removeByBlogsEntryId(long blogsEntryId)
		throws NoSuchBlogStatisticException {

		BlogStatistic blogStatistic = findByBlogsEntryId(blogsEntryId);

		return remove(blogStatistic);
	}

	/**
	 * Returns the number of blog statistics where blogsEntryId = &#63;.
	 *
	 * @param blogsEntryId the blogs entry ID
	 * @return the number of matching blog statistics
	 */
	@Override
	public int countByBlogsEntryId(long blogsEntryId) {
		FinderPath finderPath = _finderPathCountByBlogsEntryId;

		Object[] finderArgs = new Object[] {blogsEntryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_BLOGSTATISTIC_WHERE);

			sb.append(_FINDER_COLUMN_BLOGSENTRYID_BLOGSENTRYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(blogsEntryId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_BLOGSENTRYID_BLOGSENTRYID_2 =
		"blogStatistic.blogsEntryId = ?";

	private FinderPath _finderPathWithPaginationFindByCompanyId;
	private FinderPath _finderPathWithoutPaginationFindByCompanyId;
	private FinderPath _finderPathCountByCompanyId;

	/**
	 * Returns all the blog statistics where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching blog statistics
	 */
	@Override
	public List<BlogStatistic> findByCompanyId(long companyId) {
		return findByCompanyId(
			companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the blog statistics where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @return the range of matching blog statistics
	 */
	@Override
	public List<BlogStatistic> findByCompanyId(
		long companyId, int start, int end) {

		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the blog statistics where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching blog statistics
	 */
	@Override
	public List<BlogStatistic> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<BlogStatistic> orderByComparator) {

		return findByCompanyId(companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the blog statistics where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching blog statistics
	 */
	@Override
	public List<BlogStatistic> findByCompanyId(
		long companyId, int start, int end,
		OrderByComparator<BlogStatistic> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCompanyId;
				finderArgs = new Object[] {companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCompanyId;
			finderArgs = new Object[] {
				companyId, start, end, orderByComparator
			};
		}

		List<BlogStatistic> list = null;

		if (useFinderCache) {
			list = (List<BlogStatistic>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BlogStatistic blogStatistic : list) {
					if (companyId != blogStatistic.getCompanyId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_BLOGSTATISTIC_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ENTITY_ALIAS_PREFIX, orderByComparator);
			}
			else {
				sb.append(BlogStatisticModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				list = (List<BlogStatistic>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first blog statistic in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	@Override
	public BlogStatistic findByCompanyId_First(
			long companyId, OrderByComparator<BlogStatistic> orderByComparator)
		throws NoSuchBlogStatisticException {

		BlogStatistic blogStatistic = fetchByCompanyId_First(
			companyId, orderByComparator);

		if (blogStatistic != null) {
			return blogStatistic;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchBlogStatisticException(sb.toString());
	}

	/**
	 * Returns the first blog statistic in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	@Override
	public BlogStatistic fetchByCompanyId_First(
		long companyId, OrderByComparator<BlogStatistic> orderByComparator) {

		List<BlogStatistic> list = findByCompanyId(
			companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last blog statistic in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	@Override
	public BlogStatistic findByCompanyId_Last(
			long companyId, OrderByComparator<BlogStatistic> orderByComparator)
		throws NoSuchBlogStatisticException {

		BlogStatistic blogStatistic = fetchByCompanyId_Last(
			companyId, orderByComparator);

		if (blogStatistic != null) {
			return blogStatistic;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchBlogStatisticException(sb.toString());
	}

	/**
	 * Returns the last blog statistic in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	@Override
	public BlogStatistic fetchByCompanyId_Last(
		long companyId, OrderByComparator<BlogStatistic> orderByComparator) {

		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<BlogStatistic> list = findByCompanyId(
			companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public BlogStatistic[] findByCompanyId_PrevAndNext(
			long blogStatisticId, long companyId,
			OrderByComparator<BlogStatistic> orderByComparator)
		throws NoSuchBlogStatisticException {

		BlogStatistic blogStatistic = findByPrimaryKey(blogStatisticId);

		Session session = null;

		try {
			session = openSession();

			BlogStatistic[] array = new BlogStatisticImpl[3];

			array[0] = getByCompanyId_PrevAndNext(
				session, blogStatistic, companyId, orderByComparator, true);

			array[1] = blogStatistic;

			array[2] = getByCompanyId_PrevAndNext(
				session, blogStatistic, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected BlogStatistic getByCompanyId_PrevAndNext(
		Session session, BlogStatistic blogStatistic, long companyId,
		OrderByComparator<BlogStatistic> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_BLOGSTATISTIC_WHERE);

		sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ENTITY_ALIAS_PREFIX);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ENTITY_ALIAS_PREFIX);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(BlogStatisticModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						blogStatistic)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<BlogStatistic> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the blog statistics where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 */
	@Override
	public void removeByCompanyId(long companyId) {
		for (BlogStatistic blogStatistic :
				findByCompanyId(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(blogStatistic);
		}
	}

	/**
	 * Returns the number of blog statistics where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching blog statistics
	 */
	@Override
	public int countByCompanyId(long companyId) {
		FinderPath finderPath = _finderPathCountByCompanyId;

		Object[] finderArgs = new Object[] {companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_BLOGSTATISTIC_WHERE);

			sb.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 =
		"blogStatistic.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the blog statistics where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching blog statistics
	 */
	@Override
	public List<BlogStatistic> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the blog statistics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @return the range of matching blog statistics
	 */
	@Override
	public List<BlogStatistic> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the blog statistics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching blog statistics
	 */
	@Override
	public List<BlogStatistic> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<BlogStatistic> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the blog statistics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching blog statistics
	 */
	@Override
	public List<BlogStatistic> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<BlogStatistic> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<BlogStatistic> list = null;

		if (useFinderCache) {
			list = (List<BlogStatistic>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BlogStatistic blogStatistic : list) {
					if (groupId != blogStatistic.getGroupId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_BLOGSTATISTIC_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ENTITY_ALIAS_PREFIX, orderByComparator);
			}
			else {
				sb.append(BlogStatisticModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<BlogStatistic>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first blog statistic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	@Override
	public BlogStatistic findByGroupId_First(
			long groupId, OrderByComparator<BlogStatistic> orderByComparator)
		throws NoSuchBlogStatisticException {

		BlogStatistic blogStatistic = fetchByGroupId_First(
			groupId, orderByComparator);

		if (blogStatistic != null) {
			return blogStatistic;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchBlogStatisticException(sb.toString());
	}

	/**
	 * Returns the first blog statistic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	@Override
	public BlogStatistic fetchByGroupId_First(
		long groupId, OrderByComparator<BlogStatistic> orderByComparator) {

		List<BlogStatistic> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last blog statistic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blog statistic
	 * @throws NoSuchBlogStatisticException if a matching blog statistic could not be found
	 */
	@Override
	public BlogStatistic findByGroupId_Last(
			long groupId, OrderByComparator<BlogStatistic> orderByComparator)
		throws NoSuchBlogStatisticException {

		BlogStatistic blogStatistic = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (blogStatistic != null) {
			return blogStatistic;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchBlogStatisticException(sb.toString());
	}

	/**
	 * Returns the last blog statistic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	@Override
	public BlogStatistic fetchByGroupId_Last(
		long groupId, OrderByComparator<BlogStatistic> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<BlogStatistic> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public BlogStatistic[] findByGroupId_PrevAndNext(
			long blogStatisticId, long groupId,
			OrderByComparator<BlogStatistic> orderByComparator)
		throws NoSuchBlogStatisticException {

		BlogStatistic blogStatistic = findByPrimaryKey(blogStatisticId);

		Session session = null;

		try {
			session = openSession();

			BlogStatistic[] array = new BlogStatisticImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, blogStatistic, groupId, orderByComparator, true);

			array[1] = blogStatistic;

			array[2] = getByGroupId_PrevAndNext(
				session, blogStatistic, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected BlogStatistic getByGroupId_PrevAndNext(
		Session session, BlogStatistic blogStatistic, long groupId,
		OrderByComparator<BlogStatistic> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_BLOGSTATISTIC_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ENTITY_ALIAS_PREFIX);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ENTITY_ALIAS_PREFIX);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(BlogStatisticModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						blogStatistic)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<BlogStatistic> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the blog statistics where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (BlogStatistic blogStatistic :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(blogStatistic);
		}
	}

	/**
	 * Returns the number of blog statistics where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching blog statistics
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_BLOGSTATISTIC_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"blogStatistic.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByC_GtViewCount;
	private FinderPath _finderPathWithPaginationCountByC_GtViewCount;

	/**
	 * Returns all the blog statistics where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @return the matching blog statistics
	 */
	@Override
	public List<BlogStatistic> findByC_GtViewCount(
		long companyId, long viewCount) {

		return findByC_GtViewCount(
			companyId, viewCount, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the blog statistics where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @return the range of matching blog statistics
	 */
	@Override
	public List<BlogStatistic> findByC_GtViewCount(
		long companyId, long viewCount, int start, int end) {

		return findByC_GtViewCount(companyId, viewCount, start, end, null);
	}

	/**
	 * Returns an ordered range of all the blog statistics where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching blog statistics
	 */
	@Override
	public List<BlogStatistic> findByC_GtViewCount(
		long companyId, long viewCount, int start, int end,
		OrderByComparator<BlogStatistic> orderByComparator) {

		return findByC_GtViewCount(
			companyId, viewCount, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the blog statistics where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BlogStatisticModelImpl</code>.
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
	@Override
	public List<BlogStatistic> findByC_GtViewCount(
		long companyId, long viewCount, int start, int end,
		OrderByComparator<BlogStatistic> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByC_GtViewCount;
		finderArgs = new Object[] {
			companyId, viewCount, start, end, orderByComparator
		};

		List<BlogStatistic> list = null;

		if (useFinderCache) {
			list = (List<BlogStatistic>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (BlogStatistic blogStatistic : list) {
					if ((companyId != blogStatistic.getCompanyId()) ||
						(viewCount >= blogStatistic.getViewCount())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_BLOGSTATISTIC_WHERE);

			sb.append(_FINDER_COLUMN_C_GTVIEWCOUNT_COMPANYID_2);

			sb.append(_FINDER_COLUMN_C_GTVIEWCOUNT_VIEWCOUNT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ENTITY_ALIAS_PREFIX, orderByComparator);
			}
			else {
				sb.append(BlogStatisticModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				queryPos.add(viewCount);

				list = (List<BlogStatistic>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public BlogStatistic findByC_GtViewCount_First(
			long companyId, long viewCount,
			OrderByComparator<BlogStatistic> orderByComparator)
		throws NoSuchBlogStatisticException {

		BlogStatistic blogStatistic = fetchByC_GtViewCount_First(
			companyId, viewCount, orderByComparator);

		if (blogStatistic != null) {
			return blogStatistic;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", viewCount>");
		sb.append(viewCount);

		sb.append("}");

		throw new NoSuchBlogStatisticException(sb.toString());
	}

	/**
	 * Returns the first blog statistic in the ordered set where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	@Override
	public BlogStatistic fetchByC_GtViewCount_First(
		long companyId, long viewCount,
		OrderByComparator<BlogStatistic> orderByComparator) {

		List<BlogStatistic> list = findByC_GtViewCount(
			companyId, viewCount, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public BlogStatistic findByC_GtViewCount_Last(
			long companyId, long viewCount,
			OrderByComparator<BlogStatistic> orderByComparator)
		throws NoSuchBlogStatisticException {

		BlogStatistic blogStatistic = fetchByC_GtViewCount_Last(
			companyId, viewCount, orderByComparator);

		if (blogStatistic != null) {
			return blogStatistic;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", viewCount>");
		sb.append(viewCount);

		sb.append("}");

		throw new NoSuchBlogStatisticException(sb.toString());
	}

	/**
	 * Returns the last blog statistic in the ordered set where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching blog statistic, or <code>null</code> if a matching blog statistic could not be found
	 */
	@Override
	public BlogStatistic fetchByC_GtViewCount_Last(
		long companyId, long viewCount,
		OrderByComparator<BlogStatistic> orderByComparator) {

		int count = countByC_GtViewCount(companyId, viewCount);

		if (count == 0) {
			return null;
		}

		List<BlogStatistic> list = findByC_GtViewCount(
			companyId, viewCount, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public BlogStatistic[] findByC_GtViewCount_PrevAndNext(
			long blogStatisticId, long companyId, long viewCount,
			OrderByComparator<BlogStatistic> orderByComparator)
		throws NoSuchBlogStatisticException {

		BlogStatistic blogStatistic = findByPrimaryKey(blogStatisticId);

		Session session = null;

		try {
			session = openSession();

			BlogStatistic[] array = new BlogStatisticImpl[3];

			array[0] = getByC_GtViewCount_PrevAndNext(
				session, blogStatistic, companyId, viewCount, orderByComparator,
				true);

			array[1] = blogStatistic;

			array[2] = getByC_GtViewCount_PrevAndNext(
				session, blogStatistic, companyId, viewCount, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected BlogStatistic getByC_GtViewCount_PrevAndNext(
		Session session, BlogStatistic blogStatistic, long companyId,
		long viewCount, OrderByComparator<BlogStatistic> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_BLOGSTATISTIC_WHERE);

		sb.append(_FINDER_COLUMN_C_GTVIEWCOUNT_COMPANYID_2);

		sb.append(_FINDER_COLUMN_C_GTVIEWCOUNT_VIEWCOUNT_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ENTITY_ALIAS_PREFIX);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ENTITY_ALIAS_PREFIX);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(BlogStatisticModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		queryPos.add(viewCount);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						blogStatistic)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<BlogStatistic> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the blog statistics where companyId = &#63; and viewCount &gt; &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 */
	@Override
	public void removeByC_GtViewCount(long companyId, long viewCount) {
		for (BlogStatistic blogStatistic :
				findByC_GtViewCount(
					companyId, viewCount, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(blogStatistic);
		}
	}

	/**
	 * Returns the number of blog statistics where companyId = &#63; and viewCount &gt; &#63;.
	 *
	 * @param companyId the company ID
	 * @param viewCount the view count
	 * @return the number of matching blog statistics
	 */
	@Override
	public int countByC_GtViewCount(long companyId, long viewCount) {
		FinderPath finderPath = _finderPathWithPaginationCountByC_GtViewCount;

		Object[] finderArgs = new Object[] {companyId, viewCount};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_BLOGSTATISTIC_WHERE);

			sb.append(_FINDER_COLUMN_C_GTVIEWCOUNT_COMPANYID_2);

			sb.append(_FINDER_COLUMN_C_GTVIEWCOUNT_VIEWCOUNT_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				queryPos.add(viewCount);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_GTVIEWCOUNT_COMPANYID_2 =
		"blogStatistic.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_GTVIEWCOUNT_VIEWCOUNT_2 =
		"blogStatistic.viewCount > ?";

	public BlogStatisticPersistenceImpl() {
		setModelClass(BlogStatistic.class);

		setModelImplClass(BlogStatisticImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the blog statistic in the entity cache if it is enabled.
	 *
	 * @param blogStatistic the blog statistic
	 */
	@Override
	public void cacheResult(BlogStatistic blogStatistic) {
		entityCache.putResult(
			BlogStatisticImpl.class, blogStatistic.getPrimaryKey(),
			blogStatistic);

		finderCache.putResult(
			_finderPathFetchByBlogsEntryId,
			new Object[] {blogStatistic.getBlogsEntryId()}, blogStatistic);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the blog statistics in the entity cache if it is enabled.
	 *
	 * @param blogStatistics the blog statistics
	 */
	@Override
	public void cacheResult(List<BlogStatistic> blogStatistics) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (blogStatistics.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (BlogStatistic blogStatistic : blogStatistics) {
			if (entityCache.getResult(
					BlogStatisticImpl.class, blogStatistic.getPrimaryKey()) ==
						null) {

				cacheResult(blogStatistic);
			}
		}
	}

	/**
	 * Clears the cache for all blog statistics.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(BlogStatisticImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the blog statistic.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(BlogStatistic blogStatistic) {
		entityCache.removeResult(BlogStatisticImpl.class, blogStatistic);
	}

	@Override
	public void clearCache(List<BlogStatistic> blogStatistics) {
		for (BlogStatistic blogStatistic : blogStatistics) {
			entityCache.removeResult(BlogStatisticImpl.class, blogStatistic);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(BlogStatisticImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		BlogStatisticModelImpl blogStatisticModelImpl) {

		Object[] args = new Object[] {blogStatisticModelImpl.getBlogsEntryId()};

		finderCache.putResult(
			_finderPathCountByBlogsEntryId, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByBlogsEntryId, args, blogStatisticModelImpl,
			false);
	}

	/**
	 * Creates a new blog statistic with the primary key. Does not add the blog statistic to the database.
	 *
	 * @param blogStatisticId the primary key for the new blog statistic
	 * @return the new blog statistic
	 */
	@Override
	public BlogStatistic create(long blogStatisticId) {
		BlogStatistic blogStatistic = new BlogStatisticImpl();

		blogStatistic.setNew(true);
		blogStatistic.setPrimaryKey(blogStatisticId);

		blogStatistic.setCompanyId(CompanyThreadLocal.getCompanyId());

		return blogStatistic;
	}

	/**
	 * Removes the blog statistic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param blogStatisticId the primary key of the blog statistic
	 * @return the blog statistic that was removed
	 * @throws NoSuchBlogStatisticException if a blog statistic with the primary key could not be found
	 */
	@Override
	public BlogStatistic remove(long blogStatisticId)
		throws NoSuchBlogStatisticException {

		return remove((Serializable)blogStatisticId);
	}

	/**
	 * Removes the blog statistic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the blog statistic
	 * @return the blog statistic that was removed
	 * @throws NoSuchBlogStatisticException if a blog statistic with the primary key could not be found
	 */
	@Override
	public BlogStatistic remove(Serializable primaryKey)
		throws NoSuchBlogStatisticException {

		Session session = null;

		try {
			session = openSession();

			BlogStatistic blogStatistic = (BlogStatistic)session.get(
				BlogStatisticImpl.class, primaryKey);

			if (blogStatistic == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchBlogStatisticException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(blogStatistic);
		}
		catch (NoSuchBlogStatisticException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected BlogStatistic removeImpl(BlogStatistic blogStatistic) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(blogStatistic)) {
				blogStatistic = (BlogStatistic)session.get(
					BlogStatisticImpl.class, blogStatistic.getPrimaryKeyObj());
			}

			if (blogStatistic != null) {
				session.delete(blogStatistic);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (blogStatistic != null) {
			clearCache(blogStatistic);
		}

		return blogStatistic;
	}

	@Override
	public BlogStatistic updateImpl(BlogStatistic blogStatistic) {
		boolean isNew = blogStatistic.isNew();

		if (!(blogStatistic instanceof BlogStatisticModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(blogStatistic.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					blogStatistic);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in blogStatistic proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom BlogStatistic implementation " +
					blogStatistic.getClass());
		}

		BlogStatisticModelImpl blogStatisticModelImpl =
			(BlogStatisticModelImpl)blogStatistic;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (blogStatistic.getCreateDate() == null)) {
			if (serviceContext == null) {
				blogStatistic.setCreateDate(date);
			}
			else {
				blogStatistic.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!blogStatisticModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				blogStatistic.setModifiedDate(date);
			}
			else {
				blogStatistic.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(blogStatistic);
			}
			else {
				blogStatistic = (BlogStatistic)session.merge(blogStatistic);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			BlogStatisticImpl.class, blogStatisticModelImpl, false, true);

		cacheUniqueFindersCache(blogStatisticModelImpl);

		if (isNew) {
			blogStatistic.setNew(false);
		}

		blogStatistic.resetOriginalValues();

		return blogStatistic;
	}

	/**
	 * Returns the blog statistic with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the blog statistic
	 * @return the blog statistic
	 * @throws NoSuchBlogStatisticException if a blog statistic with the primary key could not be found
	 */
	@Override
	public BlogStatistic findByPrimaryKey(Serializable primaryKey)
		throws NoSuchBlogStatisticException {

		BlogStatistic blogStatistic = fetchByPrimaryKey(primaryKey);

		if (blogStatistic == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchBlogStatisticException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return blogStatistic;
	}

	/**
	 * Returns the blog statistic with the primary key or throws a <code>NoSuchBlogStatisticException</code> if it could not be found.
	 *
	 * @param blogStatisticId the primary key of the blog statistic
	 * @return the blog statistic
	 * @throws NoSuchBlogStatisticException if a blog statistic with the primary key could not be found
	 */
	@Override
	public BlogStatistic findByPrimaryKey(long blogStatisticId)
		throws NoSuchBlogStatisticException {

		return findByPrimaryKey((Serializable)blogStatisticId);
	}

	/**
	 * Returns the blog statistic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param blogStatisticId the primary key of the blog statistic
	 * @return the blog statistic, or <code>null</code> if a blog statistic with the primary key could not be found
	 */
	@Override
	public BlogStatistic fetchByPrimaryKey(long blogStatisticId) {
		return fetchByPrimaryKey((Serializable)blogStatisticId);
	}

	/**
	 * Returns all the blog statistics.
	 *
	 * @return the blog statistics
	 */
	@Override
	public List<BlogStatistic> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the blog statistics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @return the range of blog statistics
	 */
	@Override
	public List<BlogStatistic> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the blog statistics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of blog statistics
	 */
	@Override
	public List<BlogStatistic> findAll(
		int start, int end,
		OrderByComparator<BlogStatistic> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the blog statistics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BlogStatisticModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of blog statistics
	 * @param end the upper bound of the range of blog statistics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of blog statistics
	 */
	@Override
	public List<BlogStatistic> findAll(
		int start, int end, OrderByComparator<BlogStatistic> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<BlogStatistic> list = null;

		if (useFinderCache) {
			list = (List<BlogStatistic>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_BLOGSTATISTIC);

				appendOrderByComparator(
					sb, _ENTITY_ALIAS_PREFIX, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_BLOGSTATISTIC;

				sql = sql.concat(BlogStatisticModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<BlogStatistic>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the blog statistics from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (BlogStatistic blogStatistic : findAll()) {
			remove(blogStatistic);
		}
	}

	/**
	 * Returns the number of blog statistics.
	 *
	 * @return the number of blog statistics
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					"SELECT COUNT(blogStatistic) FROM BlogStatistic blogStatistic");

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "blogStatisticId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_BLOGSTATISTIC;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return BlogStatisticModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the blog statistic persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class, new BlogStatisticModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", BlogStatistic.class.getName()));

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathFetchByBlogsEntryId = _createFinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByBlogsEntryId",
			new String[] {Long.class.getName()}, new String[] {"blogsEntryId"},
			true);

		_finderPathCountByBlogsEntryId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByBlogsEntryId",
			new String[] {Long.class.getName()}, new String[] {"blogsEntryId"},
			false);

		_finderPathWithPaginationFindByCompanyId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"companyId"}, true);

		_finderPathWithoutPaginationFindByCompanyId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] {Long.class.getName()}, new String[] {"companyId"},
			true);

		_finderPathCountByCompanyId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] {Long.class.getName()}, new String[] {"companyId"},
			false);

		_finderPathWithPaginationFindByGroupId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId"}, true);

		_finderPathWithoutPaginationFindByGroupId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			true);

		_finderPathCountByGroupId = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			false);

		_finderPathWithPaginationFindByC_GtViewCount = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_GtViewCount",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"companyId", "viewCount"}, true);

		_finderPathWithPaginationCountByC_GtViewCount = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_GtViewCount",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"companyId", "viewCount"}, false);

		BlogStatisticUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		BlogStatisticUtil.setPersistence(null);

		entityCache.removeCache(BlogStatisticImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	@Override
	@Reference(
		target = BlogStatsPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = BlogStatsPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = BlogStatsPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private BundleContext _bundleContext;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _ENTITY_ALIAS_PREFIX =
		BlogStatisticModelImpl.ENTITY_ALIAS + ".";

	private static final String _SQL_SELECT_BLOGSTATISTIC =
		"SELECT blogStatistic FROM BlogStatistic blogStatistic";

	private static final String _SQL_SELECT_BLOGSTATISTIC_WHERE =
		"SELECT blogStatistic FROM BlogStatistic blogStatistic WHERE ";

	private static final String _SQL_COUNT_BLOGSTATISTIC_WHERE =
		"SELECT COUNT(blogStatistic) FROM BlogStatistic blogStatistic WHERE ";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No BlogStatistic exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No BlogStatistic exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		BlogStatisticPersistenceImpl.class);

	private FinderPath _createFinderPath(
		String cacheName, String methodName, String[] params,
		String[] columnNames, boolean baseModelResult) {

		FinderPath finderPath = new FinderPath(
			cacheName, methodName, params, columnNames, baseModelResult);

		if (!cacheName.equals(FINDER_CLASS_NAME_LIST_WITH_PAGINATION)) {
			_serviceRegistrations.add(
				_bundleContext.registerService(
					FinderPath.class, finderPath,
					MapUtil.singletonDictionary("cache.name", cacheName)));
		}

		return finderPath;
	}

	private Set<ServiceRegistration<FinderPath>> _serviceRegistrations =
		new HashSet<>();
	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;

	private static class BlogStatisticModelArgumentsResolver
		implements ArgumentsResolver {

		@Override
		public Object[] getArguments(
			FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
			boolean original) {

			String[] columnNames = finderPath.getColumnNames();

			if ((columnNames == null) || (columnNames.length == 0)) {
				if (baseModel.isNew()) {
					return new Object[0];
				}

				return null;
			}

			BlogStatisticModelImpl blogStatisticModelImpl =
				(BlogStatisticModelImpl)baseModel;

			long columnBitmask = blogStatisticModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(blogStatisticModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						blogStatisticModelImpl.getColumnBitmask(columnName);
				}

				if (finderPath.isBaseModelResult() &&
					(BlogStatisticPersistenceImpl.
						FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION ==
							finderPath.getCacheName())) {

					finderPathColumnBitmask |= _ORDER_BY_COLUMNS_BITMASK;
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(blogStatisticModelImpl, columnNames, original);
			}

			return null;
		}

		private static Object[] _getValue(
			BlogStatisticModelImpl blogStatisticModelImpl, String[] columnNames,
			boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] =
						blogStatisticModelImpl.getColumnOriginalValue(
							columnName);
				}
				else {
					arguments[i] = blogStatisticModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static final Map<FinderPath, Long>
			_finderPathColumnBitmasksCache = new ConcurrentHashMap<>();

		private static final long _ORDER_BY_COLUMNS_BITMASK;

		static {
			long orderByColumnsBitmask = 0;

			orderByColumnsBitmask |= BlogStatisticModelImpl.getColumnBitmask(
				"viewCount");

			_ORDER_BY_COLUMNS_BITMASK = orderByColumnsBitmask;
		}

	}

}
// LIFERAY-SERVICE-BUILDER-HASH:272140991