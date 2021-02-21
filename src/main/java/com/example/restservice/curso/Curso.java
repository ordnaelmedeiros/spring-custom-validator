package com.example.restservice.curso;

import static javax.persistence.CascadeType.ALL;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.restservice.curso.aula.Aula;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
//@CustomValidations(CursoValidaNome.class)
//@CustomValidations(value = CursoValidaPersist.class, groups = PrePersist.class)

public class Curso {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
	
    @NotNull
    @Column(nullable = false)
    private String titulo;
    
    @OneToMany(mappedBy = "curso", cascade = ALL, orphanRemoval = true)
    @Singular
    private List<Aula> aulas;
    
}
