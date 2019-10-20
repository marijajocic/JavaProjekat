package zadatak;

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
	
    public static boolean proveraZaBrisanjeIIzmenu(String string, Agencija agencija) {

	try {
		int broj = Integer.parseInt(string);
		if(broj < 1) {
			return false;
		}
		ArrayList<Prevoz> lista = agencija.getListaPrevoza();
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

         public static boolean proveraZaDodavanje(String string, Agencija agencija) {

	    try {
		int broj = Integer.parseInt(string);
		if(broj < 1) {
			return false;
		}
		ArrayList<Prevoz> lista = agencija.getListaPrevoza();
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
    

    public static void ispisSvihPrevoza (Agencija agencija) {
    	   agencija.ispisPrevoza();
    }
 

}
   