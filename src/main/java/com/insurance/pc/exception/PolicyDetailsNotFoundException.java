package com.insurance.pc.exception;

public class PolicyDetailsNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4495215885144913633L;

	public PolicyDetailsNotFoundException(String msg){
		super(msg);
	}
}
