package com.accounts.util;

public class IBANGenerator {

	private final static int CHECK_DIGIT = 21;
	
	public static String generateIBAN() {
		long account = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		return "NL" + CHECK_DIGIT + "ABNA" + account;
	}
}
