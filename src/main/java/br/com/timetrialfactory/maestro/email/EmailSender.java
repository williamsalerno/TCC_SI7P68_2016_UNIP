package br.com.timetrialfactory.maestro.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import br.com.timetrialfactory.maestro.dto.UserDTO;

@Component
public class EmailSender {

	@Autowired
	private MailSender mailer;

	public void sendConfirmationEmail(UserDTO user) {
		if (user != null) {
			SimpleMailMessage email = new SimpleMailMessage();
			email.setFrom("timetrial.fac@gmail.com");
			email.setTo(user.getEmail());
			email.setSubject("Confirmação de cadastro");
			email.setText(new StringBuilder().append(user.getFirstName()).append(" ").append(user.getLastName())
					.append(", muito prazer!").append("\n\n")
					.append("Você esta recebendo este e-mail para confirmar seu cadastro no site da TimeTrial Factory. Clique no link abaixo para efetivar a ativação de sua conta.")
					.append("\n\n").append("http://localhost:8080/newUser?confirmationCode=")
					.append(user.getActivationCode().toString()).append("\n\n")
					.append("Esta é uma medida de segurança para garantirmos que o seu e-mail é autêntico. Obrigado e bom divertimento!")
					.toString());
			mailer.send(email);
		}
	}

	public void sendRecoveryPasswordEmail(String username, String emailUser, String password) {
		if (username != null && emailUser != null && password != null) {
			SimpleMailMessage email = new SimpleMailMessage();
			email.setFrom("timetrial.fac@gmail.com");
			email.setTo(emailUser);
			email.setSubject("Recuperação de Senha");
			email.setText(new StringBuilder().append("Saudações!").append("\n\n")
					.append("Você requisitou uma recuperação de senha para a conta ").append(username).append("\n")
					.append("Senha: ").append(password).append("\n\n").append("Bom divertimento!").toString());
			mailer.send(email);
		}
	}

	public void sendMessage(String sender, String subject, String message) {
		if (sender != null && subject != null && message != null) {
			SimpleMailMessage email = new SimpleMailMessage();
			email.setFrom(sender);
			email.setTo("timetrial.fac@gmail.com");
			email.setSubject(subject);
			email.setText(message);
			mailer.send(email);
		}
	}
}
