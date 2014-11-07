package sample.core.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sample.core.dao.BaseDao;
import sample.core.utils.QueryBuilder;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	public final Class<T> modelClass;

	public final String HQL_UPDATE;

	public final String HQL_DELETE;

	public final String HQL_FIND;

	public final String HQL_COUNT;

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		super();
		modelClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		HQL_UPDATE = " update " + modelClass.getSimpleName()
				+ " t set {0} where 1 = 1 {1} ";

		HQL_DELETE = " delete " + modelClass.getSimpleName()
				+ " t where 1 = 1 {0} ";

		HQL_FIND = " from " + modelClass.getSimpleName()
				+ " t where 1 = 1 {0} {1} ";

		HQL_COUNT = " select count(*) from " + modelClass.getSimpleName()
				+ " t where 1 = 1 {0} ";
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected Query setParams(Query query, List<Object> params) {
		if (params != null) {
			for (int i = 0; i < params.size(); ++i) {
				if (params.get(i) instanceof Collection) {
					query.setParameterList(String.valueOf(i),
							(Collection<?>) params.get(i));
				} else {
					query.setParameter(String.valueOf(i), params.get(i));
				}
			}
		}

		return query;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public T load(Integer id) {
		return (T) getSession().load(modelClass, id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public T save(T obj) {
		getSession().save(obj);
		return obj;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public T update(T obj) {
		getSession().update(obj);
		return obj;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public T saveOrUpdate(T obj) {
		getSession().saveOrUpdate(obj);
		return obj;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer update(QueryBuilder qb) {
		assert (qb.hasWhere());
		String hql = MessageFormat.format(HQL_UPDATE, qb.getColumn(),
				qb.getWhere());
		Query query = setParams(getSession().createQuery(hql), qb.getParams());
		return query.executeUpdate();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(T obj) {
		getSession().delete(obj);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Integer id) {
		delete(load(id));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer delete(QueryBuilder qb) {
		assert (qb.hasWhere());
		String hql = MessageFormat.format(HQL_DELETE, qb.getWhere());
		Query query = setParams(getSession().createQuery(hql), qb.getParams());
		return query.executeUpdate();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> find(QueryBuilder qb) {
		String hql = MessageFormat.format(HQL_FIND, qb.getWhere(),
				qb.getOrder());
		return hqlList(hql, qb.getParams(), qb.getStart(), qb.getLength());
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Integer count(QueryBuilder qb) {
		String hql = MessageFormat.format(HQL_FIND, qb.getWhere());
		return hqlUnique(hql, qb.getParams());
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public <U> List<U> hqlList(String hql, List<Object> params, int start,
			int length) {
		Query query = setParams(getSession().createQuery(hql), params);

		if (start > 0) {
			query.setFirstResult(start);
		}

		if (length > 0) {
			query.setMaxResults(length);
		}

		Class<U> entityClass = (Class<U>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		if (entityClass.isAssignableFrom(Map.class)) {
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		} else if (!entityClass.equals(modelClass)) {
			query.setResultTransformer(Transformers.aliasToBean(entityClass));
		}

		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public <U> List<U> sqlList(String sql, List<Object> params, int start,
			int length) {
		Query query = setParams(getSession().createSQLQuery(sql), params);

		if (start > 0) {
			query.setFirstResult(start);
		}

		if (length > 0) {
			query.setMaxResults(length);
		}

		Class<U> entityClass = (Class<U>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		if (entityClass.isAssignableFrom(Map.class)) {
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		} else {
			query.setResultTransformer(Transformers.aliasToBean(entityClass));
		}

		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public <U> U hqlUnique(String hql, List<Object> params) {
		Query query = setParams(getSession().createQuery(hql), params);
		return (U) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public <U> U sqlUnique(String sql, List<Object> params) {
		Query query = setParams(getSession().createSQLQuery(sql), params);
		return (U) query.uniqueResult();
	}
}