package com.ppp.Exception;

public class customExectionClass  extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public customExectionClass(String message) {
		super(message);
	}
	
	public customExectionClass(String message, Throwable cause) {
		super(message, cause);
	}

}
