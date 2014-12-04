package sample.core.utils;

public class ColumnUtils {
	public static final String DICT_VALUE = " (select t1.dictValue from SysDict t1 where t1.dictKey = {0} and t1.dictType = '{1}') as {2} ";

	public static final String USER_NAME = " (select t1.userName from SysUser t1 where t1.id = {0}) as {1} ";

	public static String dictValue(String column, String alias, String dictType) {
		return Utilities.format(DICT_VALUE, column, dictType, alias);
	}

	public static String userName(String column, String alias) {
		return Utilities.format(USER_NAME, column, alias);
	}
}
