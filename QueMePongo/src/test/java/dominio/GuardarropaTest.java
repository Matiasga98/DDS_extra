package dominio;

import dominio.enumerados.Categoria;
import dominio.enumerados.Material;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

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

    @Before       //FIXME
    public void setUp() {
        borrador = new Borrador();
        guardarropa = new Guardarropa();
        colores = colores();


        //REMERAS LISAS DE DISTINTOS COLORES
        borrador.definirTipo(Tipo.REMERA);
        borrador.definirMaterial(Material.ALGODON);
        borrador.definirNombre(("reme"));
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
    }

    @Test
    public void NoHayCalzadosEntoncesSugerenciasDeAtuendosEsVacio() {
        assertTrue(guardarropa.generarAtuendos().isEmpty());
    }
    @Test
    public void cantidadDePrendas() {
        assertEquals(12,guardarropa.cantidadPrendas());
    }



    @Test
    public void GenerarAtuendosNuevo() {
        Prenda remerita = new Prenda("remerita", Tipo.REMERA, Material.ALGODON, Trama.LISA, negro, null);
        Prenda pantaloncito =new Prenda("pantaloncito", Tipo.PANTALON, Material.JEAN, Trama.LISA, rojo, null);
        Prenda inviernito =new Prenda("inviernito", Tipo.PANTALONINVIERNO, Material.JEAN, Trama.LISA, rojo, null);
        Prenda zapatito =new Prenda("zapatito", Tipo.ZAPATO, Material.CUERO, Trama.LISA, azul, null);
        Prenda buzito =new Prenda("buzito", Tipo.BUZO, Material.ALGODON, Trama.LISA, blanco, null);
        Prenda anillito =new Prenda("anillito", Tipo.ANILLO, Material.ORO, Trama.LISA, verde, null);
        Prenda guantitos =new Prenda("guantitos", Tipo.GUANTES, Material.LANA, Trama.LISA, verde, null);
        Prenda bufandita =new Prenda("bufandita", Tipo.BUFANDA, Material.LANA, Trama.LISA, verde, null);
        Prenda camperita = new Prenda("camperita", Tipo.CAMPERA,Material.ALGODON,Trama.LISA,rojo,null);
        Prenda camisita = new Prenda ("camisita", Tipo.CAMISA,Material.ALGODON,Trama.LISA,azul,null);
        Prenda lentitos = new Prenda ("lentitos", Tipo.LENTES,Material.VIDRIO,Trama.LISA,negro,null);
        Guardarropa testito = new Guardarropa();
        //testito.agregarPrendas(guantitos);
        testito.agregarPrendas(bufandita);
        testito.agregarPrendas(remerita);
        testito.agregarPrendas(zapatito);
        testito.agregarPrendas(lentitos);
        testito.agregarPrendas(buzito);
        testito.agregarPrendas(pantaloncito);
        //testito.agregarPrendas(anillito);
        //testito.agregarPrendas(camperita);
        //testito.agregarPrendas(camisita);
        //testito.agregarPrendas(inviernito);
        Set<Atuendo>  atuendos = new HashSet<>();
        /*
        System.out.println(testito.prendasSegunCategoria(Categoria.ACCESORIOS));
        System.out.println(testito.prendasSegunCategoria(Categoria.PARTE_INFERIOR));
        System.out.println(testito.prendasSegunCategoria(Categoria.CALZADO));
        System.out.println(testito.prendasSegunCategoria(Categoria.PARTE_SUPERIOR));
        System.out.println(testito.prendasSegunCategoria(Categoria.PARTE_SUPERIOR).stream().filter(prenda -> prenda.tipo.Capa() <=2).collect(Collectors.toSet()));
        System.out.println(remerita);*/
        atuendos = testito.generarAtuendos();
        //System.out.println(remerita.tipo);
        //testito.generarAtuendos().stream().forEach(atuendo->System.out.println(atuendo.prendas()));
        //testito.generarAtuendos().stream().forEach(atuendo->this.mostrarAtuendos(atuendo.prendas()));
        //System.out.println(testito.generarAtuendos());
        atuendos.stream().forEach(atuendo -> atuendo.mostrarPrendas());

    }
    public void mostrarAtuendos (List<Prenda> atuendo){
        System.out.println("----------");
        atuendo.stream().forEach(prenda->System.out.println(prenda.tipo));
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