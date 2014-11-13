package sample.core.utils;

import org.apache.commons.lang.StringUtils;

public class DictUtils {
	public static final String YES = "1";

	public static final String NO = "0";

	public static boolean getYesNo(String str) {
		return StringUtils.equals(str, YES);
	}

	public static String getYesNo(boolean b) {
		return b ? YES : NO;
	}
	
	// user type
	public static final String USER_TYPE = "01";
	
	public static final String USER_TYPE_ADMIN = "01";
}
