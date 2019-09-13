package dominio;

import dominio.Notificadores.Twilio.Gmail.gmail;
import org.junit.Test;
import dominio.Evento;
import java.util.HashSet;
import javax.mail.MessagingException;

public class mailTest {
    @Test
    public void testMail() throws MessagingException {
    	gmail.mandarMail(new Evento(), new HashSet<Atuendo>());
    }
}
