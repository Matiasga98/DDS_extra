package dominio;

import dominio.clima.AccuweatherData.AccuWeather;
import dominio.clima.ProveedorClima;
import dominio.clima.ProveedorMock;
import dominio.enumerados.Categoria;
import dominio.enumerados.Material;
import dominio.enumerados.ModoDeRepeticion;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
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
    private Evento cumpleDeHernan;
    private Guardarropa guardarropa;

    @Before
    public void setUp() throws IOException {
        borrador = new Borrador();
        usuario = new Usuario(true);
        guardarropa = new Guardarropa("holis");
        usuario.agregarGuardarropa(guardarropa);
        colores = colores();
    }



    @Test
    public void usuarioSinGuardarropaPidenSugerencias() {
        //assertTrue(usuario.guardarropas().isEmpty());
        assertTrue(usuario.sugerenciasDeAtuendosDeTodosLosGuardarropas().isEmpty());
    }

    @Test
    public void usuarioConUnGuardarropa() {

        //assertTrue(usuario.guardarropas().isEmpty());






        Guardarropa testito = new Guardarropa("testito");

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
        Prenda gorrito = new Prenda ( "gorrito", Tipo.GORRO,Material.LANA,Trama.LISA,blanco,azul);

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

        assertEquals(4, testito.prendasSegunCategoria(Categoria.PARTE_SUPERIOR).size());
        assertEquals(2, testito.prendasSegunCategoria(Categoria.PARTE_INFERIOR).size());
        assertEquals(1, testito.prendasSegunCategoria(Categoria.CALZADO).size());
        assertEquals(1, testito.prendasSegunCategoria(Categoria.CABEZA).size());
        assertEquals(1, testito.prendasSegunCategoria(Categoria.CARA).size());
        assertEquals(2, testito.prendasSegunCategoria(Categoria.MANOS).size());
        assertEquals(1, testito.prendasSegunCategoria(Categoria.CUELLO).size());
        

    }



   /* @Test
    public void superTest() {
        usuario = new Usuario(true);
        Guardarropa guardarropa = new Guardarropa();

        Prenda remera = new Prenda(Tipo.REMERA,Material.ALGODON, Trama.LISA,rojo,null);
        Prenda buzo = new Prenda(Tipo.BUZO,Material.ALGODON, Trama.LISA,rojo,null);
        Prenda pantalon = new Prenda(Tipo.PANTALON,Material.JEAN, Trama.LISA,rojo,null);
        Prenda zapatos = new Prenda(Tipo.ZAPATO,Material.CUERO, Trama.LISA,rojo,null);
        Prenda bufanda = new Prenda(Tipo.BUFANDA,Material.LANA, Trama.LISA,rojo,null);

        usuario.agregarGuardarropa(guardarropa);
        usuario.agregarPrenda(remera,guardarropa);
        usuario.agregarPrenda(buzo,guardarropa);
        usuario.agregarPrenda(pantalon,guardarropa);
        usuario.agregarPrenda(zapatos,guardarropa);
        usuario.agregarPrenda(bufanda,guardarropa);
        List<Prenda> prendasTest = new ArrayList<Prenda>();;
        prendasTest.add(remera);
        prendasTest.add(buzo);
        prendasTest.add(pantalon);
        prendasTest.add(zapatos);
        Atuendo atuendoTest1 = new Atuendo(prendasTest);
        prendasTest.remove(buzo);
        Atuendo atuendoTest2 = new Atuendo (prendasTest);
        prendasTest.add(bufanda);
        Atuendo atuendoTest3 = new Atuendo (prendasTest);
        Set<Atuendo> atuendosTest = new HashSet<>();
        atuendosTest.add(atuendoTest1);
        atuendosTest.add(atuendoTest2);
        atuendosTest.add(atuendoTest3);



        Evento cumpleDeHernan = new Evento("cumple Hernan AAAAA", LocalDateTime.parse("2019-05-31T13:00:00"),false);


        ProveedorClima mock = new AccuWeather();
        cumpleDeHernan = new Evento("cumple Hernan AAAAA", mock, LocalDateTime.parse("2019-05-31T13:00:00"), ModoDeRepeticion.NUNCA, usuario, false);
        // System.out.println(guardarropa.cantidadPrendas());
        Set<Atuendo> atuendosSugeridos = usuario.pedirSugerenciaParaEvento(cumpleDeHernan,guardarropa, mock, false);
        /*atuendosSugeridos.stream().forEach(atuendo -> atuendo.prendas().stream().forEach(prenda->System.out.println(prenda)));
        System.out.println("--");
        atuendosTest.stream().forEach(atuendo -> atuendo.prendas().stream().forEach(prenda->System.out.println(prenda)));
        System.out.println(atuendosSugeridos.containsAll(atuendosTest))
        assertTrue(this.compararSets(atuendosSugeridos,atuendosTest));
    }*/


   @Test
   public void testSugerencia(){
       Prenda remerita = new Prenda("remerita", Tipo.REMERA, Material.ALGODON, Trama.LISA, negro, null);
       Prenda pantaloncito =new Prenda("pantaloncito", Tipo.PANTALON, Material.JEAN, Trama.LISA, rojo, null);
       Prenda inviernito =new Prenda("inviernito", Tipo.PANTALONINVIERNO, Material.JEAN, Trama.LISA, rojo, null);
       Prenda zapatito =new Prenda("zapatito", Tipo.ZAPATO, Material.CUERO, Trama.LISA, azul, null);
       Prenda superZapatito =new Prenda("zapatito2", Tipo.ZAPATOGRUESO, Material.CUERO, Trama.LISA, azul, null);
       Prenda buzito =new Prenda("buzito", Tipo.BUZO, Material.ALGODON, Trama.LISA, blanco, null);
       Prenda anillito =new Prenda("anillito", Tipo.ANILLO, Material.ORO, Trama.LISA, verde, null);
       Prenda guantitos =new Prenda("guantitos", Tipo.GUANTES, Material.LANA, Trama.LISA, verde, null);
       Prenda bufandita =new Prenda("bufandita", Tipo.BUFANDA, Material.LANA, Trama.LISA, verde, null);
       Prenda camperita = new Prenda("camperita", Tipo.CAMPERA,Material.ALGODON,Trama.LISA,rojo,null);
       Prenda camisita = new Prenda ("camisita", Tipo.CAMISA,Material.ALGODON,Trama.LISA,azul,null);
       Prenda lentitos = new Prenda ("lentitos", Tipo.LENTES,Material.VIDRIO,Trama.LISA,negro,null);
       Prenda mascarita = new Prenda ("mascarita", Tipo.BALACLAVA,Material.LANA,Trama.LISA,negro,null);


       Guardarropa testito = new Guardarropa("testito");
       testito.agregarPrendas(guantitos);
      // testito.agregarPrendas(bufandita);
       testito.agregarPrendas(remerita);
       testito.agregarPrendas(zapatito);
       //testito.agregarPrendas(lentitos);
       testito.agregarPrendas(buzito);
       testito.agregarPrendas(pantaloncito);
       testito.agregarPrendas(anillito);
       //testito.agregarPrendas(camperita);
       testito.agregarPrendas(camisita);
       //testito.agregarPrendas(inviernito);
       //testito.agregarPrendas(mascarita);
       //testito.agregarPrendas(superZapatito);
       Set<Atuendo>  sugerencias = new HashSet<>();
       Usuario elio = new Usuario(true);
       elio.agregarGuardarropa(testito);


       ProveedorClima mock = new ProveedorMock();
       cumpleDeHernan = new Evento("cumple Hernan AAAAA", mock, LocalDateTime.parse("2019-11-29T10:00:00"),false, ModoDeRepeticion.ANUAL, elio, false);
        elio.agregarEvento(cumpleDeHernan);
       sugerencias = elio.pedirSugerenciaParaEventoDeTodosLosGuadaropas(cumpleDeHernan,mock,false);
       sugerencias.stream().forEach(atuendo -> atuendo.mostrarPrendas());
       //System.out.println(mock.temperatura(LocalDateTime.parse("2019-09-13T13:00:00")));
       assertEquals(sugerencias.size(),3);

   }

    @Test
    public void testSetEquals(){
        Prenda remera = new Prenda("remera",Tipo.REMERA,Material.ALGODON, Trama.LISA,rojo,null);
        List<Prenda> prendaTest = new ArrayList<>();
        prendaTest.add(remera);
        Atuendo atuendo1 = new Atuendo(prendaTest);

        Atuendo atuendo2 = new Atuendo(prendaTest);

        System.out.println(this.compararAtuendos(atuendo1,atuendo2));

        Set<Atuendo> atuendos1 = new HashSet<>();
        Set<Atuendo> atuendos2 = new HashSet<>();
        atuendos1.add(atuendo1);
        atuendos2.add(atuendo2);

        System.out.println(atuendos1.stream().allMatch(atuendo -> estaAtuendoEnSet(atuendo,atuendos2)));

        /* String elements[] = { "B", "A", "C", "D", "E" };
         Set set = new HashSet(Arrays.asList(elements));
         elements = new String[] { "A", "B", "C", "D","E" };
         Set set2 = new HashSet(Arrays.asList(elements));
         System.out.println(set.equals(set2));*/
    }
    
    @Test
    public void testPlanificador() {
    	Alertador.planificame_porfi();
    	assertTrue(Alertador.planificador.isStarted());
    }
    
    public boolean estaAtuendoEnSet (Atuendo atuendo, Set<Atuendo> atuendos){
       return atuendos.stream().anyMatch(atuendoSet -> compararAtuendos(atuendoSet,atuendo));

    }
    public boolean compararSets (Set<Atuendo> atuendos1, Set<Atuendo> atuendos2){
        return atuendos1.stream().allMatch(atuendo -> estaAtuendoEnSet(atuendo,atuendos2));
    }

    public void agregoUnGuardarropa() {
        //REMERAS LISAS DE DISTINTOS COLORES
        Guardarropa guardarropa = new Guardarropa("testito");

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
    public boolean compararAtuendos(Atuendo atuendo1, Atuendo atuendo2){
        return atuendo1.prendas().containsAll(atuendo2.prendas());
    }

}