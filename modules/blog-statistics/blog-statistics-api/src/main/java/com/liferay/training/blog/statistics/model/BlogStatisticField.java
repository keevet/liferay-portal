package com.liferay.training.blog.statistics.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Kevin
 */
public class BlogStatisticField {

	public static final String BLOGS_ENTRY_ID = "blogsEntryId";

	public static final String CREATE_DATE = "createDate";

	public static final String MODIFIED_DATE = "modifiedDate";

	public static final String VIEW_COUNT = "viewCount";

	public static final Set<String> SORTABLE = Collections.unmodifiableSet(
		new LinkedHashSet<>(
			Arrays.asList(
				VIEW_COUNT, BLOGS_ENTRY_ID, CREATE_DATE, MODIFIED_DATE)));

	public static boolean isSortable(String fieldName) {
		return SORTABLE.contains(fieldName);
	}

}
