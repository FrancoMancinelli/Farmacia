package models;

public class Ventas {
	
	//Atributos
	private int id;
	private String fechaVenta;
	private String medicamento;
	private int cantidad;
	
	
	/**
	 * Construye una venta
	 * @param id ID de la venta
	 * @param fechaVenta Fecha en el que se realiza la venta (YYYY-MM-DD)
	 * @param medicamento Nombre del medicamento vendido
	 * @param cantidad Cantidades vendidas
	 */
	public Ventas(int id, String fechaVenta, String medicamento, int cantidad) {
		super();
		this.id = id;
		this.fechaVenta = fechaVenta;
		this.medicamento = medicamento;
		this.cantidad = cantidad;
	}

	
	//Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	
	
}
