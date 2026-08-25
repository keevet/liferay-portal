package com.liferay.training.blog.statistics.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.training.blog.statistics.model.impl.BlogStatisticModelImpl;
import com.liferay.training.blog.statistics.service.persistence.BlogStatisticFinder;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

/**
 * @author kevin
 */
@Component(service = BlogStatisticFinder.class)
public class BlogStatisticFinderImpl
	extends BlogStatisticFinderBaseImpl implements BlogStatisticFinder {

	public int incrementViewCount(
		long blogsEntryId, long delta, Date modifiedDate) {

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSQLQuery(_SQL_INCREMENT);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(delta);
			queryPos.add(modifiedDate);
			queryPos.add(blogsEntryId);

			return sqlQuery.executeUpdate();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	public long fetchViewCount(long blogsEntryId) {
		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSQLQuery(_SQL_SELECT_VIEW_COUNT);

			sqlQuery.addScalar("viewCount", Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(blogsEntryId);

			Long viewCount = (Long)sqlQuery.uniqueResult();

			if (viewCount == null) {
				return -1;
			}

			return viewCount;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _SQL_INCREMENT =
		"UPDATE " + BlogStatisticModelImpl.TABLE_NAME +
			" SET viewCount = viewCount + ?, modifiedDate = ? WHERE " +
				"blogsEntryId = ?";

	private static final String _SQL_SELECT_VIEW_COUNT =
		"SELECT viewCount FROM " + BlogStatisticModelImpl.TABLE_NAME +
			" WHERE blogsEntryId = ?";

    }