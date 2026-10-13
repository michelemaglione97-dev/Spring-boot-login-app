package com.example.springstep1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Table(name="accounts")
@Entity
@Data
public class Account {
	
	
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="username", nullable = false,unique = true)
	private String username; //indice unico
	
	@Column(name="email", nullable = false,unique = true)
	private String email; //indice unico
	
	@Column(name="password", nullable = false)
	private String passwr;
	
	
	@OneToOne(mappedBy = "account", fetch = FetchType.EAGER) //collego Account al dipendente tramite idAccount
	private Dipendente dipendente;
	
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="id_permesso", nullable = false, foreignKey = @ForeignKey(name="fk_account-permessi"))
	private Permessi permesso;

}
