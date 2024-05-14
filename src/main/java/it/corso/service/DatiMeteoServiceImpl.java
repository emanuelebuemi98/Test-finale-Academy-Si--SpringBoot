package it.corso.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.DatiMeteoDao;
import it.corso.dto.DatiMeteoDto;
import it.corso.model.DatiMeteo;

@Service
public class DatiMeteoServiceImpl implements DatiMeteoService {
	
	@Autowired
	DatiMeteoDao datiMeteoDao;

	@Override
	public void salvaDatiMeteo(DatiMeteoDto datiMeteoDto) {
        DatiMeteo datiMeteo = new DatiMeteo();
        datiMeteo.setCitta(datiMeteoDto.getCitta());
        datiMeteo.setTemperatura(datiMeteoDto.getTemperatura());
        datiMeteo.setUmidita(datiMeteoDto.getUmidita());
        datiMeteo.setDescrizione(datiMeteoDto.getDescrizione());
        datiMeteo.setPressione(datiMeteoDto.getPressione());
        datiMeteo.setVelocitaVento(datiMeteoDto.getVelocitaVento());
        datiMeteo.setDirezioneVento(datiMeteoDto.getDirezioneVento());
        datiMeteo.setVisibilita(datiMeteoDto.getVisibilita());
        
        datiMeteoDao.save(datiMeteo);
	}

}
