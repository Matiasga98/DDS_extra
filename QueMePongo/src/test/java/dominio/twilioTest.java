package dominio;

import dominio.Notificadores.Twilio.twilio;
import dominio.Evento;
import org.junit.Test;
import java.util.HashSet;

public class twilioTest {
    @Test
    public void testTwilio(){
        twilio.MandarWpp(new Evento(), new HashSet<Atuendo>());
    }
}
