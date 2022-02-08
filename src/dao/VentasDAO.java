package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Ventas;

public class VentasDAO {
	
	final String DB_URL = "jdbc:mysql://localhost/farmacia";
	final String USER = "fran";
	final String PASS = "#IltwwAmh3127";
	
	/**
	 * Inserta una nueva venta en la Base de Datos con la FECHA ACTUAL
	 * @param v La venta a ser insertada
	 */
	public void nuevaVenta(Ventas v) {
		final String INSERT = "INSERT INTO farmacia.ventas (id, fechaventa, medicamento, cantidad)"
				+ " VALUES ('0', CURDATE(), '"+ String.valueOf(v.getMedicamento().getNombre()) +"', '"
				+ v.getCantidad() +"');";
		try { 
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(INSERT);		      
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Consulta cuantos registros de ventas hay registrados en la FECHA ACTUAL
	 * @return El total de registros, en caso de no haber devuelve 0
	 */
	public int consultaVentas() {
		final String QUERY = "SELECT COUNT(id) FROM farmacia.ventas WHERE fechaventa = CURDATE()";
		try { 
				 Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		         Statement stmt = conn.createStatement();
		         ResultSet rs = stmt.executeQuery(QUERY);		      
		         while(rs.next()){
		        	 	int total = rs.getInt("COUNT(id)");
		        	 	return total;
		         }
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }
				return 0;
	}
}
