package dominio;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class ImagenTest {

    Imagen imagen;

    String direccionDeOrigenDeImagen;
    String direccionDeDestinoDeImagen;

    int anchoNuevo;
    int largoNuevo;

    @Before
    public void setUp() throws Exception {

        anchoNuevo = 200;
        largoNuevo = 200;

        //MODIFICAR LA LOCACION EN PARA SU COMPU EN CADA CASO
        direccionDeOrigenDeImagen = "../../imagenes/campera.png";
        direccionDeDestinoDeImagen = "../../imagenes/campera-normalizada.png";

        imagen = new Imagen();

        imagen.setAnchoDeDestino(anchoNuevo);
        imagen.setLargoDeDestino(largoNuevo);

    }

    /*@Test
    public void cargarImagen() throws IOException {
        imagen.cargarImagen(direccionDeOrigenDeImagen,direccionDeDestinoDeImagen);
        File imagenGuardada = new File(direccionDeDestinoDeImagen);
        assertNotNull(imagenGuardada);
    }*/
}