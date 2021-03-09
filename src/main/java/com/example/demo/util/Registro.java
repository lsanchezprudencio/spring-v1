package com.example.demo.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.dao.Persona;

public class Registro {

	private static final String TELEFONO = "telefono";
	private static final String APELLIDO2 = "apellido2";
	private static final String APELLIDO1 = "apellido1";
	private static final String NOMBRE = "nombre";
	private static final String DNI = "dni";

	public Persona buscarCiudadanoCampo(String pCondicion, String pCampo) {
		// ConectandoBD
		Conector javaMySQLBasic = new Conector();
		Connection conn = javaMySQLBasic.connectDatabase();
		Persona personaAux = new Persona();

		ResultSet resultQ = null;

		// me buscas a la persona
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT* FROM personas WHERE " + pCampo + " = ? ");
			stmt.setString(1, pCondicion);

			resultQ = stmt.executeQuery();

			while (resultQ.next()) {
				// Me metes datos en la persona Aux, los datos de la persona seleccionada.
				// resultQ.next() devuelve true si hay linea, las va recorriendo.

				// TO DO
				personaAux.setDni(resultQ.getString(DNI));
				personaAux.setNombre(resultQ.getString(NOMBRE));
				personaAux.setApellido1(resultQ.getString(APELLIDO1));
				personaAux.setApellido2(resultQ.getString(APELLIDO2));
				personaAux.setNumTelefono(resultQ.getString(TELEFONO));

			}
		} catch (SQLException e) {
			System.out.println("Error al buscar ciudadano por " + pCampo + System.lineSeparator() + e.getMessage());
		} finally {
			javaMySQLBasic.cerrarConexion(conn);
		}

		return personaAux;
	}

	// que me devuelva todos los ciudadanos
	public List<Persona> listarCiudadanos() {
		// ConectandoBD
		Conector javaMySQLBasic = new Conector();
		Connection conn = javaMySQLBasic.connectDatabase();

		List<Persona> listPersonas = new ArrayList<>();

		// ResultSet, tipo de dato de las bbdd
		ResultSet resultQ = null;

		// me buscas a la persona
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT* FROM personas");

			resultQ = stmt.executeQuery();

			while (resultQ.next()) {
				// Me metes datos en la persona Aux, los datos de la persona seleccionada.
				// resultQ.next() devuelve true si hay linea, las va recorriendo.

				// TO DO
				Persona personaAux = new Persona();

				personaAux.setDni(resultQ.getString(DNI));
				personaAux.setNombre(resultQ.getString(NOMBRE));
				personaAux.setApellido1(resultQ.getString(APELLIDO1));
				personaAux.setApellido2(resultQ.getString(APELLIDO2));
				personaAux.setNumTelefono(resultQ.getString(TELEFONO));

				listPersonas.add(personaAux);

			}
		} catch (SQLException e) {
			System.out.println("Error al listar ciudadano por " + System.lineSeparator() + e.getMessage());
		} finally {
			javaMySQLBasic.cerrarConexion(conn);
		}

		return listPersonas;
	}

	// Eliminar un ciudadano

	public int eliminarCiudadano(String pDni) {
		// ConectandoBD
		Conector javaMySQLBasic = new Conector();
		Connection conn = javaMySQLBasic.connectDatabase();

		int count = 0;

		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM personas WHERE DNI = ? ");
			stmt.setString(1, pDni);

			count = stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			javaMySQLBasic.cerrarConexion(conn);
		}

		if (count == 1) {
			System.out.println(count + " persona eliminada");
		} else {
			System.out.println("Error al eliminar personas");
		}

		return count;

	}

	public int insertarCiudadano(Persona pCiudadano) {

		Conector javaMySQLBasic = new Conector();
		Connection con = javaMySQLBasic.connectDatabase();
		int count = 0;

		try {

			PreparedStatement stmt = con.prepareStatement("INSERT INTO personas VALUES (?,?,?,?,?,?)");
			stmt.setString(1, pCiudadano.getDni());
			stmt.setString(2, pCiudadano.getNombre());
			stmt.setString(3, pCiudadano.getApellido1());
			stmt.setString(4, pCiudadano.getApellido2());
			stmt.setDate(5, null);
			stmt.setString(6, pCiudadano.getNumTelefono());

			count = stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			javaMySQLBasic.cerrarConexion(con);
		}

		if (count != 0) {

			System.out.println(count + " personas insertadas ");

		} else {

			System.out.println(" Error ha vevuelto un " + count);
		}

		return count;

	}
	
	
	
	public int updateCiudadano(Persona pCiudadano) {

		Conector javaMySQLBasic = new Conector();
		Connection con = javaMySQLBasic.connectDatabase();
		int count = 0;

		try {

			PreparedStatement stmt = con.prepareStatement("UPDATE personas SET nombre = ?,apellido1 = ?, apellido2 = ?, telefono = ? where dni = ?");
			stmt.setString(1, pCiudadano.getNombre());
			stmt.setString(2, pCiudadano.getApellido1());
			stmt.setString(3, pCiudadano.getApellido2());
			stmt.setString(4, pCiudadano.getNumTelefono());
			stmt.setString(5, pCiudadano.getDni());

			count = stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			javaMySQLBasic.cerrarConexion(con);
		}

		if (count != 0) {

			System.out.println(count + " personas actualizadas ");

		} else {

			System.out.println(" Error ha vevuelto un " + count);
		}

		return count;

	}


}
