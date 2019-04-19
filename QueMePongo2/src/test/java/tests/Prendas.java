package tests;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import excepciones.MaterialInconsistente;
import excepciones.NoHayPrendas;
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

	/*@Test
	public void sugerenciaDeAtuendos() {
		miGuardarropas.sugerir();
	}*/
	
	/*@Test
	public void sugerenciaDeAtuendosDeUsuario() {
		usuarioElio.sugerirDeTodosLosGuardarropas();
	}*/

	@Test
	public void mostrarTodasLasPrendasDeUnUsuario() {
		usuarioElio.pedirTodasLasPrendas();
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

	// @Test(expected = NullPointerException.class);

}
