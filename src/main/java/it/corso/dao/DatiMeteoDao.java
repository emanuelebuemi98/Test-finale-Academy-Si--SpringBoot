package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.DatiMeteo;

public interface DatiMeteoDao extends CrudRepository<DatiMeteo, Integer>{

}
