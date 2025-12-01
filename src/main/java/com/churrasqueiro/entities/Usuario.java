package com.churrasqueiro.entities;

import java.sql.Timestamp;

public class Usuario {
	private int id;
	private String login;
	private String senhaHash;
	private String tipo;
	private String email;
	private String tokenRecuperacao;
	private Timestamp tokenExpiracao;
	
	public Usuario(int id, String login, String senhaHash, String tipo, String email) {
		this.id = id;
		this.login = login;
		this.senhaHash = senhaHash;
		this.tipo = tipo;
		this.email = email;
	}
	
	public Usuario() {
		//CONSTRUTOR VAZIO (SÓ PRA NÃO FICAR SEM NADA DENTRO)
	}

	public Usuario(String login, String senhaHash, String tipo, String email) {
		this.login = login;
		this.senhaHash = senhaHash;
		this.tipo = tipo;
		this.email = email;
	}
	
	public Usuario(String login, String senhaHash, String tipo, String email, String tokenRecuperacao, Timestamp tokenExpiracao) {
		this.login = login;
		this.senhaHash = senhaHash;
		this.tipo = tipo;
		this.email = email;
		this.tokenRecuperacao = tokenRecuperacao;
		this.tokenExpiracao = tokenExpiracao;
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
	
	public String getTokenRecuperacao() {
		return tokenRecuperacao;
	}
	
	public void setTokenRecuperacao(String tokenRecuperacao) {
		this.tokenRecuperacao = tokenRecuperacao;
	}
	
	public Timestamp getTokenExpiracao() {
		return tokenExpiracao;
	}
	
	public void setTokenExpiracao(Timestamp tokenExpiracao) {
		this.tokenExpiracao = tokenExpiracao;
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




