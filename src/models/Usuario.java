package models;

public class Usuario {

	//Atributos
	private int id;
	private String pin;
	
	//Constructores
	/**
	 * Construye un usuario
	 * @param id ID del usuario
	 * @param password Contraseña del usuario (de 4 digitos)
	 */
	public Usuario(int id, String password) {
		super();
		this.id = id;
		this.pin = password;
	}

	
	//Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}
	
	
	
}
