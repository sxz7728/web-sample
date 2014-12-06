package sample.view.action.system;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import sample.core.service.SystemService;
import sample.core.utils.QueryBuilder;
import sample.core.utils.QueryUtils;
import sample.view.action.BaseAction;

public class DictList extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private SystemService systemService;

	private String dictType;

	@Action("dictList")
	public String execute() {
		return INPUT;
	}

	@Action("dictDatagrid")
	public void datagrid() {
		QueryBuilder qb = QueryUtils.addWhereNotDeleted(new QueryBuilder());
		qb.addWhere("and t.dictType = {0}", dictType);
		writeJson(systemService.datagridDict(qb));
	}

	@Action("dictionaryDict")
	public void keyValue() {
		QueryBuilder qb = QueryUtils.addWhereNotDeleted(new QueryBuilder());
		qb.addWhere("and t.dictType = {0}", dictType);
		writeJson(systemService.dictionaryDict(qb));
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
}
