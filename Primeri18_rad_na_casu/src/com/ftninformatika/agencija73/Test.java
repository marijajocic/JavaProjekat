package com.ftninformatika.agencija73;

import java.time.LocalDate;

public class Test {

	public static void main(String[] args) {
		Agencija agencija = new Agencija();
		
		Transakcija t1 = new Transakcija(1, "Klijent 1", LocalDate.of(2018, 12, 12), 5000, "uplata");
		Transakcija t2 = new Transakcija(2, "Klijent 1", LocalDate.of(2018, 11, 12), 3000, "isplata");
		Transakcija t3 = new Transakcija(3, "Klijent 2", LocalDate.of(2019, 12, 12), 5000, "uplata");
		
		agencija.dodavanjeTransakcije(t1);
		agencija.dodavanjeTransakcije(t2);
		agencija.dodavanjeTransakcije(t3);
		
		agencija.ispisTransakcija();
	}

}
