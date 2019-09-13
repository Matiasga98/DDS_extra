package dominio;

import dominio.Notificadores.Twilio.twilio;
import dominio.clima.ProveedorClima;
import dominio.clima.AccuweatherData.AccuWeather;
import dominio.enumerados.ModoDeRepeticion;
import dominio.Evento;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import java.util.HashSet;

public class twilioTest {
    @Test
    public void testTwilio(){
    	//ProveedorClima mock = new AccuWeather();
        //twilio.MandarWpp(new Evento("Hola Hernan.", mock, LocalDateTime.parse("2019-06-30T23:15:00"), false, ModoDeRepeticion.ANUAL, new Usuario(false), false), new HashSet<Atuendo>());
    }
}
