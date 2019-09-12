package dominio.Notificadores.Twilio.Gmail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.persistence.*;
import dominio.Atuendo;
import dominio.Notificadores.Notificador;
import dominio.Evento;

@Entity
@DiscriminatorValue(value="G")
public class gmail extends Notificador {

	@Override
	public void notificar(Evento evento, HashSet<Atuendo> sugerencias) {
		mandarMail(evento, sugerencias);
	}
	
    public static void mandarMail(Evento evento, HashSet<Atuendo> sugerencias) {
        final String username = "elapuestoelio@gmail.com";
        final String password = "elapuestoelio";
        String submensaje;
        String mensaje;
        
        if (evento.tieneAlertasMeteorologicas) {
        	submensaje = "Tu evento se ve comprometido por una alerta meteorologica.";
        	mensaje = "En base a esta alerta reformulamos tus atuendos disponibles para que te sientas cómodo: ";
        }
        else {
        	submensaje = "Se acerca un nuevo evento!! Come de verdad!!";
            mensaje = "Se te sugiere que uses los siguientes atuendos para que te veas bonito: ";
        }
        
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
            message.setSubject(submensaje);
            message.setText(mensaje + sugerencias);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
