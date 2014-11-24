package sample.core.utils;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

public class QueryBuilder {

	public final String PREFIX_COLUMN_PARAMS = "c";

	public final String PREFIX_WHERE_PARAMS = "w";

	private StringBuilder column = new StringBuilder();

	private StringBuilder where = new StringBuilder();

	private StringBuilder order = new StringBuilder();

	private List<Object> columnParams = Lists.newArrayList();

	private List<Object> whereParams = Lists.newArrayList();

	private int start = 0;

	private int length = 0;

	public QueryBuilder() {
	}

	public QueryBuilder(int start, int length) {
		this.start = start;
		this.length = length;
	}

	public QueryBuilder addColumn(String str) {
		if (column.length() > 0) {
			column.append(",");
		}

		column.append(str);
		return this;
	}

	public QueryBuilder addColumn(String str, Object param) {
		if (column.length() > 0) {
			column.append(",");
		}

		columnParams.add(param);
		column.append(Utilities.format(str, ":" + PREFIX_COLUMN_PARAMS
				+ columnParams.size()));
		return this;
	}

	public QueryBuilder addWhere(String str, Object... params) {
		if (params.length == 0) {
			where.append(" ").append(str);
		} else if (params.length == 1) {
			whereParams.add(params[0]);

			if (params[0] instanceof Collection) {
				where.append(" ").append(
						Utilities.format(str, "(:" + PREFIX_WHERE_PARAMS
								+ whereParams.size() + ")"));
			} else {
				where.append(" ").append(
						Utilities.format(str, ":" + PREFIX_WHERE_PARAMS
								+ whereParams.size()));
			}
		} else {
			List<String> args = Lists.newArrayList();

			for (Object param : params) {
				whereParams.add(param);

				if (params[0] instanceof Collection) {
					args.add("(:" + PREFIX_WHERE_PARAMS + whereParams.size()
							+ ")");
				} else {
					args.add(":" + PREFIX_WHERE_PARAMS + whereParams.size());
				}
			}

			where.append(" ").append(Utilities.format(str, args.toArray()));
		}

		return this;
	}

	public QueryBuilder addOrder(String str) {
		if (order.length() > 0) {
			order.append(",");
		} else {
			order.append(" order by ");
		}

		order.append(str);
		return this;
	}

	public String getColumn() {
		return column.toString();
	}

	public boolean hasColumn() {
		return column.length() > 0;
	}

	public String getWhere() {
		return where.toString();
	}

	public boolean hasWhere() {
		return where.length() > 0;
	}

	public String getOrder() {
		return order.toString();
	}

	public List<Object> getColumnParams() {
		return columnParams;
	}

	public List<Object> getWhereParams() {
		return whereParams;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
