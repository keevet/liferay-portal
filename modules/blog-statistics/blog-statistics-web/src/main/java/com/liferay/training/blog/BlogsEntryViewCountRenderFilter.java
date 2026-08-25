package com.liferay.training.blog;

import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.blogs.service.BlogsEntryLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.blog.statistics.service.BlogStatisticLocalService;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderFilter;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = "javax.portlet.name=" + BlogsPortletKeys.BLOGS,
	service = PortletFilter.class
)
public class BlogsEntryViewCountRenderFilter implements RenderFilter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(
			RenderRequest renderRequest, RenderResponse renderResponse,
			FilterChain filterChain)
		throws IOException, PortletException {

		filterChain.doFilter(renderRequest, renderResponse);

		try {
			_countView(renderRequest);
		}
		catch (Exception exception) {
			_log.error("Nao foi possivel contabilizar a visualizacao", exception);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws PortletException {
	}

	private void _countView(RenderRequest renderRequest) {
		String mvcRenderCommandName = GetterUtil.getString(
			renderRequest.getParameter("mvcRenderCommandName"));

		if (!_VIEW_ENTRY.equals(mvcRenderCommandName)) {
			return;
		}

		BlogsEntry blogsEntry = _resolveBlogsEntry(renderRequest);

		if (blogsEntry == null) {
			return;
		}

		_blogStatisticLocalService.incrementViewCount(
			blogsEntry.getEntryId(), blogsEntry.getCompanyId(),
			blogsEntry.getGroupId());
	}

	private BlogsEntry _resolveBlogsEntry(RenderRequest renderRequest) {
		long entryId = GetterUtil.getLong(
			renderRequest.getParameter("entryId"));

		if (entryId > 0) {
			return _blogsEntryLocalService.fetchBlogsEntry(entryId);
		}

		String urlTitle = GetterUtil.getString(
			renderRequest.getParameter("urlTitle"));

		if (Validator.isNull(urlTitle)) {
			return null;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (themeDisplay == null) {
			return null;
		}

		return _blogsEntryLocalService.fetchEntry(
			themeDisplay.getScopeGroupId(), urlTitle);
	}

	private static final String _VIEW_ENTRY = "/blogs/view_entry";

	private static final Log _log = LogFactoryUtil.getLog(
		BlogsEntryViewCountRenderFilter.class);

	@Reference
	private BlogStatisticLocalService _blogStatisticLocalService;

	@Reference
	private BlogsEntryLocalService _blogsEntryLocalService;

}
