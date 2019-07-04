package dominio.Notificadores.Twilio.Gmail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class gmail {

    public static void mandarMail() {
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
            message.setSubject("Tu cuenta de SHAUAUA esta a punto de expirar");
            message.setText("Estimado señor \"Deko\","
                    + "\n\n Le enviamos esta notificacion para recordarle que renueve su subscripcion a SHAUAUA anual. Por favor pague la cuota correspondiente y realice el KERNEl. Si realiza este trabajo en tiempo y forma, perseguiremos acciones legales. \n\n Queda usted notificado. \n\n Este mail ha sido enviado desde Java (c)");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
