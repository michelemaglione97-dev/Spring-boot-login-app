package com.example.springstep1.controller;
import java.util.List;
import com.example.springstep1.utility.Validation;
import org.springframework.stereotype.Controller; //annotation che decori questa pagina come controller
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springstep1.dto.DtoDipendente;
import com.example.springstep1.entities.Dipendente;

import com.example.springstep1.services.DipendenteService;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
//indichiamo a spring che questa classe è un componente di tipo controller MVC
//ci permette di gestire richieste HTTPspecifiche e restituisce risposte html dinamiche
//questo restituisce nomi di file HTML (solitamente processati da un motore come Thymeleaf)->E' un
//Un Template Engine (motore di template) è un software progettato per unire modelli di pagine (template) 
//con dati reali per generare documenti finiti, solitamente pagine HTML. Serve a trasformare un file HTML statico in una pagina 
//dinamica che mostra i dati reali provenienti dal database.
@Controller
public class PageController {
	//spring crea il bean PageController
	//metodi per gestire delle richieste
	//richiamo la home creata tramite una stringa
	
	//costruttore che lega il service per utilizzare i suoi metodi(COSTRUISCO LA DIPENDENZA)
//	La Dependency Injection (DI) è un "pattern" (un modo di progettare il codice) in cui 
//	un oggetto non crea da solo gli altri oggetti di cui ha bisogno per funzionare, ma li riceve dall'esterno.
//    Invece di andare a prenderti gli strumenti che ti servono, qualcuno (nel nostro caso Spring) te li consegna già pronti sulla scrivania.
	
	Validation val=new Validation();
	
	private final DipendenteService dipService;
	//inietto la dipendenza
	public PageController(DipendenteService dipService) {
		
		this.dipService=dipService;
		
		
	}
	
	@GetMapping("/")
	public String root() 
	{
	return "redirect:home";
		
	}
	
	@GetMapping("/home")
	public String home() 
	{
	return "home"; //richiama la view ossia la pagina home.html
		
	}
	
	
	@GetMapping("/new") //mappo la richiesta in un endpoint new
	public String home(Model model) //passiamo degli attributi alla pagina html tramite il model
	{
		
		DtoDipendente d=new DtoDipendente();
	
		//persona è un etichetta non centra con la pagina form
		model.addAttribute("dipendente", d);//passo i dati dal controller a una vista specifica(form)
		//passo al form.html degli oggetti,così possiamo inizializzarlo in html
	
		return "form"; //richiama la view ossia la pagina form.html
		
	}
	
	
	@PostMapping("/new") //lo chiamo con lostessto nome ma ha metodi diversi perchè è post(dopo l'inserimento)
	public String create(@Valid @ModelAttribute("dipendente") DtoDipendente d, BindingResult result, Model model) //recupero la persona(etichetta "form") che ho inserito nel form registrati
	{
		val.validaEta(d, result);
	    
	    
		val.validaStipendio(d, result);
	    
		//controllo se ci sono errori
	    if(result.hasErrors())
	    {
	    	return "form";
	    	
	    }	
			
	DtoDipendente dnew=dipService.saveOmodifica(d);
	model.addAttribute("msg", "Benvenuto: ");
	model.addAttribute("dip", dnew);
	
	return "home";
		
	}
	
	@GetMapping("/gestione") //metodo che ritorna una lista
	public String selectAll(Model model) 
	{
		
		model.addAttribute("items", dipService.selectAll());
		return "gestione";
	}
	

	@GetMapping("/delete")
	public String deleteId(@RequestParam("id") Integer id) {
		
		System.out.println("id-----" +id);
		return "home";
	}
	
	/*
	@DeleteMapping("/delete")
	public String deletebyId(@RequestParam("id") Integer id, Model model) {
		
		//System.out.println("delete id-----" +id);
		persService.deletebyId(id);
		model.addAttribute("msg", "Persona eliminata correttamente!");
		
		return "home";
	}
	*/
	
	@DeleteMapping("/{id}/delete")
	public String deletebyId(@PathVariable Integer id, Model model) {
		
		//System.out.println("delete id-----" +id);
		dipService.deletebyId(id);
		model.addAttribute("msg", "Dipendente eliminato correttamente!");
		
		return "form";
	}
	
	
	@GetMapping("/ricerca")
	public String ricercaByCf(@RequestParam("cf") String cf, Model model) {
	   
	 
		model.addAttribute("items", dipService.findbyCf(cf));
	    return "gestione"; 
	}
	
	
	@GetMapping("/ricercaNomeCognome")
	public String ricercaPerNome(@RequestParam("nome") String n, 
	                             @RequestParam("cognome") String c, 
	                             Model model) {
	    
		model.addAttribute("items", dipService.findbyNomeCognome(n, c));
	    
	    return "gestione"; 
	}
	
	@GetMapping("/ricercaEmail")
	public String ricercaByEmail(@RequestParam("email") String email, Model model) {
	   
	 
		model.addAttribute("items", dipService.findbyEmail(email));
	    return "gestione"; 
	}
	
	@GetMapping("/sopraMedia")
	public String sopraMedia(Model model) {
	   
	 
		model.addAttribute("items", dipService.cercaSopraMedia());
		model.addAttribute("msg", "Dipendenti con stipendio superiore alla media: ");
	    return "gestione"; 
	}
	
	@GetMapping("/fascia3040")
	public String fascia3040(Model model) {
	    model.addAttribute("items", dipService.cercaPerFascia3040());
	    model.addAttribute("msg", " Dipendenti tra i 30 e i 40 anni: ");
	    model.addAttribute("mostraEta", true);
	    return "gestione"; 
	}
	
	
	@GetMapping("/ricercaStipendio")
	public String ricercaStipendio(@RequestParam("cifra") Double stipendio, Model model) {
	    model.addAttribute("items", dipService.cercaPerStipendioMaggiore(stipendio));
	    model.addAttribute("msg", "Dipendenti con stipendio superiore a " + stipendio + "€ ");
	    return "gestione"; 
	}
	
	@GetMapping("/filtraPermesso")
	public String filtraPerIdPermesso(@RequestParam("idPermesso") Integer idPermesso, Model model) {
	    // Usiamo il nuovo metodo del service
	
	    
	    model.addAttribute("items", dipService.cercaPerIdPermesso(idPermesso));
	    
	    
	    return "gestione"; // Torna alla tua tabella
	}
	
	@GetMapping("/modifica")
	public String modifica(@RequestParam("id") Integer id, Model model) {
	 
	    model.addAttribute("dipendente", dipService.findById(id));
        model.addAttribute("azione", "Modifica");
        model.addAttribute("testoBottone", "MODIFICA");
        return "form"; 
	}
	
	@PutMapping("/modifica")
	public String updateDipendente( @Validated @ ModelAttribute("dipendente") DtoDipendente d,BindingResult result, Model model) {
	    
         val.validaEta(d, result);
	    
	    
		val.validaStipendio(d, result);
	    
		if(result.hasErrors()) {
	        
	        model.addAttribute("azione", "Modifica");
	        model.addAttribute("testoBottone", "MODIFICA");
	        return "form"; 
	    }
	    
	    dipService.saveOmodifica(d);
	    model.addAttribute("items", dipService.selectAll());
	    
	  
	    model.addAttribute("msg", "Il Dipendente è stato modificato correttamente " + "Id:" + d.getId());
	    
	    
	    return "gestione"; 
	}
	
	
}
