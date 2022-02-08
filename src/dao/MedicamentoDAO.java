package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Medicamento;


public class MedicamentoDAO {
	final String DB_URL = "jdbc:mysql://localhost/farmacia";
	final String USER = "fran";
	final String PASS = "#IltwwAmh3127";
	
	/**
	 * Crea y rellena un ArrayList con los medicamentos registrados en la Base de Datos
	 * @return El ArrayList con los medicamentos
	 */
	public ArrayList<Medicamento> getAll() {
		final String QUERY = "SELECT id, nombre, ppio_activo, fecha, tipo, cantidad, precio"
				+ " FROM medicamento ORDER BY nombre";
		ArrayList<Medicamento> medicamentos = new ArrayList<Medicamento>();
		try { 
				 Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		         Statement stmt = conn.createStatement();
		         ResultSet rs = stmt.executeQuery(QUERY);		      
		         while(rs.next()){
		            //Display values
		        	 	int id = rs.getInt("id");
		        	 	String nombre = rs.getString("nombre");
		        	 	String ppioActivo = rs.getString("ppio_activo");
		        	 	String fecha = rs.getString("fecha");
		        	 	String tipo = rs.getString("tipo");
		        	 	int cantidad = rs.getInt("cantidad");
		        	 	double precio = rs.getDouble("precio");
		        	 	Medicamento m = new Medicamento(id, nombre, ppioActivo, fecha, tipo, cantidad, precio);
		        	 	medicamentos.add(m);
		         }
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }
				return medicamentos;
	}
	
	/**
	 * Actualiza la información del medicamente seleccionado 
	 * @param m El medicamento al que le actualizaremos los datos.
	 */
	public void updateMedicamento (Medicamento m) {
		final String UPDATE = "UPDATE farmacia.medicamento\r\n"
				+ "SET\r\n"
				+ "nombre = '"+m.getNombre()+"',\r\n"
				+ "fecha = '"+m.getFechaIncorp()+"',\r\n"
				+ "tipo = '"+m.getTipo()+"',\r\n"
				+ "precio = '"+m.getPrecio()+"',\r\n"
				+ "cantidad = '"+m.getCantidad()+"' \r\n"
				+ "WHERE id = "+m.getId()+";";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Actualiza la cantidad de medicamentos en stock
	 * @param m El medicamento al que acutalizaremos la cantidad
	 */
	public void updateCantidad (Medicamento m) {
		final String UPDATE = "UPDATE farmacia.medicamento\r\n"
				+ "SET\r\n"
				+ "cantidad = '"+m.getCantidad()+"' \r\n"
				+ "WHERE id = "+m.getId()+";";
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(UPDATE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
