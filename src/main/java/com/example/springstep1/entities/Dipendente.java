package com.example.springstep1.entities;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="dipendenti")
@Data
@NoArgsConstructor
public class Dipendente extends Persona{
	
	
	@Id  //è un id a tutti gli effetti e autoincrement
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="stipendio", nullable = false)
	private Double stipendio;
	

	@Column(name="data_assunzione", nullable = false)
		private LocalDate dataDiAss;
	

	@Column(name="ruolo", nullable = false)
	@Enumerated(EnumType.STRING)
	private Ruolo tiporuolo;
	

	@Column(name = "sesso", length = 1)
	private Character sesso; 

	
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})//PERSIST SE CREO DIPENDENTE CREO ANCHE UTENTE,ELIMINA FA LO STESSO,MERGE PER LA MODIFICA
	@JoinColumn(name="id_Account", nullable = false, foreignKey = @ForeignKey(name="fk_dipendente_account"))// questo rappresenta il Constraint
	private Account account;    
	//associazione tra dipendente e account(1 a 1)
    //LAZY MI CARICA ACCOUNT SOLO DOPO AVER CARICATO DIPENDENTE(RITARDA),PER UNA QUESTIONE DI 'PESANTEZZA' 
	//EAGER MI CARICA ACCOUNT A MONTE
    
}
