package it.corso.service;

import it.corso.dto.UtenteLoginRequestDto;
import it.corso.dto.UtenteRegistrazioneDto;
import it.corso.model.Utente;

public interface UtenteService {
	
	 void utenteRegistration(UtenteRegistrazioneDto registrazioneUtenteDto); 
	 boolean existsUtenteByEmail(String email); 
	 boolean loginUtente(UtenteLoginRequestDto utenteLoginDto);
	 Utente getUtenteByEmail(String email);

}
