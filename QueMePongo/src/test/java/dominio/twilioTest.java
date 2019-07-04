package dominio;

import dominio.Notificadores.Twilio.twilio;
import org.junit.Test;

public class twilioTest {
    @Test
    public void testTwilio(){
        twilio.MandarWpp();
    }
}
