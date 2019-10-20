package com.ftninformatika.agencija74;

import java.time.LocalDate;

public class Test {

	public static void main(String[] args) {
		Agencija agencija = new Agencija();
		
		Transakcija t1 = new Transakcija(1, "Klijent 1", LocalDate.of(2019, 11, 11), 5000, "isplata");
		Transakcija t2 = new Transakcija(2, "Klijent 2", LocalDate.of(2019, 11, 12), 3000, "uplata");
		Transakcija t3 = new Transakcija(3, "Klijent 1", LocalDate.of(2018, 11, 13), 5000, "isplata");
		
		agencija.dodavanjeTransakcije(t1);
		agencija.dodavanjeTransakcije(t2);
		agencija.dodavanjeTransakcije(t3);
		agencija.ispisTransakcija();
		
		System.out.println();
		agencija.pretraga9("Klijent 2", LocalDate.of(2017, 11, 11), LocalDate.of(2019, 12, 12));
		System.out.println();
		agencija.pretraga8("Klijent 1");
		
		System.out.println();
		agencija.pretraga7("Klijent 2");
		
		System.out.println(agencija);
		
		
	}

}
