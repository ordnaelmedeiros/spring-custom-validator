package com.example.restservice.curso.aula.validacoes;

import com.example.restservice.curso.aula.Aula;
import com.example.restservice.validacao.Validation;

public class AulaValidacaoUpdate implements Validation<Aula> {

	@Override
	public boolean isValid(Aula aula) throws Exception {
		System.out.println("AulaValidacaoUpdate: " + aula);
		return true;
	}

	@Override
	public String message() {
		return "AulaValidacaoUpdate";
	}

}
