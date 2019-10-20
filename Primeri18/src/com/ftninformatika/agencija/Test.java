package com.ftninformatika.agencija;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
	 * Proveravamo da li je prosledjeni string broj - ako nije vracamo false
	 * Proveravamo da li je broj veci od 0 - ako nije vracamo false
	 * Proveravamo da li transakcija sa tim brojem vec postoji - ako postoji vracamo false
	 * Ako ne postoji vracamo true
	 */
	public static boolean proveraZaDodavanje(String string, Agencija agencija) {

		try {
			int broj = Integer.parseInt(string);
			if(broj < 1) {
				return false;
			}
			ArrayList<Transakcija> lista = agencija.getListaTransakcija();
			for(int i = 0; i < lista.size(); i++) {
				if(broj == lista.get(i).getId()) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/*
	 * Proveravamo da li je prosledjeni string broj - ako nije vracamo false
	 * Proveravamo da li je broj veci od 0 - ako nije vracamo false
	 * Proveravamo da li transakcija sa tim brojem vec postoji - ako postoji vracamo true
	 * Ako ne postoji vracamo false
	 */
	public static boolean proveraZaBrisanjeIIzmenu(String string, Agencija agencija) {

		try {
			int broj = Integer.parseInt(string);
			if(broj < 1) {
				return false;
			}
			ArrayList<Transakcija> lista = agencija.getListaTransakcija();
			for(int i = 0; i < lista.size(); i++) {
				if(broj == lista.get(i).getId()) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean isDecimalNumber(String string) {
		try {
			double iznos = Double.parseDouble(string);
			if(iznos > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	private static void unesiAgenciju(Agencija agencija) {

		System.out.println("Unesite naziv agencije:");
		String naziv = scanner.nextLine();
		agencija.setNaziv(naziv);
		System.out.println("Unesite web adresu agencije:");
		String adresa = scanner.nextLine();
		agencija.setWebAdresa(adresa);
		System.out.println("Unesite broj telefona agencije:");
		String telefon = scanner.nextLine();
		agencija.setTelefon(telefon);
		System.out.println("Podaci o agenciji su uspesno dodati.");

	}

	public static void unesiTransakciju(Agencija agencija) {

		int id = 0;
		String nazivKlijenta = null;
		String tipTransakcije = null;
		String iznosTransakcije = null;
		double iznos = 0.0;
		String idTransakcije = null;
		String datumUnos = null;
		LocalDate datumZaTransakciju = null;
		do {
			System.out.print("Identifikacioni broj: ");
			idTransakcije = scanner.nextLine();
		} while(!proveraZaDodavanje(idTransakcije, agencija));
		id = Integer.parseInt(idTransakcije);
		System.out.print("Naziv klijenta: ");
		nazivKlijenta = scanner.nextLine();
		do {
			System.out.print("Tip transakcije: ");
			tipTransakcije = scanner.nextLine();
		} while(!(tipTransakcije.equals("uplata") || tipTransakcije.equals("isplata")));
		do {
			System.out.print("Iznos transakcije: ");
			iznosTransakcije = scanner.nextLine();
		} while(!isDecimalNumber(iznosTransakcije));
		iznos = Double.parseDouble(iznosTransakcije);
		do {
			System.out.print("Datum transakcije: ");
			datumUnos = scanner.nextLine();
		} while(!proveriDatum(datumUnos));
		try {
			datumZaTransakciju = LocalDate.parse(datumUnos, dtf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Transakcija transakcija = new Transakcija(id, nazivKlijenta, datumZaTransakciju, tipTransakcije, iznos);
		boolean provera = agencija.dodavanjeTransakcije(transakcija);
		if(provera) {
			System.out.println("Transakcija je uspešno dodata.");
		} else {
			System.out.println("Transakcija nije uspešno dodata.");
		}
	}

	public static void ispisSvihTransakcija(Agencija agencija) {

		agencija.ispisTransakcija();
	}

	public static void brisanjeTransakcije(Agencija agencija) {

		int id = 0;
		String idTransakcije = null;
		do {
			System.out.println("Identifikacioni broj transakcije za brisanje: ");
			idTransakcije = scanner.nextLine();
		} while(!proveraZaBrisanjeIIzmenu(idTransakcije, agencija));
		id = Integer.valueOf(idTransakcije);
		Transakcija provera = agencija.brisanjeTransakcije(id);
		if(provera == null) {
			System.out.println("Transakcija sa zadatim brojem ne postoji.");
		}
	}

	public static void pretragaPoKlijentu(Agencija agencija) {

		System.out.println("Unesite naziv klijenta: ");
		String nazivKlijenta = scanner.nextLine();
		agencija.ispisTransakcijaKlijenta(nazivKlijenta);
	}

	public static void pretragaPoUplatamaKlijenta(Agencija agencija) {

		System.out.println("Unesite naziv klijenta: ");
		String nazivKlijenta = scanner.nextLine();
		agencija.ispisUplataKlijenta(nazivKlijenta);
	}

	public static void prikazIsplateZaKlijenta(Agencija agencija) {

		System.out.println("Unesite naziv klijenta: ");
		String nazivKlijenta = scanner.nextLine();
		agencija.izracunavanjeProsecneIsplateKlijenta(nazivKlijenta);
	}

	public static void ispisPodatakaOAgenciji(Agencija agencija) {

		System.out.println(agencija);
	}

	public static void ispisNajveceTransakcije(Agencija agencija) {

		System.out.println("Unesite naziv klijenta: ");
		String nazivKlijenta = scanner.nextLine();
		String datumMinString = null;
		LocalDate datumMin = null;
		do {
			System.out.print("Datum minimalni: ");
			datumMinString = scanner.nextLine();
		} while(!proveriDatum(datumMinString));

		try {
			datumMin = LocalDate.parse(datumMinString, dtf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LocalDate datumMax = null;
		String datumMaxString;
		do {
			System.out.print("Datum maksimalni: ");
			datumMaxString = scanner.nextLine();
		} while(!daLiJeVeciDatum(datumMin, datumMaxString));
		try {
			datumMax = LocalDate.parse(datumMaxString, dtf);
		} catch (Exception e) {
			e.printStackTrace();
		}

		agencija.pretraga9(nazivKlijenta, datumMin, datumMax);
	}

	public static void izmeniTransakciju(Agencija agencija) {

		int id = 0;
		String nazivKlijenta = null;
		String tipTransakcije = null;
		String iznosTransakcije = null;
		double iznos = 0.0;
		String idTransakcije = null;
		String datumUnos = null;
		LocalDate datumZaTransakciju = null;
		do {
			System.out.print("Identifikacioni broj: ");
			idTransakcije = scanner.nextLine();
		} while(!proveraZaBrisanjeIIzmenu(idTransakcije, agencija));
		id = Integer.parseInt(idTransakcije);
		System.out.print("Naziv klijenta: ");
		nazivKlijenta = scanner.nextLine();
		do {
			System.out.print("Tip transakcije: ");
			tipTransakcije = scanner.nextLine();
		} while(!(tipTransakcije.equals("uplata") || tipTransakcije.equals("isplata")));
		do {
			System.out.print("Iznos transakcije: ");
			iznosTransakcije = scanner.nextLine();
		} while(!isDecimalNumber(iznosTransakcije));
		iznos = Double.parseDouble(iznosTransakcije);
		do {
			System.out.print("Datum transakcije: ");
			datumUnos = scanner.nextLine();
		} while(!proveriDatum(datumUnos));
		try {
			datumZaTransakciju = LocalDate.parse(datumUnos, dtf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Transakcija transakcija = new Transakcija(id, nazivKlijenta, datumZaTransakciju, tipTransakcije, iznos);
		Transakcija provera = agencija.izmenaTransakcije(transakcija);
		if(provera != null) {
			System.out.println("Transakcija je uspešno izmenjena.");
		} else {
			System.out.println("Transakcija nije uspešno izmenjena.");
		}
	}


	public static void main(String[] args) {

		Agencija agencija = new Agencija();
		agencija.load("transakcije.txt");

		String answer = null;

		do {

			System.out.println("Meni:");
			System.out.println("1. Unos agencije");
			System.out.println("2. Unos nove transakcije klijenta");
			System.out.println("3. Ispis svih transakcija");
			System.out.println("4. Izmena transakcije");
			System.out.println("5. Brisanje transakcije");
			System.out.println("6. Pretraga transakcija za prosledjenog klijenta");
			System.out.println("7. Pretraga uplata za prosledjenog klijenta");
			System.out.println("8. Prikaz prosecne transakcije isplate za prosledjenog klijenta");
			System.out.println("9. Ispis najvece transakcije uplate za prosledjenog klijenta i opseg datuma");
			System.out.println("10. Ispis podataka o agenciji");
			System.out.println("x. Izlaz");

			answer = scanner.nextLine();

			switch (answer) {
			case "1":
				unesiAgenciju(agencija);
				break;
			case "2":
				unesiTransakciju(agencija);
				agencija.save("transakcije.txt");
				break;
			case "3":
				ispisSvihTransakcija(agencija);
				break;
			case "4":
				izmeniTransakciju(agencija);
				agencija.save("transakcije.txt");
				break;
			case "5":
				brisanjeTransakcije(agencija);
				agencija.save("transakcije.txt");
				break;
			case "6":
				pretragaPoKlijentu(agencija);
				break;
			case "7":
				pretragaPoUplatamaKlijenta(agencija);
				break;
			case "8":
				prikazIsplateZaKlijenta(agencija);
				break;
			case "9":
				ispisNajveceTransakcije(agencija);
				break;
			case "10":
				ispisPodatakaOAgenciji(agencija);
				break;
			case "x":
				break;
			default:
				System.out.println("Pogresan izbor opcije. Pokusajte ponovo.");
			}

		} while (!answer.equals("x"));

		agencija.save("transakcije.txt");
		scanner.close();

	}
}
