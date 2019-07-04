package dominio.Notificadores.Twilio.Gmail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Set;
import dominio.Atuendo;
import dominio.Notificadores.Notificador;

public class gmail implements Notificador {

	@Override
	public void notificar(Set<Atuendo> sugerencias) {
		mandarMail(sugerencias);
	}
	
    public static void mandarMail(Set<Atuendo> sugerencias) {
        final String username = "elapuestoelio@gmail.com";
        final String password = "elapuestoelio";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("diegodanielgagliardi@gmail.com, hernanrodriguez806@gmail.com, matiasgamal98@gmail.com")
            );
            message.setSubject("Se acerca un nuevo evento!! Come de verdad!!");
            message.setText("Se te sugiere que uses los siguientes atuendos para que te veas bonito: " + sugerencias);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}