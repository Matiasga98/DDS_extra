package dominio;

import dominio.enumerados.Categoria;
import dominio.enumerados.Material;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class GuardarropaTest {


    Borrador borrador;
    Guardarropa guardarropa;


    //COLORES
    Color negro;
    Color rojo;
    Color blanco;
    Color azul;
    Color verde;
    Color amarillo;

    List<Color> colores;
    private Atuendo testing;


    @Before
    public void setUp() {
        borrador = new Borrador();
        guardarropa = new Guardarropa();
        colores = colores();


        //REMERAS LISAS DE DISTINTOS COLORES
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

        Prenda remeraNegra = new Prenda(Tipo.REMERA, Material.ALGODON, Trama.LISA, negro, null);
        Prenda pantalonRojo = new Prenda(Tipo.PANTALON, Material.JEAN, Trama.LISA, rojo, null);
        Prenda zapatosAzul = new Prenda(Tipo.ZAPATO, Material.CUERO, Trama.LISA, azul, null);
        Prenda buzoBlanco = new Prenda(Tipo.BUZO, Material.ALGODON, Trama.LISA, blanco, null);
        Prenda anilloLinternaVerde = new Prenda(Tipo.ANILLO, Material.ORO, Trama.LISA, verde, null);
        List<Prenda> superioreseses = new ArrayList<>();
        superioreseses.add(remeraNegra);
        superioreseses.add(buzoBlanco);      
        testing = new Atuendo(superioreseses,zapatosAzul,pantalonRojo,anilloLinternaVerde);
    }

    @Test
    public void NoHayCalzadosEntoncesSugerenciasDeAtuendosEsVacio() {
        assertTrue(guardarropa.generarAtuendos().isEmpty());
    }
    @Test
    public void MeCalculaBienElAbrigo() {
        System.out.println(testing.abrigoTotal());
    }
    @Test
    public void cantidadDePrendas() {
        System.out.println(guardarropa.cantidadPrendas());
    }

    @Test
    public void HaySugerenciasDeAtuendosSinAccesorios() {

        agregoUnCalzado(negro);

        assertEquals(6, guardarropa.prendasSegunCategoria(Categoria.PARTE_SUPERIOR).size());
        assertEquals(6, guardarropa.prendasSegunCategoria(Categoria.PARTE_INFERIOR).size());
        assertEquals(1, guardarropa.prendasSegunCategoria(Categoria.CALZADO).size());

        Set<Atuendo> atuendos = guardarropa.generarAtuendos();

        assertFalse(atuendos.isEmpty());
        assertEquals(36, atuendos.size());
    }

    @Test
    public void haySugerenciasDeAtuendosConAccesorios() {

        agregoUnCalzado(negro);
        agregoUnAccesorio(verde);

        assertEquals(6, guardarropa.prendasSegunCategoria(Categoria.PARTE_SUPERIOR).size());
        assertEquals(6, guardarropa.prendasSegunCategoria(Categoria.PARTE_INFERIOR).size());
        assertEquals(1, guardarropa.prendasSegunCategoria(Categoria.CALZADO).size());
        assertEquals(1, guardarropa.prendasSegunCategoria(Categoria.ACCESORIOS).size());

        Set<Atuendo> atuendos = guardarropa.generarAtuendos();

        assertFalse(atuendos.isEmpty());
        assertEquals(72, atuendos.size());

    }

    public List<Color> colores() {
        negro = new Color(0, 0, 0);
        rojo = new Color(255, 0, 0);
        blanco = new Color(255, 255, 255);
        azul = new Color(0, 0, 255);
        verde = new Color(0, 100, 0);
        amarillo = new Color(255, 233, 0);

        return asList(negro, rojo, blanco, azul, verde ,amarillo);
    }

    public void agregoUnCalzado(Color color) {
        borrador.definirTipo(Tipo.ZAPATO);
        borrador.definirMaterial(Material.CUERO);
        borrador.definirColorPrimario(color);
        guardarropa.agregarPrendas(borrador.crearPrenda());
    }

    public void agregoUnAccesorio(Color color) {
        borrador.definirTipo(Tipo.ANILLO);
        borrador.definirMaterial(Material.ORO);
        borrador.definirColorPrimario(color);
        guardarropa.agregarPrendas(borrador.crearPrenda());
    }
}