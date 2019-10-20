package com.ftninformatika.agencija;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.ftninformatika.agencija.Transakcija;

public class Agencija {

	private String naziv;
	private String webAdresa;
	private String telefon;
	private ArrayList<Transakcija> listaTransakcija;

	public Agencija() {
		this.listaTransakcija = new ArrayList<Transakcija>();
	}


	public Agencija(String naziv, String webAdresa, String telefon) {
		this.naziv = naziv;
		this.webAdresa = webAdresa;
		this.telefon = telefon;
		this.listaTransakcija = new ArrayList<Transakcija>();
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


	public ArrayList<Transakcija> getListaTransakcija() {
		return listaTransakcija;
	}

	public void setListaTransakcija(ArrayList<Transakcija> listaTransakcija) {
		this.listaTransakcija = listaTransakcija;
	}

    public void ispisTransakcija() {
    	System.out.printf("%15s %15s %15s %15s %20s\n", "Id", "Naziv klijenta", "Tip transakcije", "Iznos transakcije", "Datum");
    	
    	for(int i = 0; i < this.listaTransakcija.size(); i++) {
			Transakcija transakcija = listaTransakcija.get(i);
			System.out.println(transakcija);
    	}
    }
 
    public boolean dodavanjeTransakcije(Transakcija transakcija) {
    	for ( int i = 0; i< this.listaTransakcija.size(); i++) {
    		Transakcija transakcijaIzListe = this.listaTransakcija.get(i);
    		
    		if(transakcija.getId()== transakcijaIzListe.getId()) {
    			return false;
    		}
    	}
    	this.listaTransakcija.add(transakcija);
    	return true;
    }
	
    
	public Transakcija brisanjeTransakcije(int id) {

		int indeks = -1;
		for (int i = 0; i < this.listaTransakcija.size(); i++) {
			if(this.listaTransakcija.get(i).getId() == id) {
				indeks = i;
			}
		}

		if(indeks != -1) {
			Transakcija transakcijaKojaSeBrise = this.listaTransakcija.remove(indeks);
			return transakcijaKojaSeBrise;
		}
		return null;
		
  
    	}
    	

	public ArrayList<Transakcija> ispisTransakcijaKlijenta(String nazivKlijenta) {

		System.out.printf("%15s %15s %15s %15s %20s\n", "Id", "Naziv klijenta", "Tip transakcije", "Iznos transakcije", "Datum");
		ArrayList<Transakcija> listaRezultata = new ArrayList<>();
		for (int i = 0; i < this.listaTransakcija.size(); i++) {
			if(this.listaTransakcija.get(i).getNazivKlijenta().equalsIgnoreCase(nazivKlijenta)) {
				Transakcija transakcijaZaKlijenta = this.listaTransakcija.get(i);
				listaRezultata.add(transakcijaZaKlijenta);
				System.out.println(transakcijaZaKlijenta);
			}
		}
		return listaRezultata;

	}

	public ArrayList<Transakcija> ispisUplataKlijenta(String nazivKlijenta) {

		System.out.printf("%15s %15s %15s %15s %20s\n", "Id", "Naziv klijenta", "Tip transakcije", "Iznos transakcije", "Datum");
		ArrayList<Transakcija> listaRezultata = new ArrayList<>();
		for (int i = 0; i < this.listaTransakcija.size(); i++) {
			if(this.listaTransakcija.get(i).getNazivKlijenta().equalsIgnoreCase(nazivKlijenta) && this.listaTransakcija.get(i).getTipTransakcije().equals("uplata")) {
				Transakcija transakcijaZaKlijenta = this.listaTransakcija.get(i);
				listaRezultata.add(transakcijaZaKlijenta);
				System.out.println(transakcijaZaKlijenta);
			}
		}
		return listaRezultata;

	}

    
	public Transakcija izmenaTransakcije(Transakcija transakcijaZaIzmenu) {

		for (int i = 0; i < this.listaTransakcija.size(); i++) {
			if(this.listaTransakcija.get(i).getId() == transakcijaZaIzmenu.getId()) {
				Transakcija transakcijaKojiSeMenja = this.listaTransakcija.set(i, transakcijaZaIzmenu);
				return transakcijaKojiSeMenja;
			}
		}
		return null;
	}
	
	public double izracunavanjeProsecneIsplateKlijenta(String nazivKlijenta) {

		ArrayList<Transakcija> listaRezultata = new ArrayList<>();
		for(int i = 0; i < this.listaTransakcija.size(); i++) {
			if(this.listaTransakcija.get(i).getNazivKlijenta().equalsIgnoreCase(nazivKlijenta) && this.listaTransakcija.get(i).getTipTransakcije().equals("isplata")) {
				listaRezultata.add(this.listaTransakcija.get(i));

			}
		}

		double prosek = 0.0;
		double suma = 0.0;
		for(int i = 0; i < listaRezultata.size(); i++) {
			suma += listaRezultata.get(i).getIznosTransakcije();
		}
		if(suma > 0.0) {
			prosek = suma / listaRezultata.size();
		}
		System.out.println("Prosecna transakcija klijenta: " + nazivKlijenta + " za isplatu je: " + prosek);
		return prosek;
	}

	public Transakcija pronadjiJednuNajvecuUplatu(ArrayList<Transakcija> listaTransakcija) {

		Transakcija najveca = null;
		if(listaTransakcija.size() > 0) {
			najveca = listaTransakcija.get(0);
			for(int i = 1; i < listaTransakcija.size(); i++) {
				if(najveca.getIznosTransakcije() < listaTransakcija.get(i).getIznosTransakcije()) {
					najveca = listaTransakcija.get(i);
				}
			}
		}
		return najveca;
	}

	public ArrayList<Transakcija> pronadjiSveNajveceUplate(ArrayList<Transakcija> listaFilterTransakcija, Transakcija najveca) {

		ArrayList<Transakcija> listaRezultata = new ArrayList<>();
		for(int i = 0; i < listaFilterTransakcija.size(); i++) {
			if(listaFilterTransakcija.get(i).getIznosTransakcije() == najveca.getIznosTransakcije()) {
				listaRezultata.add(listaFilterTransakcija.get(i));
			}
		}
		return listaRezultata;
	}

	public ArrayList<Transakcija> pretraga9(String nazivKlijenta, LocalDate min, LocalDate max) {

		ArrayList<Transakcija> listaRezultata = new ArrayList<>();
		for(int i = 0; i < this.listaTransakcija.size(); i++) {
			if(this.listaTransakcija.get(i).getNazivKlijenta().equalsIgnoreCase(nazivKlijenta)
					&& this.listaTransakcija.get(i).getTipTransakcije().equals("uplata")
					&& this.listaTransakcija.get(i).getDatumTransakcije().compareTo(min) >= 0
					&& this.listaTransakcija.get(i).getDatumTransakcije().compareTo(max) <= 0) {
				listaRezultata.add(this.listaTransakcija.get(i));
			}
		}
		Transakcija najveca = pronadjiJednuNajvecuUplatu(listaRezultata);

		if(najveca == null) {
			System.out.println("Ne postoji informacija za zadate uslove.");
			return null;
		} else {
			ArrayList<Transakcija> sveNajveceTransakcije = pronadjiSveNajveceUplate(listaRezultata, najveca);
			for(int i = 0; i < sveNajveceTransakcije.size(); i++) {
				System.out.println("Najveca transakcija " + i + " je: " + sveNajveceTransakcije.get(i));
			}
			return sveNajveceTransakcije;
		}
	}

	public void save(String path) {

		ArrayList<String> lines = new ArrayList<String>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		for (int i = 0; i < this.listaTransakcija.size(); i++) {
			Transakcija transakcija = this.listaTransakcija.get(i);
			int identifikacioniBroj = transakcija.getId();
			String nazivKlijenta = transakcija.getNazivKlijenta();
			String tipTransakcije = transakcija.getTipTransakcije();
			double iznos = transakcija.getIznosTransakcije();
			String formatiraniDatum = dtf.format(transakcija.getDatumTransakcije()); 
			String line = identifikacioniBroj  + ";" + nazivKlijenta + ";" + formatiraniDatum + ";" + tipTransakcije + ";" + iznos;
			lines.add(line);
		}

		try {
			Files.write(Paths.get(path), lines, Charset.defaultCharset(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronađena.");
		}
	}
	public void load(String path) {

		this.listaTransakcija = new ArrayList<Transakcija>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
			for (String line: lines) {
				String[] attributes = line.split(";");

				int id = Integer.parseInt(attributes[0]); 
				String nazivKlijenta = attributes[1];
				String datumString = attributes[2];  
				String tipTransakcije = attributes[3];
				double iznosTransakcije = Double.parseDouble(attributes[4]); 
				LocalDate datumZaCuvanje = null;
				try {
					datumZaCuvanje = LocalDate.parse(datumString, dtf);
				} catch (Exception e) {
					e.printStackTrace();
				}

				Transakcija transakcija = new Transakcija(id, nazivKlijenta, datumZaCuvanje, tipTransakcije, iznosTransakcije);
				this.listaTransakcija.add(transakcija);

			}
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronađena.");
		}

	}


	public double izracunajSumu(String tip) {

		double suma = 0.0;
		for(int i = 0; i < this.listaTransakcija.size(); i++) {
			if(this.listaTransakcija.get(i).getTipTransakcije().equals(tip)) {
				suma += this.listaTransakcija.get(i).getIznosTransakcije();
			}
		}

		return suma;
	}

	public String toString() {

		String temp = "";
		temp += "Naziv agencije: " + this.naziv + "\n";
		temp += "Web adresa agencije: " + this.webAdresa + "\n";
		temp += "Telefon agencije: " + this.telefon + "\n";
		temp += "Ukupan iznos uplata: " + izracunajSumu("uplata") + "\n";
		temp += "Ukupan iznos isplata: " + izracunajSumu("isplata") + "\n";
		return temp;
	}





}
