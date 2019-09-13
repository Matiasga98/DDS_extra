package db;

import dominio.*;
import dominio.clima.AccuweatherData.AccuWeather;
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


public class PersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

    private Color negro;
    private Prenda remerita, pantaloncito;
    private Usuario elio;
    private Evento presentarEsteTP;

    @Before
    public void setUp() {

        negro = new Color(0,0,0);

        remerita = new Prenda("remerita", Tipo.REMERA, Material.ALGODON, Trama.LISA, negro, null);
        pantaloncito = new Prenda("pantaloncito", Tipo.PANTALON, Material.JEAN, Trama.LISA, negro, null);

        elio = new Usuario(true);

    }

    @Test
    public void persistirPrenda() throws Exception {
    	Prenda remerita = new Prenda("remerita", Tipo.REMERA, Material.ALGODON, Trama.LISA, negro, null);

        entityManager().getTransaction().begin();

        entityManager().persist(negro);
        entityManager().persist(remerita);

        entityManager().getTransaction().commit();

    }

    @Test
    public void persistirGuardarropa() throws Exception {
        Guardarropa testito = new Guardarropa();

        testito.agregarPrendas(remerita);
        testito.agregarPrendas(pantaloncito);

        entityManager().getTransaction().begin();
        entityManager().persist(remerita.colorPrimario());
        entityManager().persist(pantaloncito.colorPrimario());
        
        entityManager().persist(remerita);
        entityManager().persist(pantaloncito);

        testito.getPrendas().forEach(prendas -> entityManager().persist(prendas));
        
        entityManager().persist(testito);

        entityManager().getTransaction().commit();
    }

    @Test
    public void cargarEvento() throws Exception{
        presentarEsteTP = new Evento("Prensentar TP",new AccuWeather(), LocalDateTime.parse("2019-05-31T13:00:00"),false, ModoDeRepeticion.MENSUAL,elio,false);
        entityManager().getTransaction().begin();
        entityManager().persist(presentarEsteTP);
        entityManager().getTransaction().commit();
    }

    @Test
    public void borrarEvento() throws Exception{

        Evento presentarEsteTP2 = entityManager().createQuery("from Evento where id_evento = 1", Evento.class).getResultList().get(0);
        presentarEsteTP2.destruirEvento();

        entityManager().remove(presentarEsteTP2);
        entityManager().getTransaction().commit();

    }

}
