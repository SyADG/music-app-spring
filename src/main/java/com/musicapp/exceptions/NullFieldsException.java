package com.musicapp.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NullFieldsException extends Exception{
	
	public NullFieldsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NullFieldsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NullFieldsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -8707565753431788707L;

	public NullFieldsException(String message) {
		super(message);
	}
}
