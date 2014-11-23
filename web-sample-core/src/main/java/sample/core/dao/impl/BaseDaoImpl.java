package sample.core.dao.impl;

import java.lang.reflect.ParameterizedType;
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
import sample.core.utils.Datagrid;
import sample.core.utils.QueryBuilder;
import sample.core.utils.Utilities;

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
					query.setParameterList("p" + (i + 1),
							(Collection<?>) params.get(i));
				} else {
					query.setParameter("p" + (i + 1), params.get(i));
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
		String hql = Utilities
				.format(HQL_UPDATE, qb.getColumn(), qb.getWhere());
		Query query = setParams(getSession().createQuery(hql),
				qb.getParamters());
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
		String hql = Utilities.format(HQL_DELETE, qb.getWhere());
		Query query = setParams(getSession().createQuery(hql),
				qb.getParamters());
		return query.executeUpdate();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> find(QueryBuilder qb) {
		String hql = Utilities.format(HQL_FIND, qb.getWhere(), qb.getOrder());
		return hqlList(hql, qb.getParamters(), qb.getStart(), qb.getLength());
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Integer count(QueryBuilder qb) {
		String hql = Utilities.format(HQL_COUNT, qb.getWhere());
		return hqlUnique(hql, qb.getParamters());
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	protected Datagrid datagrid(String hqlFind, String hqlCount, QueryBuilder qb) {
		Datagrid result = new Datagrid();

		String hql = Utilities.format(hqlFind, qb.getWhere(), qb.getOrder());
		result.setData(hqlListMap(hql, qb.getParamters(), qb.getStart(),
				qb.getLength()));

		if (qb.getLength() > 0) {
			hql = Utilities.format(hqlCount, qb.getWhere());
			result.setCount(this.<Integer> hqlUnique(hql, qb.getParamters()));
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> hqlList(String hql, List<Object> params, int start,
			int length) {
		Query query = setParams(getSession().createQuery(hql), params);

		if (start > 0) {
			query.setFirstResult(start);
		}

		if (length > 0) {
			query.setMaxResults(length);
		}

		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Map<String, ?>> hqlListMap(String hql, List<Object> params,
			int start, int length) {
		Query query = setParams(getSession().createQuery(hql), params);

		if (start > 0) {
			query.setFirstResult(start);
		}

		if (length > 0) {
			query.setMaxResults(length);
		}

		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public <U> List<U> hqlListBean(Class<U> clazz, String hql,
			List<Object> params, int start, int length) {
		Query query = setParams(getSession().createQuery(hql), params);

		if (start > 0) {
			query.setFirstResult(start);
		}

		if (length > 0) {
			query.setMaxResults(length);
		}

		return query.setResultTransformer(Transformers.aliasToBean(clazz))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> sqlList(String sql, List<Object> params, int start,
			int length) {
		Query query = setParams(getSession().createSQLQuery(sql), params);

		if (start > 0) {
			query.setFirstResult(start);
		}

		if (length > 0) {
			query.setMaxResults(length);
		}

		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Map<String, ?>> sqlListMap(String sql, List<Object> params,
			int start, int length) {
		Query query = setParams(getSession().createSQLQuery(sql), params);

		if (start > 0) {
			query.setFirstResult(start);
		}

		if (length > 0) {
			query.setMaxResults(length);
		}

		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public <U> List<U> sqlListBean(Class<U> clazz, String hql,
			List<Object> params, int start, int length) {
		Query query = setParams(getSession().createSQLQuery(hql), params);

		if (start > 0) {
			query.setFirstResult(start);
		}

		if (length > 0) {
			query.setMaxResults(length);
		}

		return query.setResultTransformer(Transformers.aliasToBean(clazz))
				.list();
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