package com.musicapp.exceptions;

@SuppressWarnings("serial")
public class SongNotFoundException extends RuntimeException {
	public SongNotFoundException(Long id) {
		super("Could not find song " + id);
	}
}
