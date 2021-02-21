package com.example.restservice.validacao;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomValidationsValidator implements ConstraintValidator<CustomValidations, Object> {
	
	public static final String MSG_ERRO_INESPERADO = "Erro inesperado";

	private Class<? extends Validation<?>>[] validacoes;
	
	@Override
	public void initialize(CustomValidations constraintAnnotation) {
		validacoes = constraintAnnotation.value();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		//System.out.println("CustomValidationsValidator: " + this);
		//System.out.println("CustomValidationsValidator.context: " + context);
		
		context.disableDefaultConstraintViolation();
		
		boolean isValid = true;
		
		for (Class<? extends Validation> valida : validacoes) {
			try {
				
				Optional<Validation> optValidation = getValidation(valida);
				if (optValidation.isPresent()) {
					var validation = optValidation.get();
					if (!validation.isValid(value)) {
						isValid = false;
						context.buildConstraintViolationWithTemplate(validation.message()).addConstraintViolation();
					}
				}
				
			} catch (Exception e) {
				isValid = false;
				context.buildConstraintViolationWithTemplate(MSG_ERRO_INESPERADO).addConstraintViolation();
				e.printStackTrace();
			}
		}
		
		return isValid;
		
	}

	@SuppressWarnings({ "rawtypes" })
	private Optional<Validation> getValidation(Class<? extends Validation> value) {
		return getValidationByContext(value).or(() -> getValidationByIntance(value));
	}
	
	@SuppressWarnings({ "rawtypes" })
	private Optional<Validation> getValidationByContext(Class<? extends Validation> value) {
		try {
			return ofNullable(StaticContextAccessor.getBean(value));
		} catch (Exception e) {
			return empty();
		}
	}
	
	@SuppressWarnings({ "rawtypes" })
	private Optional<Validation> getValidationByIntance(Class<? extends Validation> value) {
		try {
			return ofNullable(value.getDeclaredConstructor().newInstance());
		} catch (Exception e) {
			return empty();
		}
	}
	
}
