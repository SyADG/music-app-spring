package com.musicapp.exceptions;

@SuppressWarnings("serial")
public class ArtistNotFoundException extends RuntimeException {
	public ArtistNotFoundException(Long id) {
		super("Could not find artist " + id);
	}
}
