package db;

import dominio.Color;
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

    @Before
    public void setUp() {
        negro = new Color(0,0,0);
    }

    @Test
    public void persistirPrenda() throws Exception{

        //EntityManager em = entityManager();
        Prenda remerita = new Prenda("remerita", Tipo.REMERA, Material.ALGODON, Trama.LISA, negro, null);

        entityManager().persist(negro);
        entityManager().getTransaction().commit();
       /* entityManager().close();*/


        //entityManager().persist(remerita);

    }
}
