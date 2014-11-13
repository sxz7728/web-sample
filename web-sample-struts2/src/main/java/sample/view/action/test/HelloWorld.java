package sample.view.action.test;

import org.apache.struts2.convention.annotation.Action;

import sample.view.action.BaseAction;

@Action("helloWorld")
public class HelloWorld extends BaseAction {
	private static final long serialVersionUID = 1L;

	public String execute() {
		return INPUT;
	}
}
