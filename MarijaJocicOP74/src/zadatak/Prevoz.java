package zadatak;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Prevoz {
	
	private int id;
	private double cena;
	private LocalDate datumPocetka;
	private LocalDate datumKraja;
	private String registracijaVozila;
	private String imeNarucioca;
	private String prezimeNarucioca;
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
	
	
	
	public Prevoz() {
		
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public double getCena() {
		return cena;
	}



	public void setCena(double cena) {
		this.cena = cena;
	}



	public LocalDate getDatumPocetka() {
		return datumPocetka;
	}



	public void setDatumPocetka(LocalDate datumPocetka) {
		this.datumPocetka = datumPocetka;
	}



	public LocalDate getDatumKraja() {
		return datumKraja;
	}



	public void setDatumKraja(LocalDate datumKraja) {
		this.datumKraja = datumKraja;
	}



	public String getRegistracijaVozila() {
		return registracijaVozila;
	}



	public void setRegistracijaVozila(String registracijaVozila) {
		this.registracijaVozila = registracijaVozila;
	}



	public String getImeNarucioca() {
		return imeNarucioca;
	}



	public void setImeNarucioca(String imeNarucioca) {
		this.imeNarucioca = imeNarucioca;
	}



	public String getPrezimeNarucioca() {
		return prezimeNarucioca;
	}



	public void setPrezimeNarucioca(String prezimeNarucioca) {
		this.prezimeNarucioca = prezimeNarucioca;
	}



	public Prevoz(int id, double cena, LocalDate datumPocetka, String registracijaVozila, String imeNarucioca,
			String prezimeNarucioca) {
		super();
		this.id = id;
		this.cena = cena;
		this.datumPocetka = datumPocetka;
		this.registracijaVozila = registracijaVozila;
		this.imeNarucioca = imeNarucioca;
		this.prezimeNarucioca = prezimeNarucioca;
	}
	
	public String toString(){
		
		return String.format("%15s %15s %15s %10.2f %20s", this.id, this.cena, this.registracijaVozila, this.imeNarucioca,this.prezimeNarucioca,dtf.format(this.datumPocetka),dtf.format(this.datumKraja));
		
	}
	

}
