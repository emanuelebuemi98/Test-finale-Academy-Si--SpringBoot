package it.corso.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.UtenteDao;
import it.corso.dto.UtenteLoginRequestDto;
import it.corso.dto.UtenteRegistrazioneDto;
import it.corso.model.Utente;

@Service
public class UtenteServiceImpl implements UtenteService {
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	UtenteDao utenteDao;
	
	@Override
	public void utenteRegistration(UtenteRegistrazioneDto registrazioneUtenteDto) {
		Utente utente = new Utente();
		utente.setNome(registrazioneUtenteDto.getNome());
		utente.setCognome(registrazioneUtenteDto.getCognome());
		utente.setEmail(registrazioneUtenteDto.getEmail());
		String sha256hex = DigestUtils.sha256Hex(registrazioneUtenteDto.getPassword());
        utente.setPassword(sha256hex);
        utenteDao.save(utente);
	}
	
	@Override
	public boolean existsUtenteByEmail(String email) {
		return utenteDao.existsByEmail(email);
	}
	
	@Override
	public boolean loginUtente(UtenteLoginRequestDto utenteLoginDto) {
		
		Utente utente = new Utente();
		utente.setEmail(utenteLoginDto.getEmail());
		utente.setPassword(utenteLoginDto.getPassword());
		String sha256hex = DigestUtils.sha256Hex(utente.getPassword());
		Utente credenzialiUtente = utenteDao.findByEmailAndPassword(utente.getEmail(), sha256hex);
		return credenzialiUtente != null ? true : false;
	}
	
	@Override
	public Utente getUtenteByEmail(String email) {
		return utenteDao.findByEmail(email);
	}
	

}
