package com.example.springstep1.utility;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.example.springstep1.dto.DtoDipendente;
import com.example.springstep1.entities.Dipendente;
import com.example.springstep1.entities.Ruolo;

@Component
public class Validation {
	
	public void validaEta(DtoDipendente d, BindingResult result) {
        
        if (d.getDataDiNascita() != null) {
            int anni = Period.between(d.getDataDiNascita(), LocalDate.now()).getYears();
            if (anni < 18) {
                result.rejectValue("dataDiNascita", "error.eta", "Il dipendente deve essere maggiorenne!");
            }
            }
        }
	
	
	public void validaStipendio(DtoDipendente d, BindingResult result) {
       
        if (d.getTiporuolo() == null || d.getStipendio() == null) {
            return; //GESTITO DA NOT NULL
        }

        Ruolo ruolo = d.getTiporuolo();
        double stipendio = d.getStipendio();
        double min = 0;
        double max = 0;

        // Logica stipendi centralizzata
        switch (ruolo) {
            case PROGRAMMATORE: min = 1300; max = 1400; break;
            case PROJECT_MANAGER: min = 1400; max = 1550; break;
            case TEAM_LEADER: min = 1550; max = 1700; break;
            case TOP_MANAGER: min = 1700; max = 2000; break;
        }

        // 2. Verifica range
        if (stipendio < min || stipendio > max) {
            // Registra l'errore direttamente nel BindingResult
            result.rejectValue("stipendio", "error.stipendio", 
                "Per il ruolo " + ruolo + " lo stipendio deve essere tra " + min + "€ e " + max + "€");
        }
    }


}


