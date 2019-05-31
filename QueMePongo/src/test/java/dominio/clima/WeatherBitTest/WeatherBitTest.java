package dominio.clima.WeatherBitTest;

import dominio.clima.ApixuData.Apixu;
import dominio.clima.weatherBitData.WeatherBit;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WeatherBitTest {
    WeatherBit weatherBit;

    @Before
    public void setUp() throws Exception {
        weatherBit = new WeatherBit();
    }

    @Test
    public void BitTest() {
        assertNotNull(weatherBit.obtenerClima());
    }
}