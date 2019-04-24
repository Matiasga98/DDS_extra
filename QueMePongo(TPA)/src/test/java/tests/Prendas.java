package tests;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import excepciones.MaterialInconsistente;
import excepciones.NoHayPrendas;
import excepciones.RequiereColorDistinto;
import dominio.*;

public class Prendas {
	Borrador borrador;

	@Rule
	public ExpectedException excepcion = ExpectedException.none();

	private Guardarropa miGuardarropas;

	private Usuario usuarioElio;

	@Before
	public void init() throws NoHayPrendas {
		borrador = new Borrador();
		
		Color negro = new Color(0, 0, 0);
		Color rojo = new Color(255, 0, 0);
		Color blanco = new Color(255, 255, 255);
		Color azul = new Color(0, 0, 255);
		Color verde = new Color(0, 100, 0);
		Color amarillo = new Color(255, 233, 0);
		
		Set<Prenda> superiores = new HashSet<Prenda>();
		Set<Prenda> inferiores = new HashSet<Prenda>();
		Set<Prenda> calzados = new HashSet<Prenda>();
		Set<Prenda> accesorios = new HashSet<Prenda>();
		
		Set<Prenda> superiores2 = new HashSet<Prenda>();
		Set<Prenda> inferiores2 = new HashSet<Prenda>();
		Set<Prenda> calzados2 = new HashSet<Prenda>();
		Set<Prenda> accesorios2 = new HashSet<Prenda>();
		
		miGuardarropas = new Guardarropa(superiores, inferiores, calzados, accesorios);
		Guardarropa miGuardarropas2 = new Guardarropa(superiores2, inferiores2, calzados2, accesorios2);

		//Guardarropa 1
		armarmeUnaPrenda(Tipo.REMERA, verde, negro, Trama.ESTAMPADO, Material.ALGODON,
				"unaRemeraDeGreenPeace", miGuardarropas);
		armarmeUnaPrenda(Tipo.POLLERA, amarillo, null, Trama.LISA, Material.ALGODON,
				"miPolleraAmarilla", miGuardarropas);
		armarmeUnaPrenda(Tipo.ZAPATO, negro, null, Trama.LISA, Material.GOMA, "zapatosDeGoma", miGuardarropas);
		armarmeUnaPrenda(Tipo.PANTALON, azul, null, Trama.CUADROS, Material.JEAN, "blueJeans", miGuardarropas);
		armarmeUnaPrenda(Tipo.CAMISA, negro, null, Trama.RAYADA, Material.POLIESTER, "camisaNegra", miGuardarropas);
		armarmeUnaPrenda(Tipo.ZAPATO, azul, blanco, Trama.LUNARES, Material.CUERO, "zapatosRotos", miGuardarropas);
		armarmeUnaPrenda(Tipo.ANILLO, rojo, blanco, Trama.ESTAMPADO, Material.ORO,
				"anilloDelCapitanBeto", miGuardarropas);
				
		//Guardarropa 2
		armarmeUnaPrenda(Tipo.REMERA, verde, negro, Trama.ESTAMPADO, Material.ALGODON,
				"laArquitecta", miGuardarropas2);
		armarmeUnaPrenda(Tipo.POLLERA, amarillo, null, Trama.LISA, Material.ALGODON,
				"elConstructor", miGuardarropas2);
		armarmeUnaPrenda(Tipo.ZAPATO, negro, null, Trama.LISA, Material.GOMA, "elIngeniero", miGuardarropas2);
		armarmeUnaPrenda(Tipo.PANTALON, azul, null, Trama.CUADROS, Material.JEAN, "elDiseñador", miGuardarropas2);
		armarmeUnaPrenda(Tipo.CAMISA, negro, null, Trama.RAYADA, Material.POLIESTER, "enLaCompuEstanJugando", miGuardarropas2);
		armarmeUnaPrenda(Tipo.ZAPATO, azul, blanco, Trama.LUNARES, Material.CUERO, "milProyectosVanCreando", miGuardarropas2);
		armarmeUnaPrenda(Tipo.ANILLO, rojo, blanco, Trama.ESTAMPADO, Material.ORO,
				"puente", miGuardarropas2);
		
		Set<Guardarropa> conjuntoDeGuardarropa = new HashSet<Guardarropa>();
		conjuntoDeGuardarropa.add(miGuardarropas);
		conjuntoDeGuardarropa.add(miGuardarropas2);
		usuarioElio = new Usuario(conjuntoDeGuardarropa);
	}
	
	public Prenda armarmeUnaPrenda(Tipo tipo, Color colorPrimario, Color colorSecundario, Trama trama, Material material,
			String nombre, Guardarropa guardarropa) {
		Borrador borrador = new Borrador();
		borrador.definirTipo(tipo);
		borrador.definirColorPrimario(colorPrimario);
		borrador.definirColorSecundario(colorSecundario);
		borrador.definirTrama(trama);
		borrador.definirMaterial(material);
		borrador.definirNombre(nombre);
		borrador.definirGuardarropa(guardarropa);
		return borrador.crearPrenda();
	}

	@Test
	public void sugerenciaDeAtuendos() {
		System.out.println("Test de sugerir atuendos de un guardarropas:");
		miGuardarropas.sugerir();
		System.out.println();
	}
	
	@Test
	public void sugerenciaDeAtuendosDeUsuario() {
		System.out.println("Test de sugerir atuendos de un usuario:");
		usuarioElio.sugerirDeTodosLosGuardarropas();
		System.out.println();
	}

	@Test
	public void mostrarTodasLasPrendasDeUnUsuario() {
		System.out.println("Test de mostrar todas las prendas de un usuario:");
		usuarioElio.pedirTodasLasPrendas();
		System.out.println();
	}

	@Test
	public void fallaAlNoDeclararColorPrimario() throws MaterialInconsistente {

		excepcion.expect(NullPointerException.class);
		excepcion.expectMessage("Se requiere un color primario");

		borrador.definirTipo(Tipo.ZAPATO);
		borrador.definirMaterial(Material.CUERO);
		borrador.definirTrama(Trama.CUADROS);
		// borrador.definirColorPrimario(new Color(122,22,22));
		borrador.crearPrenda();

	}
	
	@Test
	public void fallaAlDeclararColorPrimarioIgualAlSecundario() throws MaterialInconsistente {

		excepcion.expect(RequiereColorDistinto.class);
		excepcion.expectMessage("El color secundario debe ser distinto del primario");

		borrador.definirTipo(Tipo.ZAPATO);
		borrador.definirMaterial(Material.CUERO);
		borrador.definirTrama(Trama.CUADROS);
		borrador.definirColorPrimario(new Color(122,22,22));
		borrador.definirColorSecundario(new Color(122,22,22));
		borrador.definirGuardarropa(miGuardarropas);
		borrador.crearPrenda();

	}
	
	@Test
	public void fallaAlDefinirTelaInconsistente() throws MaterialInconsistente {

		excepcion.expect(MaterialInconsistente.class);
		excepcion.expectMessage("El material elegido no es compatible con el tipo de prenda");

		borrador.definirTipo(Tipo.ZAPATO);
		borrador.definirMaterial(Material.DIAMANTE);
		borrador.definirTrama(Trama.CUADROS);
		borrador.definirColorPrimario(new Color(122,22,22));
		borrador.definirColorSecundario(new Color(225,33,145));
		borrador.definirGuardarropa(miGuardarropas);
		borrador.crearPrenda();

	}


	// @Test(expected = NullPointerException.class);

}
