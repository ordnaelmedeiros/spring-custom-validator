package com.example.restservice.curso.validacoes;

import com.example.restservice.curso.Curso;
import com.example.restservice.validacao.Validation;

public class CursoValidaPersist implements Validation<Curso> {

	@Override
	public boolean isValid(Curso curso) throws Exception {
		System.out.println("CursoValidaPersist: " + curso);
		return true;
	}

	@Override
	public String message() {
		return "CursoValidaPersist";
	}

}
