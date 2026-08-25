/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.training.blog.statistics.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.training.blog.statistics.model.BlogStatistic;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BlogStatistic in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class BlogStatisticCacheModel
	implements CacheModel<BlogStatistic>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof BlogStatisticCacheModel)) {
			return false;
		}

		BlogStatisticCacheModel blogStatisticCacheModel =
			(BlogStatisticCacheModel)object;

		if (blogStatisticId == blogStatisticCacheModel.blogStatisticId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, blogStatisticId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{blogStatisticId=");
		sb.append(blogStatisticId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", blogsEntryId=");
		sb.append(blogsEntryId);
		sb.append(", viewCount=");
		sb.append(viewCount);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public BlogStatistic toEntityModel() {
		BlogStatisticImpl blogStatisticImpl = new BlogStatisticImpl();

		blogStatisticImpl.setBlogStatisticId(blogStatisticId);
		blogStatisticImpl.setGroupId(groupId);
		blogStatisticImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			blogStatisticImpl.setCreateDate(null);
		}
		else {
			blogStatisticImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			blogStatisticImpl.setModifiedDate(null);
		}
		else {
			blogStatisticImpl.setModifiedDate(new Date(modifiedDate));
		}

		blogStatisticImpl.setBlogsEntryId(blogsEntryId);
		blogStatisticImpl.setViewCount(viewCount);

		blogStatisticImpl.resetOriginalValues();

		return blogStatisticImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		blogStatisticId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		blogsEntryId = objectInput.readLong();

		viewCount = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(blogStatisticId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(blogsEntryId);

		objectOutput.writeLong(viewCount);
	}

	public long blogStatisticId;
	public long groupId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public long blogsEntryId;
	public long viewCount;

}
// LIFERAY-SERVICE-BUILDER-HASH:-1687254247