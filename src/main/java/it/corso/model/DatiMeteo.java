package it.corso.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "datimeteo")
public class DatiMeteo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
	
	@Column(name = "citta")
    private String citta;

	@Column(name = "temperatura")
    private String temperatura;
	
	@Column(name = "umidita")
	private String umidita;

	@Column(name = "descrizione")
    private String descrizione;
	
	@Column(name = "pressione")
    private String pressione;
    
	@Column(name = "velocita_vento")
    private String velocitaVento;
	
	@Column(name = "direzione_vento")
    private String direzioneVento;
	
	@Column(name = "visibilita")
    private String visibilita;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_id_u", referencedColumnName = "id")
    private Utente utente;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public String getUmidita() {
		return umidita;
	}

	public void setUmidita(String umidita) {
		this.umidita = umidita;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getPressione() {
		return pressione;
	}

	public void setPressione(String pressione) {
		this.pressione = pressione;
	}

	public String getVelocitaVento() {
		return velocitaVento;
	}

	public void setVelocitaVento(String velocitaVento) {
		this.velocitaVento = velocitaVento;
	}

	public String getDirezioneVento() {
		return direzioneVento;
	}

	public void setDirezioneVento(String direzioneVento) {
		this.direzioneVento = direzioneVento;
	}

	public String getVisibilita() {
		return visibilita;
	}

	public void setVisibilita(String visibilita) {
		this.visibilita = visibilita;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

}
