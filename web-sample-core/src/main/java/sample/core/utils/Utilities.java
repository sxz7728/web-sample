package sample.core.utils;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;

public class Utilities {
	public static String format(String str, Object... args) {
		return MessageFormat.format(str.replaceAll("'", "''"), args);
	}

	public static List<?> ifEmpty(List<?> list, Object obj) {
		return CollectionUtils.isEmpty(list) ? Lists.newArrayList(obj) : list;
	}
}
