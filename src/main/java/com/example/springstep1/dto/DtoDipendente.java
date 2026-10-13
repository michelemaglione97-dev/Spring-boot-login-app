package com.example.springstep1.dto;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.example.springstep1.entities.Permesso;
import com.example.springstep1.entities.Ruolo;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DtoDipendente {
	
	private Integer id;
	
	@NotBlank(message="Campo obbligatorio!")
    @Size(min=3, max=20, message="Numero caratteri tra 3-20!")
    private String nome;
	
    @NotBlank(message="Campo obbligatorio!")
    @Size(min=3, max=20, message="Numero caratteri tra 3-20!")
    private String cognome;
	
    @NotBlank(message="Campo obbligatorio!")
    @Pattern(regexp="^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$", message="Codice Fiscale non valido!")
    private String cf;

    @NotNull(message="Campo obbligatorio!")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataDiNascita;

    @NotNull(message = "Campo obbligatorio!")
    private Character sesso; 

    @NotNull(message="Campo obbligatorio!")
    private Double stipendio;
    
    @NotNull(message="Campo obbligatorio!")

	private Ruolo tiporuolo;
	
    @NotNull(message="Campo obbligatorio!")
    @Future(message="La data deve essere successiva alla data odierna!")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataDiAss;
	
    private Integer idAccount;
    
    @NotBlank(message = "Username obbligatorio!")
    @Size(min = 3, message = "Minimo 3 caratteri")
    private String username;

    @Pattern(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message="Email non valida!")
    @NotBlank(message = "Email obbligatoria!")
    private String email;

    @NotBlank(message = "Password obbligatoria!")
    @Size(min = 3, message = "Minimo 3 caratteri")
    private String passwr;
    
    private Integer idPermesso;
    
    @NotNull(message="Campo obbligatorio!")
	private Permesso tipopermesso;
}
	
	
	
	
	


