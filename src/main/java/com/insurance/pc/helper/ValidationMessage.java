package com.insurance.pc.helper;

public class ValidationMessage {

	public static class General {
		public static final String PHONE_REGEX = "^[09]{2}[0-9]{9}$";
		public static final String NATIONAL_ID_REGEX = "^[1-9]{1}[0-9]{10}$";
	}

	public static class Address {
		public static final String ADDRESS_CLIENT_ID_NOT_EMPTY = "Address student id can not be empty";
		public static final String ADDRESS_CLIENT_ID_NOT_NULL = "Address student id can not be null";

	}

}
