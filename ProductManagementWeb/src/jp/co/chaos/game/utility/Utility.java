package jp.co.chaos.game.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

public class Utility {

	private static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";

	public static String getHashPassword(String password) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
			byte[] digest = messageDigest.digest(password.getBytes());
			String hashPassword = DatatypeConverter.printHexBinary(digest);
			return hashPassword;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}

	public static Date getCurrentDate() {
		return new Date(currentTimeMillis());
	}

	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(currentTimeMillis());
	}

	public static String getCurrentDateString() {
		Date now = getCurrentDate();
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return format.format(now);
	}

	public static java.sql.Date parseToSqlDate(String string) {
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		try {
			return new java.sql.Date(format.parse(string).getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	public static String toString(Object object) {
		return object == null ? null : String.valueOf(object);
	}

	public static String toString(java.sql.Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return format.format(date);
	}

	public static Integer toInteger(String string) {
		return string == null ? null : Integer.parseInt(string);
	}

	public static boolean isEmpty(String string) {
		return string == null || string.length() == 0;
	}

	public static boolean isNotEmpty(String string) {
		return !isEmpty(string);
	}

}
