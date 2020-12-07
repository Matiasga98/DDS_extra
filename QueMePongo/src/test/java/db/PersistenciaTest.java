package db;

import dominio.*;
import dominio.clima.AccuweatherData.AccuWeather;
import dominio.clima.ProveedorClima;
import dominio.clima.ProveedorMock;
import dominio.enumerados.Material;
import dominio.enumerados.ModoDeRepeticion;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertTrue;


public class PersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

    Color negro;
    Color rojo;
    Color blanco;
    Color azul;
    Color verde;
    Color amarillo;

    private Prenda remerita, pantaloncito, inviernito, guantitos, zapatito, buzito, camisita, anillito, bufandita, camperita,
            lentitos, gorrito;
    private Usuario elio;
    private Evento presentarEsteTP;
    private Guardarropa testito;

    @Before
    public void setUp() {

        negro = new Color(0, 0, 0);
        rojo = new Color(255, 0, 0);
        blanco = new Color(255, 255, 255);
        azul = new Color(0, 0, 255);
        verde = new Color(0, 100, 0);
        amarillo = new Color(255, 233, 0);

        remerita = new Prenda("remerita", Tipo.REMERA, Material.ALGODON, Trama.LISA, negro, null);
        pantaloncito =new Prenda("pantaloncito", Tipo.PANTALON, Material.JEAN, Trama.LISA, rojo, null);
        inviernito =new Prenda("inviernito", Tipo.PANTALONINVIERNO, Material.JEAN, Trama.LISA, rojo, null);
        zapatito =new Prenda("zapatito", Tipo.ZAPATO, Material.CUERO, Trama.LISA, azul, null);
        buzito =new Prenda("buzito", Tipo.BUZO, Material.ALGODON, Trama.LISA, blanco, null);
        anillito =new Prenda("anillito", Tipo.ANILLO, Material.ORO, Trama.LISA, verde, null);
        guantitos =new Prenda("guantitos", Tipo.GUANTES, Material.LANA, Trama.LISA, verde, null);
        bufandita =new Prenda("bufandita", Tipo.BUFANDA, Material.LANA, Trama.LISA, verde, null);
        camperita = new Prenda("camperita", Tipo.CAMPERA,Material.ALGODON,Trama.LISA,rojo,null);
        camisita = new Prenda ("camisita", Tipo.CAMISA,Material.ALGODON,Trama.LISA,azul,null);
        lentitos = new Prenda ("lentitos", Tipo.LENTES,Material.VIDRIO,Trama.LISA,negro,null);
        gorrito = new Prenda ( "gorrito", Tipo.GORRO,Material.LANA,Trama.LISA,blanco,azul);

        testito = new Guardarropa("holis");

        elio = new Usuario(true);
        elio.agregarGuardarropa(testito);
        testito.agregarPrendas(guantitos);
        testito.agregarPrendas(bufandita);
        testito.agregarPrendas(remerita);
        testito.agregarPrendas(zapatito);
        testito.agregarPrendas(lentitos);
        testito.agregarPrendas(buzito);
        testito.agregarPrendas(pantaloncito);
        testito.agregarPrendas(anillito);
        testito.agregarPrendas(camperita);
        testito.agregarPrendas(camisita);
        testito.agregarPrendas(inviernito);
        testito.agregarPrendas(gorrito);
        Atuendo atuendo = new Atuendo(Arrays.asList(remerita,pantaloncito,zapatito,lentitos));
        //testito.agregarAAceptados(atuendo);
        //testito.agregarARechazados(atuendo);
    }

    @Test
    public void persistirPrenda() throws Exception {
    	Prenda remerita = new Prenda("remerita", Tipo.REMERA, Material.ALGODON, Trama.LISA, negro, null);

        //entityManager().getTransaction().begin();
        entityManager().persist(remerita);
        entityManager().getTransaction().commit();

    }

    @Test
    public void persistirGuardarropa() throws Exception {
        Guardarropa testito = new Guardarropa("testito");

        testito.agregarPrendas(remerita);
        testito.agregarPrendas(pantaloncito);

        //entityManager().getTransaction().begin();

        
        entityManager().persist(remerita);
        entityManager().persist(pantaloncito);

        testito.getPrendas().forEach(prendas -> entityManager().persist(prendas));
        
        entityManager().persist(testito);

        entityManager().getTransaction().commit();
    }

    @Test
    public void cargarEvento() throws Exception{
        presentarEsteTP = new Evento("Prensentar TP",new AccuWeather(), LocalDateTime.parse("2019-10-06T18:00:00"),false, ModoDeRepeticion.MENSUAL,elio,false);
        //entityManager().getTransaction().begin();
        entityManager().persist(presentarEsteTP);
        entityManager().getTransaction().commit();
    }


    @Test
    public void crearUsuarios() throws Exception{

        Usuario cris = new Usuario("Cris","123456");

        Guardarropa guardarropa = new Guardarropa("testito");
        guardarropa.agregarPrendas(remerita);
        guardarropa.agregarPrendas(pantaloncito);
        guardarropa.agregarPrendas(buzito);
        guardarropa.agregarPrendas(zapatito);
        guardarropa.agregarPrendas(guantitos);
        guardarropa.agregarPrendas(camisita);
        guardarropa.agregarPrendas(anillito);
        guardarropa.agregarPrendas(bufandita);

        guardarropa.agregarPrendas(lentitos);
        guardarropa.agregarPrendas(camperita);
        guardarropa.agregarPrendas(inviernito);
        guardarropa.agregarPrendas(gorrito);


        entityManager().persist(remerita);
        entityManager().persist(pantaloncito);
        entityManager().persist(buzito);
        entityManager().persist(zapatito);
        entityManager().persist(guantitos);
        entityManager().persist(camisita);
        entityManager().persist(anillito);
        entityManager().persist(lentitos);
        entityManager().persist(camperita);
        entityManager().persist(inviernito);
        entityManager().persist(gorrito);
        entityManager().persist(bufandita);

        guardarropa.getPrendas().forEach(prendas -> entityManager().persist(prendas));

        entityManager().persist(guardarropa);
        cris.agragarGuardarropa(guardarropa);
        entityManager().persist(cris);

        entityManager().getTransaction().commit();

    }

}
