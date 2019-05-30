package dominio.clima.ApixuData;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApixuTest {

    Apixu apixu;

    @Before
    public void setUp() throws Exception {
        apixu = new Apixu();
    }

    @Test
    public void ApixuTest() {
        assertNotNull(apixu.obtenerClima());
    }
}