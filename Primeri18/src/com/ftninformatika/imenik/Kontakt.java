package com.ftninformatika.imenik;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Kontakt {
	
	/*
	 * Podrazumevaćemo da će svaki kontakt imati jedinstveni broj za id jer se na neki način moraju razlikovati objekti
	 * Na primer može postojati više kontakata koji se zovu Milan i prezivaju Stojkov, ali ne može postojati više Milana Stojkova koje imaju id 1
	 */
	private int id;
	private String ime;
	private String prezime;
	private String nazivRadnogMesta;
	private String brojProstorije;
	private int brojLokala;
	private LocalDate datumUnosa;
	
	public Kontakt() {
		
	}


	public Kontakt(int id, String ime, String prezime, String nazivRadnogMesta,
			String brojProstorije, int brojLokala, LocalDate datumUnosa) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.nazivRadnogMesta = nazivRadnogMesta;
		this.brojProstorije = brojProstorije;
		this.brojLokala = brojLokala;
		this.datumUnosa = datumUnosa;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getNazivRadnogMesta() {
		return nazivRadnogMesta;
	}


	public void setNazivRadnogMesta(String nazivRadnogMesta) {
		this.nazivRadnogMesta = nazivRadnogMesta;
	}


	public String getBrojProstorije() {
		return brojProstorije;
	}


	public void setBrojProstorije(String brojProstorije) {
		this.brojProstorije = brojProstorije;
	}


	public int getBrojLokala() {
		return brojLokala;
	}


	public void setBrojLokala(int brojLokala) {
		this.brojLokala = brojLokala;
	}
	
	public LocalDate getDatumUnosa() {
		return datumUnosa;
	}


	public void setDatumUnosa(LocalDate datumUnosa) {
		this.datumUnosa = datumUnosa;
	}	
	
	/*
	 * String ima metodu format koja prima kao prvi parametar neki proizvoljan tekst i specijalne karaktere koji će zauzeti
	 * onoliko mesta koliko je to iskazano brojem.
	 * Na primer %15s će uzeti 15 mesta za ispisivanje nekog stringa a %10.2f će uzeti 10 mesta za ispisivanje broja tipa float
	 * od �?ega će 8 mesta biti zauzeto za ceo deo, a 2 za brojeve iza decimale.
	 * Svaki sledeći parametar u metodi format predstavlja promenljivu �?ija će se vrednost upisati umesto tih specijalnih karaktera.
	 * Konkretno u metodi toString prvi parametar je "%15s %15s %15s %20s %15s %15s %20s":
	 * Uzima se redom 15, 15, 15, 20, 15 mesta za string, zatim 15 mesta za int i 20 za datum
	 * Sledeći parametri su id, ime, prezime, nazivRadnogMesta, brojProstorije, brojLokala, datumUpisa koji će se upisati na mestima:
	 * id na mestu prvog %15s
	 * ime na mestu drugog %15s
	 * prezime na mestu trećeg %15s
	 * nazivRadnogMesta na mestu %20s
	 * brojProstorije na mestu %15s
	 * brojLokala na mestu %15s
	 * formatiran datumUnosa na mestu %20s
	 * 
	 * Datum je potrebno prvo formatirati upotrebom klase DateTimeFormatter kako bi se ispisao u formatu "dd.MM.yyyy." npr. 27.11.2016. 
	 * Metodu toString smo napisali u ovom formatu zbog lepšeg ispisa ali se može iskoristiti i ona osnovna verzija 
	 * koja je zakomentarisana ispod samo što će ispis biti "ružniji".
	 * 
	 */
	@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		return String.format("%15s %15s %15s %20s %15s %15s %20s", this.id, this.ime, this.prezime, this.nazivRadnogMesta, this.brojProstorije, this.brojLokala, dtf.format(this.datumUnosa));
	}


	
	/*@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		return "Kontakt [id=" + this.id + ", ime=" + this.ime + ", prezime=" + this.prezime
				+ ", nazivRadnogMesta=" + this.nazivRadnogMesta
				+ ", brojProstorije=" + this.brojProstorije + ", brojLokala="
				+ this.brojLokala + "datumUpisa=" + dtf.format(this.datumUnosa) + "]";
	}*/
	
	/*
	 * Klasa Kontakt moze (a ne mora) imati main metodu. Ovde je napisana metoda main
	 * samo da bi se proverilo da li toString radi kako treba.
	 * Obicno nije potrebno imati main metodu u klasi koja modeluje podatke.
	 */
	public static void main(String[] args) {
		Kontakt kontakt = new Kontakt(1, "Milan", "Stojkov", "sekretar", "12A", 23, LocalDate.now());
		System.out.println(kontakt);
	}

}
