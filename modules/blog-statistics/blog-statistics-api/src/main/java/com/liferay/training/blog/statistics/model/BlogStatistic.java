/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.training.blog.statistics.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the BlogStatistic service. Represents a row in the &quot;BlogStats_BlogStatistic&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see BlogStatisticModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.training.blog.statistics.model.impl.BlogStatisticImpl"
)
@ProviderType
public interface BlogStatistic extends BlogStatisticModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.training.blog.statistics.model.impl.BlogStatisticImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<BlogStatistic, Long>
		BLOG_STATISTIC_ID_ACCESSOR = new Accessor<BlogStatistic, Long>() {

			@Override
			public Long get(BlogStatistic blogStatistic) {
				return blogStatistic.getBlogStatisticId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<BlogStatistic> getTypeClass() {
				return BlogStatistic.class;
			}

		};

}
// LIFERAY-SERVICE-BUILDER-HASH:-260246415