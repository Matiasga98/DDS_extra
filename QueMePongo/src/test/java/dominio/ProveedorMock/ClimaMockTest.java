package dominio.ProveedorMock;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClimaMockTest {

    ProveedorMock mock;

    @Before
    public void setUp() throws Exception {
        mock = new ProveedorMock();
    }

    @Test
    public void MockTest() {
        assertNotNull(mock.obtenerClima());
    }
}