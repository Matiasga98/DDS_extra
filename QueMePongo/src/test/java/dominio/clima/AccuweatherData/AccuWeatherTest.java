package dominio.clima.AccuweatherData;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AccuWeatherTest {

    AccuWeather accuweather;

    @Before
    public void setUp() throws Exception {
        accuweather = new AccuWeather();
    }

    @Test
    public void AccuweatherTest() {
        assertNotNull(accuweather.temperatura(LocalDateTime.now().plusHours(4)));
    }
}