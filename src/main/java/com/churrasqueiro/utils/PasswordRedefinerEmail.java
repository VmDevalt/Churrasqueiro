package com.churrasqueiro.utils;

import com.churrasqueiro.data.UsuarioDAO;
import com.churrasqueiro.entities.Usuario;
import com.churrasqueiro.exceptions.DatabaseException;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Optional;

public class PasswordRedefinerEmail {
	private static String remetente = AppConfig.get("EMAIL");
	private static String senhaUsuario = AppConfig.get("EMAIL_PASSWORD");
	private static String enderecoHost = AppConfig.get("EMAIL_HOST_ADDRESS");
	private static String nomeUsuario = AppConfig.get("EMAIL_NAME");

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
            String html = ""
            	    + "<html>"
            	    + "<body style='font-family: Arial, sans-serif;'>"
            	    + "<h2>Olá, " + login + "!</h2>"
            	    + "<p>Aqui está seu código de redefinição de senha do <b>Sistema Churrasqueiro</b>:</p>"
            	    + "<div style='font-size: 22px; font-weight: bold; color: #b30000;'>" + token + "</div>"
            	    + "<p><b>E lembre-se: </b> esse código expira em 45 minutos.</p>"
            	    + "<p style='margin-top:35px;color:#555;'>Observação: Esta é uma mensagem automática.</p>"
            	    + "</body>"
            	    + "</html>";
            	message.setContent(html, "text/html; charset=UTF-8");

            Transport.send(message);
            System.out.println("[DEBUG]: E-mail enviado com sucesso!");

        } catch (MessagingException e) {
        	System.err.println("Erro no envio do E-mail: " + e.getMessage());
            e.printStackTrace();
        } catch (DatabaseException e) {
        	e.printStackTrace();
        	throw new DatabaseException("Erro no banco de dados.");
        }
	}
}
