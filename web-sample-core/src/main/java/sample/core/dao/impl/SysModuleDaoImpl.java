package sample.core.dao.impl;

import org.springframework.stereotype.Repository;

import sample.core.dao.SysModuleDao;
import sample.core.model.SysModule;
import sample.core.utils.Datagrid;
import sample.core.utils.QueryBuilder;

@Repository
public class SysModuleDaoImpl extends BaseDaoImpl<SysModule> implements
		SysModuleDao {
	public final String HQL_FIND;

	public SysModuleDaoImpl() {
		HQL_FIND = " select " + " t.id as id, " + " t.deleted as deleted, "
				+ " t.operatorId as operatorId, "
				+ " t.operateDate as operateDate " + " from "
				+ modelClass.getSimpleName() + " t where 1 = 1 {0} {1} ";
	}

	public Datagrid datagrid(QueryBuilder qb) {
		return datagrid(HQL_FIND, HQL_COUNT, qb);
	}
}
