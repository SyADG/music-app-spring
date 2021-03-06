package com.musicapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ArtistErrorAdvice {

	@ResponseBody
	@ExceptionHandler(ArtistNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String artistNotFoundHandler(ArtistNotFoundException ex) {
		log.error("[ERROR 404] Artist not found");
		return ex.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(NullFieldsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String artistNullFieldsHandler(NullFieldsException e){
		log.error("[ERROR 400] Bad Request\nFields must not be empty");
		return e.getMessage();
	}
}
