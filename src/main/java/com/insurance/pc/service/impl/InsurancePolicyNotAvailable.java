package com.insurance.pc.service.impl;

public class InsurancePolicyNotAvailable extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5230297088538052709L;

	public InsurancePolicyNotAvailable(String msg) {
		super(msg);
	}
}
