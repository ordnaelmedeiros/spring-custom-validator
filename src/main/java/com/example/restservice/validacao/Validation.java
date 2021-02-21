package com.example.restservice.validacao;

public interface Validation<T> {

	boolean isValid(T value) throws Exception;
	String message();
	
}

