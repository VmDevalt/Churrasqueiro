package com.churrasqueiro.entities;

public class Usuario {
	private int id;
	private String login;
	private String senhaHash;
	private String tipo;
	private String email;
	
	public Usuario(int id, String login, String senhaHash, String tipo, String email) {
		this.id = id;
		this.login = login;
		this.senhaHash = senhaHash;
		this.tipo = tipo;
		this.email = email;
	}
	
	//Construtor sem atributos
	public Usuario() {
		
	}

	//Construtor sem atributo ID
	public Usuario(String login, String senhaHash, String tipo, String email) {
		this.login = login;
		this.senhaHash = senhaHash;
		this.tipo = tipo;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenhaHash() {
		return senhaHash;
	}
	
	public void setSenhaHash(String senhaHash) {
		this.senhaHash = senhaHash;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	//Método toString
	@Override
	public String toString() {
		return  "Usuario { " +
	            "id=" + id +
	            ", login='" + login + '\'' +
	            ", senhaHash='" + senhaHash + '\'' +
	            ", tipo='" + tipo + '\'' +
	            ", email='" + email + '\'' +
	            " }";
	}
}




