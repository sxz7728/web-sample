package sample.core.dao;

import java.util.List;
import java.util.Map;

import sample.core.utils.Datagrid;
import sample.core.utils.QueryBuilder;

public interface BaseDao<T> {
	public T load(Integer id);

	public T save(T obj);

	public T update(T obj);

	public T saveOrUpdate(T obj);

	public Integer update(QueryBuilder qb);

	public void delete(T obj);

	public void delete(Integer id);

	public Integer delete(QueryBuilder qb);

	public List<T> find(QueryBuilder qb);

	public Integer count(QueryBuilder qb);

	public Datagrid datagrid(QueryBuilder qb);

	public List<T> hqlList(String hql, List<Object> params, int start,
			int length);

	public List<Map<String, ?>> hqlListMap(String hql, List<Object> params,
			int start, int length);

	public <U> List<U> hqlListBean(Class<U> clazz, String hql,
			List<Object> params, int start, int length);

	public List<T> sqlList(String sql, List<Object> params, int start,
			int length);

	public List<Map<String, ?>> sqlListMap(String hql, List<Object> params,
			int start, int length);

	public <U> List<U> sqlListBean(Class<U> clazz, String hql,
			List<Object> params, int start, int length);

	public <U> U hqlUnique(String hql, List<Object> params);

	public <U> U sqlUnique(String sql, List<Object> params);
}
