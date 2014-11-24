package sample.core.utils;

import java.text.MessageFormat;
import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

public class Utilities {
	public static final String DICT_COLUMN = " (select t1.dictValue from SysDict t1 where t1.dictKey = {0} and t1.dictType = '{1}') as {2} ";

	public static final String USER_COLUMN = " (select t1.userName from SysUser t1 where t1.id = {0}) as {1} ";

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

	public static String dictColumn(String column, String alias, String dictType) {
		return format(DICT_COLUMN, column, dictType, alias);
	}

	public static String userColumn(String column, String alias) {
		return format(USER_COLUMN, column, alias);
	}
}
