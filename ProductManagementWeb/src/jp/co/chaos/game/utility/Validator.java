package jp.co.chaos.game.utility;

import java.text.SimpleDateFormat;

public class Validator {

	private static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";

	public static <E extends Number> boolean isEmpty(E e) {
		return e == null;
	}

	public static boolean isEmpty(Object object) {
		return object == null;
	}

	public static boolean isEmpty(String string) {
		return string == null || string.length() == 0;
	}

	public static <E extends Number> boolean isNotEmpty(E e) {
		return !isEmpty(e);
	}

	public static boolean isNotEmpty(Object object) {
		return !isEmpty(object);
	}

	public static boolean isNotEmpty(String e) {
		return !isEmpty(e);
	}

	public static boolean required(String string) {
		return isNotEmpty(string);
	}

	public static boolean length(String string, int length) {
		return string == null || string.length() == length;
	}

	public static boolean maxLength(String string, int maxLength) {
		return string == null || string.length() <= maxLength;
	}

	public static boolean alphanumeric(String string) {
		return string == null || string.matches("\\w*");
	}

	public static boolean numeric(String string) {
		return numeric(string, Integer.MAX_VALUE);
	}

	public static boolean numeric(String string, int maxLength) {
		return numeric(string, 0, maxLength);
	}

	public static boolean numeric(String string, int minLength, int maxLength) {
		return string == null || string.matches("[\\d\\-]{" + minLength + "," + maxLength + "}");
	}

	public static boolean range(String string, int min, int max) {
		if (string == null) {
			return true;
		}
		try {
			int value = Integer.parseInt(string);
			return min <= value && value <= max;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean date(String string) {
		if (string == null) {
			return true;
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			format.setLenient(false);
			format.parse(string);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
