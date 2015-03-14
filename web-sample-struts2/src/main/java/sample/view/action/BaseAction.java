package sample.view.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;

import sample.core.info.UserInfo;
import sample.core.service.SystemService;
import sample.core.utils.AuthFailedException;
import sample.core.utils.DictUtils;
import sample.core.utils.JsonResult;
import sample.core.utils.JsonUtils;
import sample.core.utils.Properties;
import sample.core.utils.QueryBuilder;
import sample.core.utils.QueryUtils;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware, ServletContextAware {

	private static final long serialVersionUID = 1L;

	private static final String SESSION_USER_INFO = "user_info";

	private final boolean needAuth;

	private HttpServletRequest servletRequest;

	private HttpServletResponse servletResponse;

	private ServletContext servletContext;

	@Autowired
	private Properties properties;

	@Autowired
	private DictUtils dictUtils;

	@Autowired
	private SystemService systemService;

	private UserInfo userInfo;

	public BaseAction() {
		needAuth = true;
	}

	public BaseAction(boolean needAuth) {
		this.needAuth = needAuth;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public HttpServletResponse getServletResponse() {
		return servletResponse;
	}

	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

	public HttpSession getSession() {
		return servletRequest.getSession();
	}

	public void authenticate() {
		if (needAuth) {
			userInfo = (UserInfo) getSession().getAttribute(SESSION_USER_INFO);

			if (userInfo == null) {
				throw new AuthFailedException("not logged in.");
			}

			userInfo.setOperateDate(new Date());
		}
	}

	public UserInfo getUserInfo() {
		if (userInfo == null) {
			userInfo = (UserInfo) getSession().getAttribute(SESSION_USER_INFO);
			userInfo.setOperateDate(new Date());
		}

		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		getSession().setAttribute(SESSION_USER_INFO, userInfo);
	}

	public Properties getProperties() {
		return properties;
	}

	public DictUtils getDictUtils() {
		return dictUtils;
	}

	public List<Map<String, ?>> findDict(String dictType) {
		QueryBuilder qb = QueryUtils.addWhereNotDeleted(new QueryBuilder());
		QueryUtils.addWhere(qb, "and t.dictType = {0}", dictType);
		return systemService.dictionaryDict(qb);
	}

	public void writeJson(JsonResult result) {
		PrintWriter writer = null;

		try {
			servletResponse.setContentType("text/html;charset=utf-8");
			writer = servletResponse.getWriter();
			writer.println(JsonUtils.toJson(result));
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}

	public void writeJson(Object data) {
		JsonResult result = new JsonResult();
		result.setSuccess(true);
		result.setData(data);
		writeJson(result);
	}

	public void writeJson(Exception error) {
		JsonResult result = new JsonResult();
		result.setSuccess(false);

		if (Properties.DEV_MODE) {
			result.setError(error.getMessage());
		}

		writeJson(result);
	}
}
