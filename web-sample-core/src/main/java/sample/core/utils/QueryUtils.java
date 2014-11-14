package sample.core.utils;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class QueryUtils {
	public static QueryBuilder addWhereNotDeleted(QueryBuilder qb) {
		qb.addWhere("and t.deleted = {0}", DictUtils.NO);
		return qb;
	}

	public static QueryBuilder addWhereIfNotEmpty(QueryBuilder qb, String str,
			Object param) {
		if (param != null) {
			qb.addWhere(str, param);
		}

		return qb;
	}

	public static QueryBuilder addWhereIfNotEmpty(QueryBuilder qb, String str,
			String param) {
		if (!StringUtils.isEmpty(param)) {
			qb.addWhere(str, param);
		}

		return qb;
	}

	public static QueryBuilder addWhereIfNotEmpty(QueryBuilder qb, String str,
			Collection<?> param) {
		if (!CollectionUtils.isEmpty(param)) {
			qb.addWhere(str, param);
		}

		return qb;
	}
}
