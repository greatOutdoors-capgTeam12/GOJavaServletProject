package com.capgemini.go.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	public static boolean validatePhoneNumber(Long number) {
		String s = Long.toString(number);
		s = s.trim();
		if (s.length() == 10) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean validateUserId(String userId) {
		if (userId != null && userId.matches("[A-Za-z0-9_]+")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValid(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	private static Pattern pattern;
	private static Matcher matcher;

	private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";

	public static boolean validatePassword(final String password) {

		pattern = Pattern.compile(PASSWORD_PATTERN);
		matcher = pattern.matcher(password);
		return matcher.matches();

	}

}
