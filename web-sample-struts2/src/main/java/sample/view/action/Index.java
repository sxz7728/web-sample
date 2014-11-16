package sample.view.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

@Namespace("/")
public class Index extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String userName;

	private String password;

	@Action("index")
	public String execute() {
		return INPUT;
	}

	@Action("login")
	public void login() {
	}

	@Action("logout")
	public void logout() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
