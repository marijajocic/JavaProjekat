package com.ftninformatika.agencija74;

import java.time.LocalDate;

public class Transakcija {
	
	private int id;
	private String nazivKlijenta;
	private LocalDate datum;
	private double iznos;
	private String tip;
	
	public Transakcija() {
		
	}

	public Transakcija(int id, String nazivKlijenta, LocalDate datum, double iznos, String tip) {
		super();
		this.id = id;
		this.nazivKlijenta = nazivKlijenta;
		this.datum = datum;
		this.iznos = iznos;
		this.tip = tip;
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

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	@Override
	public String toString() {
		return "Transakcija [id=" + id + ", nazivKlijenta=" + nazivKlijenta + ", datum=" + datum + ", iznos=" + iznos
				+ ", tip=" + tip + "]";
	}

}
