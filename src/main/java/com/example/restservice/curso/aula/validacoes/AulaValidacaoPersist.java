package com.example.restservice.curso.aula.validacoes;

import com.example.restservice.curso.aula.Aula;
import com.example.restservice.validacao.Validation;

public class AulaValidacaoPersist implements Validation<Aula> {

	@Override
	public boolean isValid(Aula aula) throws Exception {
		System.out.println("AulaValidacaoPersist: " + aula);
		return true;
	}

	@Override
	public String message() {
		return "AulaValidacaoPersist";
	}

}
