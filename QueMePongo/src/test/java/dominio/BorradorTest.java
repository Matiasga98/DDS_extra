package dominio;

import dominio.enumerados.Categoria;
import dominio.enumerados.Material;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;
import dominio.excepciones.MaterialInconsistente;
import dominio.excepciones.RequiereColorDistinto;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class BorradorTest {

    Borrador borrador;

    //COLORES
    Color negro;
    Color rojo;
    Color blanco;
    Color azul;
    Color verde;
    Color amarillo;

    @Before
    public void setUp() {

        borrador = new Borrador();

        negro = new Color(0, 0, 0);
        rojo = new Color(255, 0, 0);
        blanco = new Color(255, 255, 255);
        azul = new Color(0, 0, 255);
        verde = new Color(0, 100, 0);
        amarillo = new Color(255, 233, 0);

        //COMPLETAMOS
        borrador.definirNombre("Jeans");
        borrador.definirTipo(Tipo.PANTALON);
        borrador.definirMaterial(Material.JEAN);
        borrador.definirColorPrimario(azul);
        borrador.definirColorSecundario(blanco);
    }


    @Test(expected = NullPointerException.class)
    public void tiraExcepcionPorTenerTipoNull() {
        borrador.definirTipo(null);
        borrador.crearPrenda();
    }


    @Test(expected = NullPointerException.class)
    public void tiraExcepcionPorTenerMaterialNull() {
        borrador.definirMaterial(null);
        borrador.crearPrenda();
    }

    @Test(expected = NullPointerException.class)
    public void tiraExcepcionPorTenerColorPrimarioNull() {
        borrador.definirColorPrimario(null);
        borrador.crearPrenda();
    }

    @Test(expected = RequiereColorDistinto.class)
    public void tiraExcepcionPorTenerColorPrimarioYSecundarioIguales() {
        borrador.definirColorPrimario(rojo);
        borrador.definirColorSecundario(rojo);
        borrador.crearPrenda();
    }

    @Test(expected = MaterialInconsistente.class)
    public void tiraExcepcionPorTenerMaterialInconsistenteConElTipo() {
        borrador.definirTipo(Tipo.REMERA);
        borrador.definirMaterial(Material.CUERO);
        borrador.crearPrenda();
    }

    @Test
    public void tieneColorSecundarioNullPeroEsOpcional() {
        borrador.definirColorSecundario(null);
        assertNotNull(borrador.crearPrenda());
    }

    @Test
    public void cumpleTodosLosRequerimientosCreaSinProblemas() {
        Prenda prenda = borrador.crearPrenda();

        assertNotNull(prenda);
        assertEquals("Jeans", prenda.nombre());
        assertEquals(Tipo.PANTALON, prenda.tipo());
        assertEquals(Material.JEAN, prenda.material());
        assertEquals(Trama.LISA, prenda.trama());
        assertEquals(Categoria.PARTE_INFERIOR, prenda.categoria());
        assertEquals(azul, prenda.colorPrimario());
        assertEquals(blanco, prenda.colorSecundario());
    }
}