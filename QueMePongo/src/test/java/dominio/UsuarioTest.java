package dominio;

import dominio.clima.AccuweatherData.Accuweather;
import dominio.clima.ApixuData.Apixu;
import dominio.ProveedorMock.ProveedorMock;
import dominio.enumerados.Categoria;
import dominio.enumerados.Material;
import dominio.enumerados.Tipo;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class UsuarioTest {

    Borrador borrador;
    Usuario usuario;


    //COLORES
    Color negro;
    Color rojo;
    Color blanco;
    Color azul;
    Color verde;
    Color amarillo;

    List<Color> colores;

    @Before
    public void setUp() throws IOException {

        borrador = new Borrador();
        usuario = new Usuario();
        colores = colores();

    }


    @Test
    public void usuarioSinGuardarropaPidenSugerencias() {
        assertTrue(usuario.guardarropas().isEmpty());
        assertTrue(usuario.sugerenciasDeAtuendosDeTodosLosGuardarropas().isEmpty());
    }

    @Test
    public void usuarioConUnGuardarropa() {

        assertTrue(usuario.guardarropas().isEmpty());

        agregoUnGuardarropa();

        assertEquals(1, usuario.guardarropas().size());


        Guardarropa guardarropaDelUsuarie = usuario.guardarropas().iterator().next();

        assertEquals(6, guardarropaDelUsuarie.prendasSegunCategoria(Categoria.PARTE_SUPERIOR).size());
        assertEquals(6, guardarropaDelUsuarie.prendasSegunCategoria(Categoria.PARTE_INFERIOR).size());
        assertEquals(1, guardarropaDelUsuarie.prendasSegunCategoria(Categoria.CALZADO).size());
        assertTrue(guardarropaDelUsuarie.prendasSegunCategoria(Categoria.ACCESORIOS).isEmpty());

        assertEquals(36, usuario.sugerenciasDeAtuendosDeTodosLosGuardarropas().size());

    }

    @Test
    public void usuarioConDosGuardarropa() {

        assertTrue(usuario.guardarropas().isEmpty());

        agregoUnGuardarropa();

        assertEquals(1, usuario.guardarropas().size());

        agregoUnGuardarropa();

        assertEquals(2, usuario.guardarropas().size());

        assertEquals(72, usuario.sugerenciasDeAtuendosDeTodosLosGuardarropas().size());

    }

    public void agregoUnGuardarropa() {
        //REMERAS LISAS DE DISTINTOS COLORES
        Guardarropa guardarropa = new Guardarropa();

        borrador.definirTipo(Tipo.REMERA);
        borrador.definirMaterial(Material.ALGODON);

        colores.forEach(color -> {
            borrador.definirColorPrimario(color);
            guardarropa.agregarPrendas(borrador.crearPrenda());
        });

        //PANTALONES LISOS DE DISTINTOS COLORES
        borrador.definirTipo(Tipo.PANTALON);
        borrador.definirMaterial(Material.JEAN);

        colores.forEach(color -> {
            borrador.definirColorPrimario(color);
            guardarropa.agregarPrendas(borrador.crearPrenda());
        });

        //CALZADO
        borrador.definirTipo(Tipo.ZAPATO);
        borrador.definirMaterial(Material.CUERO);
        borrador.definirColorPrimario(negro);
        guardarropa.agregarPrendas(borrador.crearPrenda());

        usuario.agregarGuardarropa(guardarropa);

    }

    public List<Color> colores() {
        negro = new Color(0, 0, 0);
        rojo = new Color(255, 0, 0);
        blanco = new Color(255, 255, 255);
        azul = new Color(0, 0, 255);
        verde = new Color(0, 100, 0);
        amarillo = new Color(255, 233, 0);

        return asList(negro, rojo, blanco, azul, verde, amarillo);
    }
}