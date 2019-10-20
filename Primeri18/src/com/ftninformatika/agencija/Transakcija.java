package com.ftninformatika.agencija;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transakcija {
	
	private int id; 
	private String nazivKlijenta;
	private LocalDate datumTransakcije;
	private String tipTransakcije;
	private double iznosTransakcije;
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
	
	public Transakcija() {
		
	}
	

	public Transakcija(int id, String nazivKlijenta, LocalDate datumTransakcije, String tipTransakcije,
			double iznosTransakcije) {
		super();
		this.id = id;
		this.nazivKlijenta = nazivKlijenta;
		this.datumTransakcije = datumTransakcije;
		this.tipTransakcije = tipTransakcije;
		this.iznosTransakcije = iznosTransakcije;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNazivKlijenta() {
		return nazivKlijenta;
	}


	public void setNazivKlijenta(String nazivKlijenta) {
		this.nazivKlijenta = nazivKlijenta;
	}


	public LocalDate getDatumTransakcije() {
		return datumTransakcije;
	}


	public void setDatumTransakcije(LocalDate datumTransakcije) {
		this.datumTransakcije = datumTransakcije;
	}


	public String getTipTransakcije() {
		return tipTransakcije;
	}


	public void setTipTransakcije(String tipTransakcije) {
		this.tipTransakcije = tipTransakcije;
	}


	public double getIznosTransakcije() {
		return iznosTransakcije;
	}


	public void setIznosTransakcije(double iznosTransakcije) {
		this.iznosTransakcije = iznosTransakcije;
	}

	@Override
	public String toString() {
		return String.format("%15s %15s %15s %10.2f %20s", this.id, this.nazivKlijenta, this.tipTransakcije, this.iznosTransakcije, dtf.format(this.datumTransakcije));
	}

}
