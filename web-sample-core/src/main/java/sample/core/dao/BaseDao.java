package sample.core.dao;

import java.util.List;

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

	public <U> List<U> hqlList(String hql, List<Object> params, int start,
			int length);

	public <U> List<U> sqlList(String sql, List<Object> params, int start,
			int length);

	public <U> U hqlUnique(String hql, List<Object> params);

	public <U> U sqlUnique(String sql, List<Object> params);
}
