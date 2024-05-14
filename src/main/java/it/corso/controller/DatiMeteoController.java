package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;

import it.corso.dto.DatiMeteoDto;
import it.corso.service.DatiMeteoService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("meteo")
public class DatiMeteoController {

	@Autowired
	DatiMeteoService datiMeteoService;
	
    @POST
    @Path("/salva")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Response salvaDatiMeteo(DatiMeteoDto datiMeteoDto) {
        try {
            datiMeteoService.salvaDatiMeteo(datiMeteoDto);
            return Response.ok("Dati meteo salvati con successo").build();
        } catch (Exception e) {
        	return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
