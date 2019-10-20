package com.ftninformatika.agencija73;

import java.time.LocalDate;
import java.util.ArrayList;

public class Agencija {
	
	private String naziv;
	private String webAdresa;
	private String telefon;
	private ArrayList<Transakcija> transakcije = new ArrayList<>();
	
	public Agencija() {
		
	}
	
	public Agencija(String naziv, String webAdresa, String telefon, ArrayList<Transakcija> transakcije) {
		super();
		this.naziv = naziv;
		this.webAdresa = webAdresa;
		this.telefon = telefon;
		this.transakcije = transakcije;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getWebAdresa() {
		return webAdresa;
	}

	public void setWebAdresa(String webAdresa) {
		this.webAdresa = webAdresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public ArrayList<Transakcija> getTransakcije() {
		return transakcije;
	}

	public void setTransakcije(ArrayList<Transakcija> transakcije) {
		this.transakcije = transakcije;
	}
	
	public boolean dodavanjeTransakcije(Transakcija transakcija) {
		for (int i = 0; i < this.transakcije.size(); i++) {
			if (this.transakcije.get(i).getId() == transakcija.getId()) {
				return false;
			}
		}
		this.transakcije.add(transakcija);
		return true;
	}
	
	public void ispisTransakcija() {
		for (int i = 0; i < this.transakcije.size(); i++) {
			System.out.println(this.transakcije.get(i));
		}
	}
	
	public ArrayList<Transakcija> pretraga9(String nazivKlijenta, LocalDate minDatum, LocalDate maxDatum) {
		ArrayList<Transakcija> pronadjene = new ArrayList<>();
		ArrayList<Transakcija> maksimalne = new ArrayList<>();
		for (int i = 0; i < this.transakcije.size(); i++) {
			if (this.transakcije.get(i).getNazivKlijenta().equalsIgnoreCase(nazivKlijenta) &&
					this.transakcije.get(i).getDatum().compareTo(minDatum) >= 0 && this.transakcije.get(i).getDatum().compareTo(maxDatum) <= 0
					&& this.transakcije.get(i).getTip().equals("uplata")) {
				pronadjene.add(this.transakcije.get(i));
			}
		}
		
		if (pronadjene.size() > 0) {
			Transakcija maxTransakcija = pronadjene.get(0);
			for (int i = 1; i < pronadjene.size(); i++) {
				if (pronadjene.get(i).getIznos() > maxTransakcija.getIznos()) {
					maxTransakcija = pronadjene.get(i);
				}
			}
			for (int i = 0; i < pronadjene.size(); i++) {
				if (pronadjene.get(i).getIznos() == maxTransakcija.getIznos()) {
					maksimalne.add(pronadjene.get(i));
				}
			}
		}
		return maksimalne;
	}

}
