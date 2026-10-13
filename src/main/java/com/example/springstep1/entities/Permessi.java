package com.example.springstep1.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="permessi")
@Data
public class Permessi {
	
	
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="tipo_permesso", nullable = false)
	@Enumerated(EnumType.STRING)
	private Permesso tipopermesso;

	
	@OneToMany(mappedBy = "permesso", fetch = FetchType.LAZY)
	private Set<Account> accounts; //molti accounts non solo uno percio'
}
