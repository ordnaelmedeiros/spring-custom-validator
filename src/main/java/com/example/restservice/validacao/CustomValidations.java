package com.example.restservice.validacao;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD, TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CustomValidationsValidator.class)
@Documented
@Repeatable(CustomValidationsGroup.class)
public @interface CustomValidations {

	Class<? extends Validation<?>>[] value();
	
	String message() default "Inválido";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
