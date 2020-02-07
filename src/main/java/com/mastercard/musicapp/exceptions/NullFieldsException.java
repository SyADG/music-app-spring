package com.mastercard.musicapp.exceptions;

@SuppressWarnings("serial")
public class NullFieldsException extends RuntimeException{
	public NullFieldsException() {
		super("[ERROR] Bad Request\nFields must not be empty");
	}
}
