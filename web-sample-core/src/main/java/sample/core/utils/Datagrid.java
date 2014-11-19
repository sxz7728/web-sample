package sample.core.utils;

import java.util.List;

public class Datagrid<T> {
	private List<T> data;

	private Integer count;

	public Datagrid(List<T> data, Integer count) {
		this.data = data;
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
