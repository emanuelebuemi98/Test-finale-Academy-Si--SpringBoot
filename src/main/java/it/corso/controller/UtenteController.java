package it.corso.controller;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.corso.dto.UtenteLoginRequestDto;
import it.corso.dto.UtenteLoginResponseDto;
import it.corso.dto.UtenteRegistrazioneDto;
import it.corso.model.Utente;
import it.corso.service.Blacklist;
import it.corso.service.UtenteService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("utente")
public class UtenteController {
	
	@Autowired
	UtenteService utenteService;
	
	@Autowired
	private Blacklist blacklist;

	@POST
	@Path("/reg")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response userRegistration(@Valid @RequestBody UtenteRegistrazioneDto utenteDto) {

		try {
			if (!Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}", utenteDto.getPassword())) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			if (utenteService.existsUtenteByEmail(utenteDto.getEmail())) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			utenteService.utenteRegistration(utenteDto);
			return Response.status(Response.Status.OK).build();

		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response userLogin(@RequestBody UtenteLoginRequestDto utenteLoginDto) {

		try {
			if (utenteService.loginUtente(utenteLoginDto)) {
				return Response.ok(generateToken(utenteLoginDto.getEmail())).build();
			}
			return Response.status(Response.Status.NOT_FOUND).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	// Metodo che genera un token JWT per l'utente con l'email specificata
	private UtenteLoginResponseDto generateToken(String email) {

		byte[] keySecret = "cusjshsvdthshaiana437882726bshsb2782ddd8282dw".getBytes();
		Key key = Keys.hmacShaKeyFor(keySecret);
		
		Utente infoUtente = utenteService.getUtenteByEmail(email);

		Map<String, Object> mappa = new HashMap<>();
		mappa.put("email", email);
		mappa.put("nome", infoUtente.getNome());
		mappa.put("cognome", infoUtente.getCognome());
		
		Instant now = Instant.now();
		Date creazioneData = Date.from(now);
		Date dataEnd = Date.from(now.plus(15, ChronoUnit.MINUTES));

		String jwtToken = Jwts.builder()
				.setClaims(mappa)
				.setIssuer("http//localhost:8080")
				.setIssuedAt(creazioneData)
				.setExpiration(dataEnd)
				.signWith(key)
				.compact(); 

		UtenteLoginResponseDto token = new UtenteLoginResponseDto();
		token.setToken(jwtToken);
		token.setTokenCreationTime(creazioneData);
		token.setTtl(dataEnd);

		return token;
	}
	
	@GET
	@Path("/logout")
	public Response userLogout (ContainerRequestContext requestContext) {
		try {
			
			String autorizzazioneHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
			String token = autorizzazioneHeader.substring("Bearer".length()).trim();
		
			blacklist.invalidateToken(token);
			return Response.status(Response.Status.OK).build();			
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
