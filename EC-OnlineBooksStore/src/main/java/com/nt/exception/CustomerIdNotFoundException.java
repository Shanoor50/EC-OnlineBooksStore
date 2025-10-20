package com.nt.exception;

public class CustomerIdNotFoundException extends RuntimeException{
	
	//It defines a unique version ID for your class when it is serializable
	private static final long serialVersionUID = 1L;

	
	public CustomerIdNotFoundException(String msg) {
		super(msg);
	}

}
