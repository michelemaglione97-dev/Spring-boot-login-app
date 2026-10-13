package com.example.springstep1.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.springstep1.dto.DtoDipendente;
import com.example.springstep1.entities.Account;
import com.example.springstep1.entities.Dipendente;
import com.example.springstep1.entities.Permessi;
import com.example.springstep1.entities.Permesso;
import com.example.springstep1.entities.Ruolo;
import com.example.springstep1.repositories.IDipendenteRepository;

import jakarta.transaction.Transactional;

@Service
 public class DipendenteService {
	
    private final IDipendenteRepository dipendenteRepo; 
    
  //inietto la dipendenza creando un costruttore
    public DipendenteService(IDipendenteRepository dipendenteRepo) {
        this.dipendenteRepo = dipendenteRepo;
    }
	
	//UNICO METODO INSER E MODIFICA
    @Transactional //solo se vengono fatte tutte le operazioni me le salva nel database
    public DtoDipendente saveOmodifica(DtoDipendente dto) {
       
        Account acc = new Account();
        // Se idAccount non è null (arriva dal form modifica)
        if (dto.getIdAccount() != null) acc.setId(dto.getIdAccount());
        acc.setUsername(dto.getUsername());
        acc.setEmail(dto.getEmail());
        acc.setPasswr(dto.getPasswr());
        
     // Creiamo l'oggetto Permessi "al volo" passandogli solo l'ID
        Permessi p = new Permessi();
        if (dto.getTipopermesso() == Permesso.ADMIN) {
            p.setId(1); // ID 1 nel data.sql
        } else {
            p.setId(2); // ID 2 nel data.sql
        }
        acc.setPermesso(p); // Colleghiamo l'account al permesso tramite ID

     
        Dipendente dip = new Dipendente();
        // Se id non è null (arriva dal form modifica),
        if (dto.getId() != null) dip.setId(dto.getId());
        dip.setNome(dto.getNome());
        dip.setCognome(dto.getCognome());
        dip.setCf(dto.getCf());
        dip.setSesso(dto.getSesso());
        dip.setStipendio(dto.getStipendio());
        dip.setTiporuolo(dto.getTiporuolo());
        dip.setDataDiAss(dto.getDataDiAss());
        dip.setDataDiNascita(dto.getDataDiNascita());

        dip.setAccount(acc);

        // SALVIAMO (grazie al CascadeType nelle Entity, salva entrambe le tabelle)
        Dipendente salvato = dipendenteRepo.save(dip);
        
        // Aggiorniamo il DTO con l'ID per sicurezza e lo restituiamo
        dto.setId(salvato.getId());
        return dto;
    }
	
    public List<DtoDipendente> selectAll() {
        return dipendenteRepo.findAll().stream()
                .map(this::entityToDto) 
                .toList();
    }
	
    public void deletebyId(Integer id) {
        // Grazie al CascadeType.REMOVE nelle tue Entity, 
        // cancellando il dipendente sparirà anche l'account collegato!
        dipendenteRepo.deleteById(id);
    }
    
	
	public List<DtoDipendente> findbyCf(String cf) {
        return dipendenteRepo.findByCf(cf).stream()
                .map(this::entityToDto)
                .toList();
    }
	
	public List<DtoDipendente> findbyNomeCognome(String nome, String cognome) {
	    // Il repository trova le Entity, noi le trasformiamo in DTO
	    return dipendenteRepo.findByNomeAndCognome(nome, cognome).stream()
	            .map(this::entityToDto)
	            .toList();
	
	}
	
	public DtoDipendente findById(Integer id) {
        Dipendente dip = dipendenteRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dipendente non trovato!"));
        return entityToDto(dip);
    }
	
	private DtoDipendente entityToDto(Dipendente dip) {
        DtoDipendente dto = new DtoDipendente();
       
        dto.setId(dip.getId());
        dto.setNome(dip.getNome());
        dto.setCognome(dip.getCognome());
        dto.setCf(dip.getCf());
        dto.setSesso(dip.getSesso());
        dto.setStipendio(dip.getStipendio());
        dto.setTiporuolo(dip.getTiporuolo());
        dto.setDataDiAss(dip.getDataDiAss());
        dto.setDataDiNascita(dip.getDataDiNascita());

       
        if (dip.getAccount() != null) {
            dto.setIdAccount(dip.getAccount().getId());
            dto.setUsername(dip.getAccount().getUsername());
            dto.setEmail(dip.getAccount().getEmail());
            dto.setPasswr(dip.getAccount().getPasswr());
            
            
            if (dip.getAccount().getPermesso() != null) {
                dto.setIdPermesso(dip.getAccount().getPermesso().getId());
                dto.setTipopermesso(dip.getAccount().getPermesso().getTipopermesso());
            }
        }
        return dto;
    }


	public List<DtoDipendente> findbyEmail(String email) {
        return dipendenteRepo.findByAccountEmail(email).stream()
                .map(this::entityToDto)
                .toList();
    }
	

	public List<DtoDipendente> cercaSopraMedia() {
	    return dipendenteRepo.findStipendiSopraMedia().stream()
	            .map(this::entityToDto)
	            .toList();
	}

	public List<DtoDipendente> cercaPerFascia3040() {
	    LocalDate oggi = LocalDate.now();
	    

	    LocalDate dataNascita40 = oggi.minusYears(40); // Il più anziano ammesso
	    LocalDate dataNascita30 = oggi.minusYears(30); // Il più giovane ammesso

	    return dipendenteRepo.findByDataDiNascitaBetween(dataNascita40, dataNascita30)
	            .stream()
	            .map(this::entityToDto)
	            .toList();
	}

	public List<DtoDipendente> cercaPerStipendioMaggiore(Double stipendio) {

	    return dipendenteRepo.findByStipendioGreaterThanEqual(stipendio).stream()
	            .map(this::entityToDto)
	            .toList();
	}


	public List<DtoDipendente> cercaPerIdPermesso(Integer idPermesso) {
	    return dipendenteRepo.findByAccountPermessoId(idPermesso).stream()
	            .map(this::entityToDto)
	            .toList();
	}



}
	
	
	
	
	
	
	
	
	
