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
		
		if (usuarioOpt.isEmpty()) {
			throw new ControllerException("Credenciais inválidas.");
		}
		
		System.out.println("Usuario: " + login);
		
		Usuario usuario = usuarioOpt.get();
		
		System.out.println("Senha: " + senha);


		
		String senhaHash = HashPasswordUtil.hashPassword(senha);
		System.out.println("Senha hasheada: " + senhaHash);

		
		if (usuario.getSenhaHash().equals(senhaHash)) {
			System.out.println("DEBUGGGGGGG: " + "Usuário " + usuario.getLogin() + " autenticado como " + usuario.getTipo() + 
					"\n" + "Senha: " + senhaHash + "\n" + "Senha Pura: " + senha);
			return usuario;
		} else {
			throw new ControllerException("Credenciais inválidas.");
		}
	}

}
