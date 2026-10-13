package com.example.springstep1.exceptions;
import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.ui.Model;
//"Questa classe osserva tutti i Controller". È un componente centralizzato che intercetta le eccezioni lanciate/generate ovunque nel progetto. 
//Invece di scrivere try-catch in ogni metodo del Controller, 
//scrivi la logica qui una volta sola.
//CENTRALIZZA LA GESTIONE DEGLI ERRORI
@ControllerAdvice 
public class GlobalExceptionHandler {
	
	// centralizzo il controllo delle eccezioni
	
	

	@ExceptionHandler(IllegalArgumentException.class)
	public String handlerIllegalArgumentException(IllegalArgumentException e,Model model)
	{
		model.addAttribute("msg","Codice Fiscale già Presente!" + e.getMessage());
		return "home";
		
			
	}
	
	//SPECIFICO QUALE TIPO DI ECCEZIONE GESTIRE
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public String handlerIllegalArgumentException(SQLIntegrityConstraintViolationException e,Model model)
	{
		model.addAttribute("msg","Attenzione: dato/i già presenti nel sistema.Controllare Codice Fiscale, Email, Username" + e.getMessage());
		return "home";
		
			
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public String handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, Model model)
	{
	model.addAttribute("msg","Dipendente da eliminare non presente "+e.getMessage());
	return "home";
	}
	
	
	

}
