package sample.core.utils;

import org.apache.commons.lang.StringUtils;

public class DictUtils {
	public static final String YES = "1";

	public static final String NO = "0";

	// User
	public static final String USER_TYPE = "user_type";

	public static final String USER_TYPE_ADMIN = "01";
	
	
	public static boolean getYesNo(String str) {
		return StringUtils.equals(str, YES);
	}

	public static String getYesNo(boolean b) {
		return b ? YES : NO;
	}
	
	public String getYES() {
		return YES;
	}

	public String getNO() {
		return NO;
	}

	public String getUSER_TYPE() {
		return USER_TYPE;
	}

	public String getUSER_TYPE_ADMIN() {
		return USER_TYPE_ADMIN;
	}
}
