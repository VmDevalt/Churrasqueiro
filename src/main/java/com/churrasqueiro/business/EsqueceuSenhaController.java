package com.churrasqueiro.business;

import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.PasswordRedefinerEmail;
import com.churrasqueiro.data.UsuarioDAO;
import com.churrasqueiro.entities.Usuario;
import java.time.LocalDateTime;
import java.util.Optional;

public class EsqueceuSenhaController {

	private final UsuarioDAO usuarioDAO;

	public EsqueceuSenhaController() {
		this.usuarioDAO = new UsuarioDAO();
	}
	
	public static String getDataHora() {
		LocalDateTime dataHora = LocalDateTime.now().plusHours(1);
		return dataHora.toString();
	}
	
	private String gerarToken() {
		int codigo = (int) (Math.random() * 900000) + 100000;
		return String.valueOf(codigo);
	}
	
	public String enviarToken(String email) throws ControllerException, DatabaseException{
		
		if(email.isEmpty()) {
			throw new ControllerException("Campo de email vazio.");
		}
		
		Optional<Usuario> usuarioOpt = usuarioDAO.buscarEmail(email);
		
		if(usuarioOpt.isEmpty()) {
			throw new ControllerException("E-mail não cadastrado, digite outro.");
		}
		
		email = usuarioOpt.get().getEmail();
		String token = gerarToken();
		UsuarioDAO.atualizarToken(token, EsqueceuSenhaController.getDataHora(), email);
		PasswordRedefinerEmail.enviarEmail(token, email);
		
		return token;
	}
	
	public void validarCodigo(String codigoDigitado, String codigoReal) throws ControllerException {
		if(codigoDigitado == null || codigoDigitado.isEmpty()) {
			throw new ControllerException("Digite o código enviado para seu e-mail.");
		}
		
		if(codigoDigitado.length() < 6) {
			throw new ControllerException("Códigos de verificação possuem 6 caractéres.");
		}
		
		if(!codigoDigitado.equals(codigoReal)) {
			throw new ControllerException("Os códigos não coincidem.");
		}
	}
}
