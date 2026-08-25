package com.liferay.training.blog.statistics.internal.command;

import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.training.blog.statistics.model.BlogStatistic;
import com.liferay.training.blog.statistics.service.BlogStatisticLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"osgi.command.scope=blogstats",
		"osgi.command.function=inc",
		"osgi.command.function=count",
		"osgi.command.function=list"
	},
	service = Object.class
)
public class BlogStatisticsCommands {

	public void inc(long blogsEntryId) {
		long companyId = PortalUtil.getDefaultCompanyId();

		System.out.println(
			"viewCount = " +
				_blogStatisticLocalService.incrementViewCount(
					blogsEntryId, companyId, 0));
	}

	public void count(long blogsEntryId) {
		System.out.println(
			_blogStatisticLocalService.getViewCount(blogsEntryId));
	}

	public void list() {
		for (BlogStatistic blogStatistic :
				_blogStatisticLocalService.getBlogStatistics(0, 100)) {

			System.out.println(
				blogStatistic.getBlogsEntryId() + " -> " +
					blogStatistic.getViewCount());
		}
	}

	@Reference
	private BlogStatisticLocalService _blogStatisticLocalService;

}