package dominio;

import dominio.clima.ProveedorClima;
import dominio.clima.AccuweatherData.AccuWeather;
import dominio.enumerados.ModoDeRepeticion;
import dominio.Notificadores.Twilio.Gmail.gmail;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import java.util.HashSet;
import javax.mail.MessagingException;

public class mailTest {
    @Test
    public void testMail() throws MessagingException {
    	//ProveedorClima mock = new AccuWeather();
        //gmail.mandarMail(new Evento("Hola Hernan.", mock, LocalDateTime.parse("2019-06-30T23:15:00"), false, ModoDeRepeticion.ANUAL, new Usuario(false), false), new HashSet<Atuendo>());
    }
}
