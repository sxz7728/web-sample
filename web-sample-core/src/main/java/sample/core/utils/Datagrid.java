package sample.core.utils;

import java.util.List;
import java.util.Map;

public class Datagrid {
	private List<Map<String, ?>> data;

	private Integer count;

	public List<Map<String, ?>> getData() {
		return data;
	}

	public void setData(List<Map<String, ?>> data) {
		this.data = data;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
