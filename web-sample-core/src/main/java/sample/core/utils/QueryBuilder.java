package sample.core.utils;

import java.util.List;

import com.google.common.collect.Lists;

public class QueryBuilder {

	private StringBuilder column = new StringBuilder();

	private StringBuilder where = new StringBuilder();

	private StringBuilder order = new StringBuilder();

	private List<Object> params = Lists.newArrayList();

	private int start = 0;

	private int length = 0;

	public QueryBuilder() {
	}

	public QueryBuilder(int start, int length) {
		this.start = start;
		this.length = length;
	}

	public QueryBuilder addColumn(String str) {
		return this;
	}

	public QueryBuilder addColumn(String str, Object obj) {
		return this;
	}

	public QueryBuilder addWhere(String str, Object... objs) {
		return this;
	}

	public QueryBuilder addOrder(String str) {
		return this;
	}

	public String getColumn() {
		return column.toString();
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

	public List<Object> getParams() {
		return params;
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
