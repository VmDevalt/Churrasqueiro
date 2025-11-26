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
	
	public Usuario cadastrar(String login, String tipo, String email, String senha, String senhaConfirmada) throws ControllerException, DatabaseException {

		Optional<Usuario> usuarioOpt = usuarioDAO.buscarEmail(email);
		
		if(!usuarioOpt.isEmpty()) {
			throw new ControllerException("E-Mail já cadastrado! Utilize outro.");
		}
		
		validarDados(login, email, senha, senhaConfirmada);
		//TODO: APLICAR METODO QUE VALIDA O E-MAIL
		
		if(!senhaConfirmada.equals(senha)) {
			throw new ControllerException("As senhas não coincidem.");
		}
		
		String senhaHash = HashPasswordUtil.hashPassword(senha);
		Usuario usuario = new Usuario(login, senhaHash, tipo, email);
		
		try{
			Usuario novoUsuario = usuarioDAO.cadastrarUsuario(usuario);
			return novoUsuario;
		} catch (DatabaseException e) {
			throw new DatabaseException("Erro ao cadastrar usuário.");
		}
	}
	
	public void validarDados(String nome, String email, String senha, String senhaConfirmada) throws ControllerException{
		if((nome.isEmpty() || nome == null) && (email.isEmpty() || email == null) && (senha.isEmpty() || senha == null) && (senhaConfirmada.isEmpty() || senhaConfirmada == null)) {
			throw new ControllerException("Todos os campos devem ser preenchidos.");
		} else if(nome == null || nome.isEmpty()) {
			throw new ControllerException("Campo de nome vazio.");
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
	
	//TODO: METODO DE VALIDAÇÃO DO E-MAIL (SE CONTÉM '@' E '.')
}
