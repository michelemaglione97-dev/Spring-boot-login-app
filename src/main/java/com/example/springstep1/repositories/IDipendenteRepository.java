package com.example.springstep1.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.springstep1.entities.Dipendente;
import com.example.springstep1.entities.Persona;

public interface IDipendenteRepository extends JpaRepository<Dipendente, Integer>{

	// JPA è intelligente: anche se CF, Nome e Cognome sono in Persona.
	List<Dipendente> findByCf(String cf); //ho creato un metodo  sfruttando query method JPA(vedi su internet)
	List<Dipendente> findByNomeAndCognome(String nome, String cognome);
	List<Dipendente> findByAccountEmail(String email); // Property Traversal cosi lo prende tramite la chiave esterna
	
	@Query("SELECT d FROM Dipendente d WHERE d.stipendio > (SELECT AVG(d2.stipendio) FROM Dipendente d2)")
	List<Dipendente> findStipendiSopraMedia();
	
	List<Dipendente> findByDataDiNascitaBetween(LocalDate inizio, LocalDate fine);
	//SELECT * FROM dipendente 
	//WHERE data_di_nascita >= inizio AND data_di_nascita <= fine
	
	List<Dipendente> findByStipendioGreaterThanEqual(Double stipendio); //SELECT * FROM dipendente WHERE stipendio >= ?;
	List<Dipendente> findByAccountPermessoId(Integer idPermesso);
}
