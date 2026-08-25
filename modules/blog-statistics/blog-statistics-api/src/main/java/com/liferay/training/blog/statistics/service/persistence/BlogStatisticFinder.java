/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.training.blog.statistics.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface BlogStatisticFinder {

	public int incrementViewCount(
		long blogsEntryId, long delta, java.util.Date modifiedDate);

	public long fetchViewCount(long blogsEntryId);

}
// LIFERAY-SERVICE-BUILDER-HASH:456530450