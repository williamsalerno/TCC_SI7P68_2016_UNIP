package br.com.timetrialfactory.maestro.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import br.com.timetrialfactory.maestro.models.User;

@Component
public class EmailSender {
	
	@Autowired
	private static MailSender mailer;

	public void sendConfirmationEmail(User user) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setFrom("timetrial.fac@gmail.com");
		email.setTo(user.getEmail());
		email.setSubject("Confirmação de cadastro");
		email.setText(new StringBuilder()
				.append(user.getFirstName())
				.append(" ")
				.append(user.getLastName())
				.append(", muito prazer!")
				.append("\n")
				.append("Você esta recebendo este e-mail para confirmar seu cadastro no site da TimeTrial Factory. Clique no link abaixo para efetivar a ativação de sua conta.")
				.append("\n")
				.append("COLOCAR O LINK DE ATIVAÇÃO AQUI")
				.append("Esta é uma medida de segurança para garantirmos que o seu e-mail é autêntico. Obrigado e bom divertimento!").toString());
		mailer.send(email);
		}

}
