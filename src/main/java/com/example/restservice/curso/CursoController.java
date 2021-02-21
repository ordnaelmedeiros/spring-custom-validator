package com.example.restservice.curso;

import static java.util.Optional.ofNullable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.SneakyThrows;

@RestController
@RequestMapping(
	path = "/curso",
	consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE
)
public class CursoController {
	
	@Autowired private CursoRepository cursoRepository;

	@GetMapping("/{id}")
    public ResponseEntity<Curso> get(@PathVariable("id") Long id) {
		return new ResponseEntity<>(cursoRepository.findById(id).orElse(null), HttpStatus.OK);
    }
	
	@GetMapping
    public ResponseEntity<List<Curso>> tudo() {
		return new ResponseEntity<>(cursoRepository.findAll(), HttpStatus.OK);
    }
	
	@PostMapping
	public ResponseEntity<Curso> post(@RequestBody Curso curso) {
		save(curso);
		return new ResponseEntity<Curso>(curso , HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Curso> put(@RequestBody Curso curso) {
		save(curso);
		return new ResponseEntity<Curso>(curso , HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Curso> delete(@PathVariable("id") Long id) {
		cursoRepository.delete(cursoRepository.findById(id).get());
		return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@SneakyThrows
	private void save(Curso curso) {
		ofNullable(curso)
			.map(Curso::getAulas)
			.orElse(new ArrayList<>())
				.stream()
				.forEach(aula -> aula.setCurso(curso));
		cursoRepository.save(curso);
		//Thread.sleep(3000);
	}
	
	
	
	
}
