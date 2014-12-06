package sample.core.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import sample.core.dao.SysDictDao;
import sample.core.model.SysDict;
import sample.core.utils.Datagrid;
import sample.core.utils.QueryBuilder;

@Repository
public class SysDictDaoImpl extends BaseDaoImpl<SysDict> implements SysDictDao {
	public final String HQL_DATAGRID;

	public final String HQL_DICTIONARY;

	public SysDictDaoImpl() {
		HQL_DATAGRID = " select " + " t.id as id, " + " t.deleted as deleted, "
				+ " t.operatorId as operatorId, "
				+ " t.operateDate as operateDate " + " from "
				+ modelClass.getSimpleName() + " t where 1 = 1 {0} {1} ";

		HQL_DICTIONARY = " select " + " t.dictType as type, "
				+ " t.dictKey as key, " + " t.dictValue as value " + " from "
				+ modelClass.getSimpleName() + " t where 1 = 1 {0} {1} ";
	}

	public Datagrid datagrid(QueryBuilder qb) {
		return datagrid(HQL_DATAGRID, HQL_COUNT, qb);
	}

	public List<Map<String, ?>> dictionary(QueryBuilder qb) {
		return hqlListMap(HQL_DICTIONARY, qb);
	}
}
