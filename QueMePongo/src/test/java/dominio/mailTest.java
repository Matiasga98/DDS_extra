package dominio;

import dominio.Notificadores.Twilio.Gmail.gmail;
import org.junit.Test;

import javax.mail.MessagingException;

public class mailTest {
    @Test
    public void testMail() throws MessagingException {
        gmail.mandarMail();
    }
}