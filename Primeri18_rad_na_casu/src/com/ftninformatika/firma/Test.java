package com.ftninformatika.firma;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Test {

	public static Scanner scanner = new Scanner(System.in);
	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

	public static boolean proveriDatum(String datum) {

		try {
			LocalDate.parse(datum, dtf);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean daLiJeVeciDatum(LocalDate datumPocetni, String datum) {

		LocalDate datumKrajnji = null;
		try {
			datumKrajnji = LocalDate.parse(datum, dtf);
			if(datumKrajnji.compareTo(datumPocetni) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * Metoda proverava da li je uneti string sa tastature ceo broj.
	 */
	public static boolean isNumber(String string) {

		try {
			Integer.parseInt(string);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/*
	 * Metoda proverava da li je uneti string sa tastature decimalni broj.
	 */
	public static boolean isDecimalNumber(String string) {
		try {
			Double.parseDouble(string);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static void unesiInventar(Firma firma) {

		int id = 0;
		String ime = null;
		String prostorija = null;
		String imePrezime = null;
		double procenjenaVrednost = 0.0;
		int godinaProizvodnje = 0;
		String idInventara = null;
		String datumUnos = null;
		LocalDate datumZaInventar = null;
		do {
			System.out.print("Identifikacioni broj: ");
			idInventara = scanner.nextLine();
		} while(!isNumber(idInventara));
		id = Integer.parseInt(idInventara);
		System.out.print("Ime inventara: ");
		ime = scanner.nextLine();
		System.out.print("Prostorija u kojoj se nalazi inventar: ");
		prostorija = scanner.nextLine();
		System.out.print("Ime i prezime osobe zadužene za inventar: ");
		imePrezime = scanner.nextLine();
		String procVrednost = null;
		do {
			System.out.print("Procenjena vrednost inventara: ");
			procVrednost = scanner.nextLine();
		} while(!isDecimalNumber(procVrednost));
		procenjenaVrednost = Double.parseDouble(procVrednost);
		String godProizvodnje = null;
		do {
			System.out.print("Godina proizvodnje inventara: ");
			godProizvodnje = scanner.nextLine();
		} while(!isNumber(godProizvodnje));
		godinaProizvodnje = Integer.parseInt(godProizvodnje);
		do {
			System.out.print("Datum inventara: ");
			datumUnos = scanner.nextLine();
		} while(!proveriDatum(datumUnos));
		try {
			datumZaInventar = LocalDate.parse(datumUnos, dtf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Inventar inventar = new Inventar(id, ime, prostorija, imePrezime, procenjenaVrednost, godinaProizvodnje, datumZaInventar);
		boolean provera = firma.dodavanjeInventara(inventar);
		if(provera) {
			System.out.println("Inventar je uspešno dodat.");
		} else {
			System.out.println("Inventar nije uspešno dodat.");
		}
	}

	public static void ispisInventara(Firma firma) {

		firma.ispisInventara();
	}

	public static void brisanjeInventara(Firma firma) {

		int id = 0;
		String idInventara = null;
		do {
			System.out.println("Identifikacioni broj inventara za brisanje: ");
			idInventara = scanner.nextLine();
		} while(!isNumber(idInventara));
		id = Integer.valueOf(idInventara);
		Inventar provera = firma.brisanjeInventara(id);
		if(provera == null) {
			System.out.println("Inventar sa zadatim brojem ne postoji.");
		}
	}

	public static void inventarUProstoriji(Firma firma) {

		System.out.println("Unesite naziv prostorije: ");
		String prostorija = scanner.nextLine();
		firma.ispisInventaraUIstojProstoriji(prostorija);
	}

	public static void inventarPoNazivu(Firma firma) {

		System.out.println("Unesite naziv inventara: ");
		String naziv = scanner.nextLine();
		firma.ispisInventaraIstogNaziva(naziv);
	}

	public static void inventarPoOsobi(Firma firma) {

		System.out.println("Unesite naziv prostorije: ");
		String prostorija = scanner.nextLine();
		System.out.println("Unesite ime i prezime osobe koja duži inventar: ");
		String imePrezime = scanner.nextLine();
		String godina = null;
		do {
			System.out.println("Unesite godinu proizvodnje inventara: ");
			godina = scanner.nextLine();
		} while(!isNumber(godina));
		int godinaProizvodnje = Integer.valueOf(godina);
		firma.ispisInventaraDodatniParametri(prostorija, imePrezime, godinaProizvodnje);
	}

	public static void inventarPoVrednosti(Firma firma) {

		System.out.println("Unesite naziv inventara: ");
		String ime = scanner.nextLine();
		String minVrednost = null;
		do {
			System.out.println("Unesite donju granicu procenjene vrednosti: ");
			minVrednost = scanner.nextLine();
		} while(!isDecimalNumber(minVrednost));
		double donjaProcenjenaVrednost = Double.parseDouble(minVrednost);
		String maxVrednost = null;
		do {
			System.out.println("Unesite gornju granicu procenjene vrednosti: ");
			maxVrednost = scanner.nextLine();
		} while(!isDecimalNumber(maxVrednost));

		double gornjaProcenjenaVrednost = Double.parseDouble(maxVrednost);
		firma.ispisInventaraProcenjenaVrednost(ime, donjaProcenjenaVrednost, gornjaProcenjenaVrednost);
	}

	public static void inventarPoDatumu(Firma firma) {

		System.out.println("Unesite prostoriju inventara: ");
		String prostorija = scanner.nextLine();
		System.out.println("Unesite osobu zaduzenu za inventar: ");
		String osoba = scanner.nextLine();
		String datumUnos = null;
		LocalDate datumZaInventar = null;
		do {
			System.out.print("Datum inventara: ");
			datumUnos = scanner.nextLine();
		} while(!proveriDatum(datumUnos));
		try {
			datumZaInventar = LocalDate.parse(datumUnos, dtf);
		} catch (Exception e) {
			e.printStackTrace();
		}

		firma.ispisInventaraDatum(prostorija, osoba, datumZaInventar);
	}

	public static void prosecnaVrednostPoUslovima(Firma firma) {

		System.out.println("Unesite osobu zaduzenu za inventar: ");
		String osoba = scanner.nextLine();
		String minGodina = null;
		do {
			System.out.println("Unesite donju granicu za godinu: ");
			minGodina = scanner.nextLine();
		} while(!isNumber(minGodina));
		int godina = Integer.parseInt(minGodina);
		firma.ispisiProsecnuCenuInventaraSaUslovima(osoba, godina);
	}

	public static void izmenaInventara(Firma firma) {

		String idInventara = null;
		do {
			System.out.println("Unesite identifikacioni broj inventara: ");
			idInventara = scanner.nextLine();
		} while(!isNumber(idInventara));
		int id = Integer.valueOf(idInventara);
		String ime = null;
		String prostorija = null;
		String imePrezime = null;
		double procenjenaVrednost = 0.0;
		int godinaProizvodnje = 0;
		System.out.print("Ime inventara: ");
		ime = scanner.nextLine();
		System.out.print("Prostorija u kojoj se nalazi inventar: ");
		prostorija = scanner.nextLine();
		System.out.print("Ime i prezime osobe zadužene za inventar: ");
		imePrezime = scanner.nextLine();
		String procVrednost = null;
		do {
			System.out.print("Procenjena vrednost inventara: ");
			procVrednost = scanner.nextLine();
		} while(!isDecimalNumber(procVrednost));
		procenjenaVrednost = Double.valueOf(procVrednost);
		String godProizvodnje = null;
		do {
			System.out.print("Godina proizvodnje inventara: ");
			godProizvodnje = scanner.nextLine();
		} while(!isNumber(godProizvodnje));
		godinaProizvodnje = Integer.valueOf(godProizvodnje);
		String datumUnos = null;
		LocalDate datumZaInventar = null;
		do {
			System.out.print("Datum inventara: ");
			datumUnos = scanner.nextLine();
		} while(!proveriDatum(datumUnos));
		try {
			datumZaInventar = LocalDate.parse(datumUnos, dtf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Inventar inventar = new Inventar(id, ime, prostorija, imePrezime, procenjenaVrednost, godinaProizvodnje, datumZaInventar);
		Inventar provera = firma.izmenaInventara(inventar);
		if(provera != null) {
			System.out.println("Izmena je uspešno izvršena.");
		} else {
			System.out.println("Izmena je neuspešno izvršena.");
		}
	}

	public static void main(String[] args) {

		Firma firmaTest = new Firma();

		Firma firma = new Firma();
		firma.load("inventar.txt");
		String answer = null;

		do {

			System.out.println("Meni:");
			System.out.println("1. Unesi inventar");
			System.out.println("2. Ispis svih inventara");
			System.out.println("3. Brisanje inventara");
			System.out.println("4. Ispis inventara u istoj prostoriji");
			System.out.println("5. Ispis inventara po istom nazivu");
			System.out.println("6. Ispis inventara po prostoriji, osobi koja ih duži i godini");
			System.out.println("7. Ispis inventara po zadatom nazivu i zadatim granicama vrednosti");
			System.out.println("8. Izmena inventara");
			System.out.println("9. Ispis inventara po zadatoj prostoriji i zadatim granicama datuma");
			System.out.println("10. Ispis prosecne vrednosti inventara za zadate uslove");
			System.out.println("x. Izlaz");

			answer = scanner.nextLine();

			switch (answer) {
			case "1":
				unesiInventar(firma);
				firma.save("inventar.txt");
				break;
			case "2":
				ispisInventara(firma);
				break;
			case "3":
				brisanjeInventara(firma);
				firma.save("inventar.txt");
				break;
			case "4":
				inventarUProstoriji(firma);
				break;
			case "5":
				inventarPoNazivu(firma);
				break;
			case "6":
				inventarPoOsobi(firma);
				break;
			case "7":
				inventarPoVrednosti(firma);
				break;
			case "8":
				izmenaInventara(firma);
				firma.save("inventar.txt");
				break;
			case "9":
				inventarPoDatumu(firma);
				break;
			case "10":
				prosecnaVrednostPoUslovima(firma);
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
