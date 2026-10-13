package com.example.springstep1.entities;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name="persone") //mi sserve altrimenti mi prende il nome al singolare
@MappedSuperclass
@Data  //fa tutto sui setter getter hascode equals
@NoArgsConstructor

public class Persona {
	

//	private int id; lo gestisco in dipendente

	
	@Column(name="nome", nullable = false ) // se è lo stesso nella tabella posso anche non mettere il name
	private String nome;
	
	
	@Column(name="cognome", nullable = false)//lo rendo NOT NULL,obbligatorio
	private String cognome;
	
	
	@Column(name="codice_fiscale", nullable = false, unique = true)
	private String cf;

	
	@Column(name="data_di_nascita", nullable = false)
	private LocalDate dataDiNascita;
}
