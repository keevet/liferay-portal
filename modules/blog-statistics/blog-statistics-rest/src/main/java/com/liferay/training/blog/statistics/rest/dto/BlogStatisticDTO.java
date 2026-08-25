package com.liferay.training.blog.statistics.rest.dto;

import com.liferay.training.blog.statistics.model.BlogStatistic;

public class BlogStatisticDTO {

	public static BlogStatisticDTO of(BlogStatistic blogStatistic) {
		return new BlogStatisticDTO(
			blogStatistic.getBlogsEntryId(), blogStatistic.getViewCount());
	}

	public static BlogStatisticDTO of(long blogsEntryId, long viewCount) {
		return new BlogStatisticDTO(blogsEntryId, viewCount);
	}

	public long getBlogsEntryId() {
		return _blogsEntryId;
	}

	public long getViewCount() {
		return _viewCount;
	}

	private BlogStatisticDTO(long blogsEntryId, long viewCount) {
		_blogsEntryId = blogsEntryId;
		_viewCount = viewCount;
	}

	private final long _blogsEntryId;
	private final long _viewCount;

}