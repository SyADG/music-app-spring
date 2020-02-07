package com.mastercard.musicapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class SongErrorAdvice {
	private ArtistErrorAdvice artistErrorAdvice;
	
	@ResponseBody
	@ExceptionHandler(SongNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String songNotFoundHandler(SongNotFoundException ex) {
		log.error("[ERROR 404] Song not found");
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(NullFieldsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String artistNullFieldsHandler(NullFieldsException e) {
		return artistErrorAdvice.artistNullFieldsHandler(e);
	}
}
