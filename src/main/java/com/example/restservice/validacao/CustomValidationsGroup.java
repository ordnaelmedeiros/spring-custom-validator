package com.example.restservice.validacao;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ FIELD, TYPE })
@Retention(RUNTIME)
@Documented
public @interface CustomValidationsGroup {

	CustomValidations[] value();
	
}
