package dominio;

import dominio.Notificadores.Twilio.twilio;
import org.junit.Test;
import java.util.HashSet;

public class twilioTest {
    @Test
    public void testTwilio(){
        twilio.MandarWpp(new HashSet<Atuendo>());
    }
}
