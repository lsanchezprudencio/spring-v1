package com.example.demo.util;

import java.util.regex.Pattern;

public class Validaciones {
	static public boolean validaDni(String pDni) {

		boolean success = true;
		
		Pattern patron = Pattern.compile("[0-9]{8}[A-Z a-z]");

		if (pDni.isEmpty()) {
			success = false;
			System.out.println("El dni esta vacio");
		}

		if (pDni.length() != 9) {
			success = false;
			System.out.println("El dni es menor de 9 caracteres");
		}

		if (!patron.matcher(pDni).matches()) {
			success = false;
			System.out.println("El dni no cumple los requsistos");
		}
		
		return success;
	}

}
