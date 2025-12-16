package com.churrasqueiro.business;

import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;
import com.churrasqueiro.utils.PasswordRedefinerEmail;
import com.churrasqueiro.data.UsuarioDAO;
import com.churrasqueiro.entities.Usuario;
import com.churrasqueiro.utils.HashPasswordUtil;
import java.time.LocalDateTime;
import java.util.Optional;
import java.sql.Timestamp;

public class EsqueceuSenhaController {
	private final UsuarioDAO usuarioDAO;

	public EsqueceuSenhaController() {
		this.usuarioDAO = new UsuarioDAO();
	}
	
	public static LocalDateTime getDataHoraAvancada() {
		LocalDateTime dataHora = LocalDateTime.now().plusMinutes(45);
		return dataHora;
	}
	
	private String gerarToken() {
		int codigo = (int) (Math.random() * 900000) + 100000;
		return String.valueOf(codigo);
	}
	
	public String enviarToken(String email) throws ControllerException, DatabaseException {
	    Optional<Usuario> usuarioOpt = UsuarioDAO.buscarLoginViaEmail(email);

	    if (email == null || email.isEmpty()) {
	        throw new ControllerException("Campo de email vazio.");
	    }

	    if (usuarioOpt.isEmpty()) {
	        throw new ControllerException("E-mail não cadastrado, digite outro.");
	    }
	    
	    email = usuarioOpt.get().getEmail();

	    String token = gerarToken();
	    
	    LocalDateTime dataHoraExpiracao = getDataHoraAvancada();
	    
	    usuarioDAO.atualizarTokenViaEmail(token, email);
	    
	    usuarioDAO.atualizarDataHoraViaEmail(Timestamp.valueOf(dataHoraExpiracao), email);

	    PasswordRedefinerEmail.enviarEmail(token, email);
	    
	    return token;
	}

	
	public void validarCodigo(String codigoDigitado, String codigoReal) throws ControllerException, DatabaseException {
		Optional<Usuario> usuarioOpt = UsuarioDAO.buscarLoginViaToken(codigoReal);
		
		if(codigoDigitado == null || codigoDigitado.isEmpty()) {
			throw new ControllerException("Digite o código enviado para seu e-mail.");
		}
		
		if(codigoDigitado.length() < 6 || codigoDigitado.length() > 6) {
			throw new ControllerException("Códigos de verificação possuem 6 caractéres.");
		}
		
		if(!codigoDigitado.equals(codigoReal)) {
			throw new ControllerException("Os códigos não coincidem.");
		}
		
		LocalDateTime dataHoraBanco = usuarioOpt.get().getTokenExpiracao().toLocalDateTime();
		
		LocalDateTime dataHoraAtual = LocalDateTime.now();
		
		if(dataHoraAtual.isAfter(dataHoraBanco)) {
			usuarioDAO.atualizarTokenViaEmail(null, usuarioOpt.get().getEmail());
			throw new ControllerException("O token já expirou.");
		}
	}
	
	public void redefinirSenha(String senha, String email) throws ControllerException, DatabaseException {
		Optional<Usuario> usuarioOpt = UsuarioDAO.buscarLoginViaEmail(email);
		
		String senhaBanco = usuarioOpt.get().getSenhaHash();
		
		String senhaHash = HashPasswordUtil.hashPassword(senha);
		
		if(senhaBanco.equals(senhaHash)) {
			throw new ControllerException("A senha nova é igual a senha registrada no banco. Digite uma diferente.");
		}
		
		usuarioDAO.atualizarSenhaViaEmail(senhaHash, email);
	}
	
	public void validarCamposDeSenha(String senha, String senhaConfirmada) throws ControllerException {
		if((senha == null || senha.isEmpty()) && (senhaConfirmada == null || senhaConfirmada.isEmpty())) {
			throw new ControllerException("Campos vazios.");
		} else if (senha == null || senha.isEmpty()) {
			throw new ControllerException("Preencha o campo de senha.");
		} else if (senhaConfirmada == null || senhaConfirmada.isEmpty()) {
			throw new ControllerException("Confirme a senha.");
		}  else if (senha.length() < 6 || senhaConfirmada.length() < 6) {
			throw new ControllerException("As senha deve ter no mínimo 6 caracteres.");
		} else if (!senha.equals(senhaConfirmada)) {
			throw new ControllerException("As senhas não coincidem.");
		}
	}
}

