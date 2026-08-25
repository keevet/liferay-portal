package com.liferay.training.blog.statistics.rest.dto;

import java.util.List;

public class Page<T> {

	public Page(List<T> items, int page, int pageSize, long totalCount) {
		_items = items;
		_page = page;
		_pageSize = pageSize;
		_totalCount = totalCount;
	}

	public List<T> getItems() {
		return _items;
	}

	public int getPage() {
		return _page;
	}

	public int getPageSize() {
		return _pageSize;
	}

	public long getTotalCount() {
		return _totalCount;
	}

	public int getTotalPages() {
		if (_pageSize <= 0) {
			return 0;
		}

		return (int)((_totalCount + _pageSize - 1) / _pageSize);
	}

	public boolean isHasNext() {
		return _page < getTotalPages();
	}

	private final List<T> _items;
	private final int _page;
	private final int _pageSize;
	private final long _totalCount;

}
