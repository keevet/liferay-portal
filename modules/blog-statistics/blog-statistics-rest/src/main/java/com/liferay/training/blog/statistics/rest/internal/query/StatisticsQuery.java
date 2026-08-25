package com.liferay.training.blog.statistics.rest.internal.query;

import com.liferay.training.blog.statistics.model.BlogStatisticField;

public class StatisticsQuery {

	public static final int DEFAULT_PAGE_SIZE = 20;

	public static final int MAX_PAGE_SIZE = 200;

	public static StatisticsQuery of(
			int page, int pageSize, String sort, Long minViewCount,
			Long maxViewCount)
		throws InvalidQueryException {

		if ((minViewCount != null) && (maxViewCount != null) &&
			(minViewCount > maxViewCount)) {

			throw new InvalidQueryException(
				"minViewCount nao pode ser maior que maxViewCount");
		}

		String sortField = BlogStatisticField.VIEW_COUNT;
		boolean ascending = false;

		if ((sort != null) && !sort.trim().isEmpty()) {
			String[] parts = sort.trim().split(":", -1);

			sortField = parts[0].trim();

			if (!BlogStatisticField.isSortable(sortField)) {
				throw new InvalidQueryException(
					"Campo de ordenacao desconhecido: " + sortField);
			}

			if (parts.length == 2) {
				ascending = "asc".equalsIgnoreCase(parts[1].trim());
			}
		}

		return new StatisticsQuery(
			Math.max(page, 1),
			(pageSize <= 0) ? DEFAULT_PAGE_SIZE :
				Math.min(pageSize, MAX_PAGE_SIZE),
			sortField, ascending, minViewCount, maxViewCount);
	}

	public int getStart() {
		return (_page - 1) * _pageSize;
	}

	public int getEnd() {
		return getStart() + _pageSize;
	}

	public int getPage() {
		return _page;
	}

	public int getPageSize() {
		return _pageSize;
	}

	public String getSortField() {
		return _sortField;
	}

	public boolean isAscending() {
		return _ascending;
	}

	public Long getMinViewCount() {
		return _minViewCount;
	}

	public Long getMaxViewCount() {
		return _maxViewCount;
	}

	private StatisticsQuery(
		int page, int pageSize, String sortField, boolean ascending,
		Long minViewCount, Long maxViewCount) {

		_page = page;
		_pageSize = pageSize;
		_sortField = sortField;
		_ascending = ascending;
		_minViewCount = minViewCount;
		_maxViewCount = maxViewCount;
	}

	private final boolean _ascending;
	private final Long _maxViewCount;
	private final Long _minViewCount;
	private final int _page;
	private final int _pageSize;
	private final String _sortField;

}