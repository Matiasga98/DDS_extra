package db;

import dominio.Color;
import dominio.Guardarropa;
import dominio.Prenda;
import dominio.enumerados.Material;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

    private Color negro;
    private Prenda remerita, pantaloncito;

    @Before
    public void setUp() {

        negro = new Color(0,0,0);

        remerita = new Prenda("remerita", Tipo.REMERA, Material.ALGODON, Trama.LISA, negro, null);
        pantaloncito = new Prenda("pantaloncito", Tipo.PANTALON, Material.JEAN, Trama.LISA, negro, null);

    }

    @Test
    public void persistirPrenda() throws Exception{

        EntityManager em = entityManager();
        Prenda remerita = new Prenda("remerita", Tipo.REMERA, Material.ALGODON, Trama.LISA, negro, null);

        entityManager().persist(negro);
        entityManager().persist(remerita);

        entityManager().getTransaction().commit();

    }

    @Test
    public void persistirGuardarropa() throws Exception
    {
        /*
        EntityManager em = entityManager();

        Guardarropa testito = new Guardarropa();
        testito.agregarPrendas(remerita);
        testito.agregarPrendas(pantaloncito);

        entityManager().persist(remerita);
        entityManager().persist(pantaloncito);

        entityManager().persist(testito);

        entityManager().getTransaction().commit();*/
    }

}
