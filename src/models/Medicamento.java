package models;

public class Medicamento {
	
	//Atributos
	private int id;
	private String nombre;
	private String ppioActivo;
	private String fechaIncorp;
	private String tipo;
	private int cantidad;
	private double precio;
	
	//Constructores
	/**
	 * Construye un medicamento
	 * @param nombre Nombre del medicamento
	 * @param ppioActivo Principio Activo del medicamento
	 * @param fechaIncorp Fecha de incorporación al mercado (YYYY-MM-DD)
	 * @param tipo El tipo de medicamento
	 * @param cantidad La cantidad en stock del medicamento
	 * @param precio El precio del medicamento
	 */
	public Medicamento(int id, String nombre, String ppioActivo, String fechaIncorp, String tipo, int cantidad, double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ppioActivo = ppioActivo;
		this.fechaIncorp = fechaIncorp;
		this.tipo = tipo;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	//Getters & Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPpioActivo() {
		return ppioActivo;
	}

	public void setPpioActivo(String ppioActivo) {
		this.ppioActivo = ppioActivo;
	}

	public String getFechaIncorp() {
		return fechaIncorp;
	}

	public void setFechaIncorp(String fechaIncorp) {
		this.fechaIncorp = fechaIncorp;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
