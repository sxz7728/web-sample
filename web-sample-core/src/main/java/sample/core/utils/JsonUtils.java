package sample.core.utils;

import com.google.gson.Gson;

public class JsonUtils {
	private static ThreadLocal<Gson> gson = new ThreadLocal<Gson>() {
		protected synchronized Gson initialValue() {
			return new Gson();
		}
	};

	public static String toJson(Object object) {
		return gson.get().toJson(object);
	}
}
