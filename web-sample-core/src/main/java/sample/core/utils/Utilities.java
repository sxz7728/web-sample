package sample.core.utils;

import java.text.MessageFormat;

public class Utilities {
	public static String format(String str, Object... args) {
		return MessageFormat.format(str.replaceAll("'", "''"), args);
	}
}
