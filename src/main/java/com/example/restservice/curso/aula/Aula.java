package com.example.restservice.curso.aula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.restservice.curso.Curso;
import com.example.restservice.curso.aula.validacoes.AulaValidacaoDefault1;
import com.example.restservice.curso.aula.validacoes.AulaValidacaoDefault2;
import com.example.restservice.curso.aula.validacoes.AulaValidacaoPersist;
import com.example.restservice.curso.aula.validacoes.AulaValidacaoRemove;
import com.example.restservice.curso.aula.validacoes.AulaValidacaoUpdate;
import com.example.restservice.validacao.PreRemove;
import com.example.restservice.validacao.PreUpdate;
import com.example.restservice.validacao.CustomValidations;
import com.example.restservice.validacao.PrePersist;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@CustomValidations({AulaValidacaoDefault1.class, AulaValidacaoDefault2.class})
@CustomValidations(value = AulaValidacaoPersist.class, groups = PrePersist.class)
@CustomValidations(value = AulaValidacaoUpdate.class, groups = PreUpdate.class)
@CustomValidations(value = AulaValidacaoRemove.class, groups = PreRemove.class)
public class Aula {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "curso_id")
	@JsonIgnore
	private Curso curso;
	
    @NotNull
    @Column(nullable = false)
    @ToString.Include
    private String nome;
    
}
