package com.example.restservice.curso.validacoes;

import static java.util.Optional.ofNullable;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.restservice.curso.Curso;
import com.example.restservice.curso.CursoRepository;
import com.example.restservice.validacao.Validation;

@Component
public class CursoValidaNome implements Validation<Curso> {

	@Autowired
	CursoRepository cursoRepository;
	
	@Autowired
	EntityManager em;
	
	@Override
	public boolean isValid(Curso curso) throws Exception {
		
		Long id = ofNullable(curso).map(Curso::getId).orElse(0L);
		
		List<Curso> resultList = em.createQuery("from Curso where id != :id and titulo like :titulo", Curso.class)
			.setParameter("id", id)
			.setParameter("titulo", curso.getTitulo())
			.getResultList();
		
		System.out.println("CursoValidaNome: "+curso+", outros: " + resultList.size());
//		if (resultList.size()>0) 
//			return false;
		
		return true;
	}

	@Override
	public String message() {
		return "Não pode ter dois cursos com o mesmo nome";
	}

}
