package com.churrasqueiro.business;

import com.churrasqueiro.data.UsuarioDAO;
import com.churrasqueiro.entities.Usuario;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.HashPasswordUtil;
import java.util.Optional;

public class CadastroUsuarioController {
	private final UsuarioDAO usuarioDAO;
	
	public CadastroUsuarioController() {
		this.usuarioDAO = new UsuarioDAO();
	}
	
	public Usuario cadastrar(String nome, String tipo, String email, String senha, String senhaConfirmada) throws ControllerException, DatabaseException {

		validarDados(nome, tipo, email, senha, senhaConfirmada);
		
		String senhaHash = HashPasswordUtil.hashPassword(senha);
		Usuario usuario = new Usuario(nome, senhaHash, tipo, email);
		
		try{
			Usuario novoUsuario = usuarioDAO.cadastrarUsuario(usuario);
			return novoUsuario;
		} catch (DatabaseException e) {
			throw new DatabaseException("Erro ao cadastrar usuário.");
		}
		
		//TODO: VALIDAÇÃO DA SENHA E SENHA CONFIRMADA
		//TODO: VALIDAÇÃO DA EXISTÊNCIA DE UM ENDEREÇO DE EMAIL NO BD
		
	}
	
	public void validarDados(String nome, String tipo, String email, String senha, String senhaConfirmada) throws ControllerException{
		if((nome.isEmpty() || nome == null) && (tipo.isEmpty() || tipo == null) && (email.isEmpty() || email == null) && (senha.isEmpty() || senha == null) && (senhaConfirmada.isEmpty() || senhaConfirmada == null)) {
			throw new ControllerException("Todos os campos devem ser preenchidos.");
		} else if(nome == null || nome.isEmpty()) {
			throw new ControllerException("Campo de nome vazio.");
		} else if(tipo == null || tipo.isEmpty()) {
			throw new ControllerException("Campo de tipo vazio.");
		} else if(email == null || email.isEmpty()) {
			throw new ControllerException("Campo de e-mail vazio.");
		} else if (senha == null || senha.isEmpty()) {
			throw new ControllerException("Campo de senha vazio.");
		} else if (senhaConfirmada == null || senhaConfirmada.isEmpty()) {
			throw new ControllerException("Confirme a senha do usuário.");
		} else {
			System.out.println("Campos preenchidos.\n------------------------------------------------------");
		}
	}
}
