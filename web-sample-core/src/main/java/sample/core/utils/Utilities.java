package sample.core.utils;

import java.text.MessageFormat;
import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

public class Utilities {
	public static String format(String str, Object... args) {
		return MessageFormat.format(str.replaceAll("'", "''"), args);
	}

	public static Collection<?> ifEmpty(Collection<?> collection, Object obj) {
		return CollectionUtils.isEmpty(collection) ? Lists.newArrayList(obj)
				: collection;
	}

	public static boolean getYesNo(String str) {
		return StringUtils.equals(str, DictUtils.YES);
	}

	public static String getYesNo(boolean b) {
		return b ? DictUtils.YES : DictUtils.NO;
	}
}
