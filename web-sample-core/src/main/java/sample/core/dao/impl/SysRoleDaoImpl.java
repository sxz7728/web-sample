package sample.core.dao.impl;

import org.springframework.stereotype.Repository;

import sample.core.dao.SysRoleDao;
import sample.core.model.SysRole;
import sample.core.utils.Datagrid;
import sample.core.utils.QueryBuilder;

@Repository
public class SysRoleDaoImpl extends BaseDaoImpl<SysRole> implements SysRoleDao {
	public final String HQL_DATAGRID;

	public SysRoleDaoImpl() {
		HQL_DATAGRID = " select " + " t.id as id, " + " t.deleted as deleted, "
				+ " t.operatorId as operatorId, "
				+ " t.operateDate as operateDate " + " from "
				+ modelClass.getSimpleName() + " t where 1 = 1 {0} {1} ";
	}

	public Datagrid datagrid(QueryBuilder qb) {
		return datagrid(HQL_DATAGRID, HQL_COUNT, qb);
	}
}
