package com.example.restservice.curso;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.example.restservice.curso.aula.Aula;

@SpringBootTest(
	webEnvironment = WebEnvironment.DEFINED_PORT
)
public class CursoControllerTest {
	
	@Test
	public void testCrudCurso() {
		
		System.out.println("Teste gravando");
		
		var retorno = given()
			.contentType("application/json")
			.accept("application/json")
			.body(Curso.builder()
				.titulo("Curso 1")
				.aula(Aula.builder().nome("Aula 1").build())
				.aula(Aula.builder().nome("Aula 2").build())
			.build())
		.post("/curso")
		.then()
			.statusCode(201)
			.extract().as(Curso.class);
		
		System.out.println("Criado: ");
		printById(retorno.getId());
		
		System.out.println("Teste alterado");
		
		retorno.setTitulo("Curso 1 alterado");
		retorno.getAulas().remove(0);
		retorno.getAulas().get(0).setNome("Aula 2 alterado");
		retorno.getAulas().add(Aula.builder().nome("Aula 3").build());
		
		retorno = given()
				.contentType("application/json")
				.accept("application/json")
				.body(retorno)
			.put("/curso")
			.then()
				.statusCode(200)
				.body("id", is(retorno.getId().intValue()))
				.body("titulo", is(retorno.getTitulo()))
				.extract()
					.as(Curso.class);
		
		System.out.println("Alterado:");
		
		printById(retorno.getId());
		
		System.out.println("Teste removendo:");
		given()
			.contentType("application/json")
			.accept("application/json")
		.delete("/curso/"+retorno.getId())
		.then()
			.statusCode(200);
		
		assertTrue(true);
	}
	
	private void printById(Long id) {
		var v = given()
			.contentType("application/json")
			.accept("application/json")
		.get("/curso/"+id)
		.then()
			.statusCode(200)
			.extract().asString();
		System.out.println(v);
	}
	
}
