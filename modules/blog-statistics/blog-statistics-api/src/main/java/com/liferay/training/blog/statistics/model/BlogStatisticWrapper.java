/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.training.blog.statistics.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link BlogStatistic}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BlogStatistic
 * @generated
 */
public class BlogStatisticWrapper
	extends BaseModelWrapper<BlogStatistic>
	implements BlogStatistic, ModelWrapper<BlogStatistic> {

	public BlogStatisticWrapper(BlogStatistic blogStatistic) {
		super(blogStatistic);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("blogStatisticId", getBlogStatisticId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("blogsEntryId", getBlogsEntryId());
		attributes.put("viewCount", getViewCount());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long blogStatisticId = (Long)attributes.get("blogStatisticId");

		if (blogStatisticId != null) {
			setBlogStatisticId(blogStatisticId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long blogsEntryId = (Long)attributes.get("blogsEntryId");

		if (blogsEntryId != null) {
			setBlogsEntryId(blogsEntryId);
		}

		Long viewCount = (Long)attributes.get("viewCount");

		if (viewCount != null) {
			setViewCount(viewCount);
		}
	}

	/**
	 * Returns the blogs entry ID of this blog statistic.
	 *
	 * @return the blogs entry ID of this blog statistic
	 */
	@Override
	public long getBlogsEntryId() {
		return model.getBlogsEntryId();
	}

	/**
	 * Returns the blog statistic ID of this blog statistic.
	 *
	 * @return the blog statistic ID of this blog statistic
	 */
	@Override
	public long getBlogStatisticId() {
		return model.getBlogStatisticId();
	}

	/**
	 * Returns the company ID of this blog statistic.
	 *
	 * @return the company ID of this blog statistic
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this blog statistic.
	 *
	 * @return the create date of this blog statistic
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the group ID of this blog statistic.
	 *
	 * @return the group ID of this blog statistic
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this blog statistic.
	 *
	 * @return the modified date of this blog statistic
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this blog statistic.
	 *
	 * @return the primary key of this blog statistic
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the view count of this blog statistic.
	 *
	 * @return the view count of this blog statistic
	 */
	@Override
	public long getViewCount() {
		return model.getViewCount();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the blogs entry ID of this blog statistic.
	 *
	 * @param blogsEntryId the blogs entry ID of this blog statistic
	 */
	@Override
	public void setBlogsEntryId(long blogsEntryId) {
		model.setBlogsEntryId(blogsEntryId);
	}

	/**
	 * Sets the blog statistic ID of this blog statistic.
	 *
	 * @param blogStatisticId the blog statistic ID of this blog statistic
	 */
	@Override
	public void setBlogStatisticId(long blogStatisticId) {
		model.setBlogStatisticId(blogStatisticId);
	}

	/**
	 * Sets the company ID of this blog statistic.
	 *
	 * @param companyId the company ID of this blog statistic
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this blog statistic.
	 *
	 * @param createDate the create date of this blog statistic
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the group ID of this blog statistic.
	 *
	 * @param groupId the group ID of this blog statistic
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this blog statistic.
	 *
	 * @param modifiedDate the modified date of this blog statistic
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this blog statistic.
	 *
	 * @param primaryKey the primary key of this blog statistic
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the view count of this blog statistic.
	 *
	 * @param viewCount the view count of this blog statistic
	 */
	@Override
	public void setViewCount(long viewCount) {
		model.setViewCount(viewCount);
	}

	@Override
	protected BlogStatisticWrapper wrap(BlogStatistic blogStatistic) {
		return new BlogStatisticWrapper(blogStatistic);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:1884220623