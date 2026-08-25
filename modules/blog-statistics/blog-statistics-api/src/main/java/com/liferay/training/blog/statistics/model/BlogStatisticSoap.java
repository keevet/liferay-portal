/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.training.blog.statistics.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class BlogStatisticSoap implements Serializable {

	public static BlogStatisticSoap toSoapModel(BlogStatistic model) {
		BlogStatisticSoap soapModel = new BlogStatisticSoap();

		soapModel.setBlogStatisticId(model.getBlogStatisticId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setBlogsEntryId(model.getBlogsEntryId());
		soapModel.setViewCount(model.getViewCount());

		return soapModel;
	}

	public static BlogStatisticSoap[] toSoapModels(BlogStatistic[] models) {
		BlogStatisticSoap[] soapModels = new BlogStatisticSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BlogStatisticSoap[][] toSoapModels(BlogStatistic[][] models) {
		BlogStatisticSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BlogStatisticSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BlogStatisticSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BlogStatisticSoap[] toSoapModels(List<BlogStatistic> models) {
		List<BlogStatisticSoap> soapModels = new ArrayList<BlogStatisticSoap>(
			models.size());

		for (BlogStatistic model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BlogStatisticSoap[soapModels.size()]);
	}

	public BlogStatisticSoap() {
	}

	public long getPrimaryKey() {
		return _blogStatisticId;
	}

	public void setPrimaryKey(long pk) {
		setBlogStatisticId(pk);
	}

	public long getBlogStatisticId() {
		return _blogStatisticId;
	}

	public void setBlogStatisticId(long blogStatisticId) {
		_blogStatisticId = blogStatisticId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getBlogsEntryId() {
		return _blogsEntryId;
	}

	public void setBlogsEntryId(long blogsEntryId) {
		_blogsEntryId = blogsEntryId;
	}

	public long getViewCount() {
		return _viewCount;
	}

	public void setViewCount(long viewCount) {
		_viewCount = viewCount;
	}

	private long _blogStatisticId;
	private long _groupId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _blogsEntryId;
	private long _viewCount;

}
// LIFERAY-SERVICE-BUILDER-HASH:-24545761