package sample.view.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

@Namespace("/")
public class Main extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Action("main")
	public String execute() {
		return INPUT;
	}
	
	
}
