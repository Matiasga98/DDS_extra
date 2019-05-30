package dominio.clima.AccuweatherData;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccuweatherTest {

    Accuweather accuweather;

    @Before
    public void setUp() throws Exception {
        accuweather = new Accuweather();
    }

    @Test
    public void AccuweatherTest() {
        assertNotNull(accuweather.obtenerClima());
    }
}