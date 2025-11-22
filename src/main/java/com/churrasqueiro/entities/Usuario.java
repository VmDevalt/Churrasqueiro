package com.churrasqueiro.entities;

public class Usuario {
	private int id;
	private String login;
	private String senhaHash;
	private String tipo;
	
	public Usuario(int id, String login, String senhaHash, String tipo) {
		this.id = id;
		this.login = login;
		this.senhaHash = senhaHash;
		this.tipo = tipo;
	}
	
	//		Construtor sem atributos
	public Usuario() {
		
	}

	//		Construtor sem atributo ID
	public Usuario(String login, String senhaHash, String tipo) {
		this.login = login;
		this.senhaHash = senhaHash;
		this.tipo = tipo;
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
	
	//		Método toString
	@Override
	public String toString() {
		return  "Usuario { " +
	            "id=" + id +
	            ", login='" + login + '\'' +
	            ", senhaHash='" + senhaHash + '\'' +
	            ", tipo='" + tipo + '\'' +
	            " }";
	}
}




