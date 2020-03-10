package com.mastercard.musicapp.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public String getToken() {
		return this.jwttoken;
	}

}