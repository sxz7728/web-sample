package sample.core.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class OpenSessionInViewFilter extends
		org.springframework.orm.hibernate4.support.OpenSessionInViewFilter {

	private static final String EXCLUDE_SUFFIXS_NAME = "excludeSuffixs";

	private static String[] excludeSuffixs = {};

	@Override
	protected boolean shouldNotFilter(final HttpServletRequest request)
			throws ServletException {
		String path = request.getServletPath();

		for (String suffix : excludeSuffixs) {
			if (path.endsWith(suffix))
				return true;
		}

		return false;
	}

	@Override
	protected void initFilterBean() throws ServletException {
		String excludeSuffixStr = getFilterConfig().getInitParameter(
				EXCLUDE_SUFFIXS_NAME);

		if (StringUtils.isNotBlank(excludeSuffixStr)) {
			excludeSuffixs = excludeSuffixStr.split(",");

			for (int i = 0; i < excludeSuffixs.length; i++) {
				excludeSuffixs[i] = "." + excludeSuffixs[i];
			}
		}
	}
}
