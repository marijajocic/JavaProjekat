package com.ftninformatika.imenik;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Test {

	public static Scanner scanner = new Scanner(System.in);
	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

	/*
	 * Metoda proverava da li je uneti string sa tastature ceo broj.
	 */
	public static boolean isNumber(String string) {
		try {
			int broj = Integer.parseInt(string);
			if(broj > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isDate(String datum) {
		try {
			LocalDate.parse(datum, dtf);
			return true;
		} catch (Exception e) {
			return false;
		}	
	}

	public static void unesiKontakt(Imenik imenik) {

		int id = 0;
		String ime = null;
		String prezime = null;
		String nazivRadnogMesta = null;
		String brojProstorije = null;
		int brojLokala = 0;
		LocalDate datumUnosa = null;
		String idKontakta = null;
		do {
			System.out.print("Identifikacioni broj: ");
			idKontakta = scanner.nextLine();
		} while(!isNumber(idKontakta));
		id = Integer.parseInt(idKontakta);
		System.out.print("Ime kontakta: ");
		ime = scanner.nextLine();
		System.out.print("Prezime kontakta: ");
		prezime = scanner.nextLine();
		System.out.print("Naziv radnog mesta kontakta: ");
		nazivRadnogMesta = scanner.nextLine();
		System.out.print("Broj prostorije: ");
		brojProstorije = scanner.nextLine();
		String brLokala = null;
		do {
			System.out.print("Broj lokala: ");
			brLokala = scanner.nextLine();
		} while(!isNumber(brLokala));
		
		brojLokala = Integer.parseInt(brLokala);
		
		String datum = null;
		do {
			System.out.println("Datum unosa: ");
			datum = scanner.nextLine();
		} while (!isDate(datum));
		
		try {
			datumUnosa = LocalDate.parse(datum, dtf);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		Kontakt kontakt = new Kontakt(id, ime, prezime, nazivRadnogMesta, brojProstorije, brojLokala, datumUnosa);
		boolean provera = imenik.dodavanjeKontakta(kontakt);
		if(provera) {
			System.out.println("Kontakt je uspešno dodat.");
		} else {
			System.out.println("Kontakt nije uspešno dodat.");
		}
	}

	public static void ispisKontakata(Imenik imenik) {

		imenik.ispisKontakata();
	}

	public static void brisanjeKontakta(Imenik imenik) {

		int id = 0;
		String idKontakta = null;
		do {
			System.out.println("Identifikacioni broj kontakta za brisanje: ");
			idKontakta = scanner.nextLine();
		} while(!isNumber(idKontakta));
		id = Integer.valueOf(idKontakta);
		Kontakt provera = imenik.brisanjeKontakta(id);
		if(provera == null) {
			System.out.println("Kontakt sa zadatim brojem ne postoji.");
		}
	}

	public static void kontaktiULokalu(Imenik imenik) {

		String brLokala = null;
		do {
			System.out.println("Unesite broj lokala: ");
			brLokala = scanner.nextLine();
		} while(!isNumber(brLokala));
		int brojLokala = Integer.parseInt(brLokala);
		imenik.ispisKontakataSaIstimLokalom(brojLokala);
	}

	public static void kontaktiNaIstomRadnomMestu(Imenik imenik) {

		System.out.println("Unesite naziv radnog mesta: ");
		String radnoMesto = scanner.nextLine();
		imenik.ispisKontakataSaIstimRadnimMestom(radnoMesto);
	}

	public static void kontaktiSaIstimImenom(Imenik imenik) {

		System.out.println("Unesite ime kontakta: ");
		String ime = scanner.nextLine();
		imenik.ispisKontakataSaIstimImenom(ime);
	}

	public static void kontaktiPoUslovima8(Imenik imenik) {

		System.out.println("Unesite ime kontakta: ");
		String ime = scanner.nextLine();
		System.out.println("Unesite prezime kontakta: ");
		String prezime = scanner.nextLine();
		System.out.println("Unesite po�?etna slova naziva radnog mesta: ");
		String radnoMesto = scanner.nextLine();
		imenik.ispisKontakataSaZadatimUslovima(ime, prezime, radnoMesto);
	}
	
	public static void kontaktiUINtervalu(Imenik imenik) {
		LocalDate datumPocetka = null;
		LocalDate datumKraja = null;
		String datumPocetkaStr = null;
		do {
			System.out.println("Datum po�?etka intervala: ");
			datumPocetkaStr = scanner.nextLine();
		} while (!isDate(datumPocetkaStr));
		
		try {
			datumPocetka = LocalDate.parse(datumPocetkaStr, dtf);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		String datumKrajaStr = null;
		do {
			System.out.println("Datum kraja intervala: ");
			datumKrajaStr = scanner.nextLine();
		} while (!isDate(datumKrajaStr));
		
		try {
			datumKraja = LocalDate.parse(datumKrajaStr, dtf);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		imenik.ispisKontakataUIntervalu(datumPocetka, datumKraja);
	}

	public static void izmenaKontakta(Imenik imenik) {

		String idKontakta = null;
		do {
			System.out.println("Unesite identifikacioni broj kontakta: ");
			idKontakta = scanner.nextLine();
		} while(!isNumber(idKontakta));
		int id = Integer.valueOf(idKontakta);
		String ime = null;
		String prezime = null;
		String nazivRadnogMesta = null;
		String brojProstorije = null;
		int brojLokala = 0;
		LocalDate datumUnosa = null;
		System.out.print("Ime kontakta: ");
		ime = scanner.nextLine();
		System.out.print("Prezime kontakta: ");
		prezime = scanner.nextLine();
		System.out.print("Naziv radnog mesta: ");
		nazivRadnogMesta = scanner.nextLine();
		System.out.print("Broj prostorije: ");
		brojProstorije = scanner.nextLine();
		String brLokala = null;
		do {
			System.out.print("Broj lokala: ");
			brLokala = scanner.nextLine();
		} while(!isNumber(brLokala));
		brojLokala = Integer.valueOf(brLokala);
		
		String datum = null;
		do {
			System.out.println("Datum unosa: ");
			datum = scanner.nextLine();
		} while (!isDate(datum));
		
		try {
			datumUnosa = LocalDate.parse(datum, dtf);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		Kontakt kontakt = new Kontakt(id, ime, prezime, nazivRadnogMesta, brojProstorije, brojLokala, datumUnosa);
		Kontakt provera = imenik.izmenaKontakta(kontakt);
		if(provera != null) {
			System.out.println("Izmena je uspešno izvršena.");
		} else {
			System.out.println("Izmena je neuspešno izvršena.");
		}
	}

	public static void main(String[] args) {

		Imenik imenik = new Imenik();
		imenik.load("kontakti.txt");
		
		String answer = null;

		do {

			System.out.println("Meni:");
			System.out.println("1. Unesi kontakt");
			System.out.println("2. Ispis svih kontakata");
			System.out.println("3. Brisanje kontakta");
			System.out.println("4. Ispis kontakata u istom lokalu");
			System.out.println("5. Ispis kontakata sa istim radnim mestom");
			System.out.println("6. Ispis kontakata sa istim imenom");
			System.out.println("7. Ispis kontakata sa istim imenom, prezimenom i nazivom radnog mesta koje po�?inje sa prosleđenim stringom");
			System.out.println("8. Ispis kontakata kreiranih u zadatom intervalu datuma");
			System.out.println("9. Izmena kontakta");
			System.out.println("x. Izlaz");

			answer = scanner.nextLine();

			switch (answer) {
			case "1":
				unesiKontakt(imenik);
				imenik.save("kontakti.txt");
				break;
			case "2":
				ispisKontakata(imenik);
				break;
			case "3":
				brisanjeKontakta(imenik);
				imenik.save("kontakti.txt");
				break;
			case "4":
				kontaktiULokalu(imenik);
				break;
			case "5":
				kontaktiNaIstomRadnomMestu(imenik);
				break;
			case "6":
				kontaktiSaIstimImenom(imenik);
				break;
			case "7":
				kontaktiPoUslovima8(imenik);
				break;
			case "8":
				kontaktiUINtervalu(imenik);
				break;
			case "9":
				izmenaKontakta(imenik);
				imenik.save("kontakti.txt");
				break;
			case "x":
				break;
			default:
				System.out.println("Pogresan izbor opcije. Pokusajte ponovo.");
			}

		} while (!answer.equals("x"));

		scanner.close();

	}

}
