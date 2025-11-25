package com.churrasqueiro.business;

import java.util.Optional;

import com.churrasqueiro.data.UsuarioDAO;
import com.churrasqueiro.entities.Usuario;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.HashPasswordUtil;

public class LoginController {
	
	private final UsuarioDAO usuarioDAO;
	
	public LoginController() {
		this.usuarioDAO = new UsuarioDAO();
	}
	
	public Usuario autenticar(String login, String senha) throws ControllerException, DatabaseException {
		
		Optional<Usuario> usuarioOpt = usuarioDAO.buscarPorLogin(login);
		
		validarDados(login, senha);
		
		if (usuarioOpt.isEmpty()) {
			throw new ControllerException("Credenciais inválidas");
		}
		
		Usuario usuario = usuarioOpt.get();
		String senhaHash = HashPasswordUtil.hashPassword(senha);
		
		if (!usuario.getSenhaHash().equals(senhaHash)) {
			throw new ControllerException("Credenciais inválidas.");
		}
		return usuario;
	}
	
	public void validarDados(String login, String senha) throws ControllerException{
		if (login.isEmpty() && senha.isEmpty()) {
			throw new ControllerException("Campos de login e senha vazios.");
		} else if(login.isEmpty() || login == null) {
			throw new ControllerException("Campo de login vazio.");
		} else if(senha.isEmpty() || senha == null){
			throw new ControllerException("Campo de senha vazio.");
		} else {
			System.out.println("Campos preenchidos.\n------------------------------------------------------");
		}
	}
}
