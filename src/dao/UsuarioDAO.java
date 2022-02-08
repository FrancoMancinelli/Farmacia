package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Usuario;

public class UsuarioDAO {
	
	final String DB_URL = "jdbc:mysql://localhost/farmacia";
	final String USER = "fran";
	final String PASS = "#IltwwAmh3127";
	
	public boolean login(Usuario usuario) {
		final String QUERY = "SELECT pin FROM usuario "+
							"WHERE pin = '" + usuario.getPin() + "'";
		try { 
			 Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(QUERY);		      
	         return rs.next();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } 
			return false;
	}
}
