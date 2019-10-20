package zadatak;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;








public class Agencija {
	
	private String naziv;
	private String webAdresa;
	private String telefon;
	private ArrayList<Prevoz> listaPrevoza;
	
	
	public Agencija () {
		
	}


	public Agencija(String naziv, String webAdresa, String telefon, ArrayList<Prevoz> listaPrevoza) {
		super();
		this.naziv = naziv;
		this.webAdresa = webAdresa;
		this.telefon = telefon;
		this.listaPrevoza = listaPrevoza;
	}


	public Agencija(ArrayList<Prevoz> listaPrevoza) {
		super();
		this.listaPrevoza = listaPrevoza;
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


	public ArrayList<Prevoz> getListaPrevoza() {
		return listaPrevoza;
	}


	public void setListaPrevoza(ArrayList<Prevoz> listaPrevoza) {
		this.listaPrevoza = listaPrevoza;
	}


	public void ispisPrevoza() {
		System.out.printf("%15s %15s %15s %10.2f %20s", "Id","Cena", "Registracija vozila", "Ime narucioca","Prezime narucioca","Datum pocetka","Datum kraja");
		for(int i = 0; i < this.listaPrevoza.size();i++) {
			Prevoz prevoz = listaPrevoza.get(i);
			System.out.println(prevoz);
		}
	}
	
	public boolean dodavanjaPrevoza(Prevoz prevoz) {
		for(int i = 0; i < this.listaPrevoza.size();i++) {
			Prevoz prevozIzListe=this.listaPrevoza.get(i);
			if(prevoz.getId() == prevozIzListe.getId()){
			 return false;
				
			}
		}
		 
		this.listaPrevoza.add(prevoz);
		return true;
		
	}
	
	public Prevoz brisanjePrevoza(int id) {
		
		int indeks = -1;
		for(int i = 0; i < this.listaPrevoza.size();i++) {
			if(this.listaPrevoza.get(i).getId() == id) {
				indeks = i;
			}
		}

		if(indeks != -1) {
			Prevoz prevozKojiSeBrise = this.listaPrevoza.remove(indeks);
			return prevozKojiSeBrise;
		}
		return null;
		}
	
	public Prevoz izmenaPrevoza(Prevoz prevozZaIzmenu) {
		for(int i = 0; i < this.listaPrevoza.size();i++) {
			
			if(this.listaPrevoza.get(i).getId() == prevozZaIzmenu.getId()) {
				Prevoz prevozKojiSeMenja = this.listaPrevoza.set(i, prevozZaIzmenu);
				return prevozZaIzmenu;
				
				
			}
		}
		
		return null;
	}
	
	
	   public ArrayList<Prevoz> ispisSvihPrevozaZaVozilo(String registracijaVozila) {
		   
		   
		   ArrayList<Prevoz> listaRezultata = new ArrayList<>();
		   for(int i = 0; i < this.listaPrevoza.size();i++) {
			   if(this.listaPrevoza.get(i).getRegistracijaVozila().equalsIgnoreCase(registracijaVozila)){
				  Prevoz prevozZaNavedenoVozilo= this.listaPrevoza.get(i);
				  listaRezultata.add(prevozZaNavedenoVozilo);
				  System.out.println(prevozZaNavedenoVozilo);
				   
			   }
		   }
		   return listaRezultata;
	   }
	   public void save(String path) {

			ArrayList<String> lines = new ArrayList<String>();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
			for (int i = 0; i < this.listaPrevoza.size(); i++) {
				Prevoz prevoz = this.listaPrevoza.get(i);
				int identifikacioniBroj = prevoz.getId();
				String registracijaVozila = prevoz.getRegistracijaVozila();
				String imeNarucioca = prevoz.getImeNarucioca();
				String prezimeNarucioca = prevoz.getPrezimeNarucioca();
				String datumPocetka = dtf.format(prevoz.getDatumPocetka());
			    String datumKraja = dtf.format(prevoz.getDatumKraja()); 
				
				String line = identifikacioniBroj  + ";" + registracijaVozila + ";" + imeNarucioca+ ";" + prezimeNarucioca + ";" + datumPocetka+";"+datumKraja;
				lines.add(line);
			}

			try {
				Files.write(Paths.get(path), lines, Charset.defaultCharset(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
			} catch (java.io.IOException e) {
				System.out.println("Datoteka " + path + " nije pronađena.");
			}
		}

	   
	   
	   
	
	public String toString() {

		String temp = "";
		temp += "Naziv agencije: " + this.naziv + "\n";
		temp += "Web adresa agencije: " + this.webAdresa + "\n";
		temp += "Telefon agencije: " + this.telefon + "\n";
		temp += "Ukupna cena prevoza za svaku kategoriju: "  + "\n";
		temp += "Ukupan broj vozila za svaku kategoriju: "   + "\n";
		
		
		return temp;
		
	}
}
	

