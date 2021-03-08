package com.example.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
	
	
	public Connection connectDatabase() {
		
		Connection connection = null;

		
        try {
            // We register the MySQL (MariaDB) driver
            // Registramos el driver de MySQL (MariaDB)
           
        	try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de MySQL: " + ex);
            }
            	            // Database connect
            // Conectamos con la base de datos
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/pueblo",
                    
                    //si tuvieramos la base de datos con usuario y password, 
                    //se meteria ahí root usuario y luego contra
                    "root", "");
            boolean valid = connection.isValid(50000);
            
            //otra forma de hacer un if, si valid es true haces test ok si es false, test fail
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
            
            
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error: " + sqle);
        }
    
        
        return connection;
        
    }  
	
	public void cerrarConexion(Connection pConnection){
		try {
        	if(pConnection != null) 
        		pConnection.close();
        		System.out.println("Conexion cerrada");
        		
        		}catch(SQLException e) {
        			System.out.println("Conexion sin cerrar");
        	}
	}
    
	
}
