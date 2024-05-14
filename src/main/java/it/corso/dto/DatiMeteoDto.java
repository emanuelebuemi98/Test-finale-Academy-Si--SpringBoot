package it.corso.dto;

public class DatiMeteoDto {
	private int id;
    private String citta;
    private String temperatura;
	private String umidita;
    private String descrizione;
    private String pressione;
    private String velocitaVento;
    private String direzioneVento;
    private String visibilita;
    
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
    
}
