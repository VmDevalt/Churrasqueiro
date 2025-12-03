package com.churrasqueiro.utils;

import com.churrasqueiro.data.UsuarioDAO;
import com.churrasqueiro.entities.Usuario;
import com.churrasqueiro.exceptions.ControllerException;
import com.churrasqueiro.exceptions.DatabaseException;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Optional;

public class PasswordRedefinerEmail {
	private static Dotenv dotenv = Dotenv.load();
	private static String remetente = dotenv.get("EMAIL");
	private static String enderecoHost = dotenv.get("EMAIL_ENDERECO_HOST");
	private static String nomeUsuario = dotenv.get("EMAIL");
	private static String senhaUsuario = dotenv.get("EMAIL_PASSWORD");

	public static void enviarEmail(String token, String destinatario) throws DatabaseException {		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", PasswordRedefinerEmail.enderecoHost);
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
				new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(PasswordRedefinerEmail.nomeUsuario, PasswordRedefinerEmail.senhaUsuario);
			}
		});
		
		try {
			Optional<Usuario> usuarioOpt = UsuarioDAO.buscarLoginViaEmail(destinatario);
			String login = usuarioOpt.get().getLogin();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(PasswordRedefinerEmail.remetente));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject("código de redefinição de senha".toUpperCase());
            message.setText("Olá, " + login + "!\n\n"
            		+ "Aqui está seu código de redefinição de senha do Sistema Churrasqueiro: " + token + "\n\n"
            		+ "Observação: Esta é uma mensagem automática.");

            Transport.send(message);
            System.out.println("Email Message Sent Successfully!");

        } catch (MessagingException e) {
        	System.err.println("Erro no envio do E-mail: " + e.getMessage());
            e.printStackTrace();
        } catch (DatabaseException e) {
        	e.printStackTrace();
        	throw new DatabaseException("Erro no banco de dados.");
        }
	}
}
