package dominio;

import java.util.*;
import java.util.stream.Collectors;
import com.google.common.collect.Sets;
import dominio.clima.ProveedorClima;
import dominio.enumerados.Categoria;
import org.checkerframework.checker.units.qual.C;


public class Guardarropa {

	private Map<Categoria, Set<Prenda>> prendas;
	private Set<Atuendo> atuendosAceptados;
	private Set<Atuendo> atuendosRechazados;
	private Set<List<Prenda>> combinacionesSuperioresValidas = new HashSet<List<Prenda>>();
	private Set<List<Prenda>> combinacionesAccesoriosValidas = new HashSet<List<Prenda>>();
	private Set<Set<Prenda>> combinacionesAccesoriosValidasConRepeticion = new HashSet<Set<Prenda>>();

	public Guardarropa() {
		prendas = new HashMap<>();
		atuendosAceptados = new HashSet<>();
		atuendosRechazados = new HashSet<>();
		prendas.put(Categoria.PARTE_SUPERIOR, new HashSet<>());
		prendas.put(Categoria.PARTE_INFERIOR, new HashSet<>());
		prendas.put(Categoria.CALZADO, new HashSet<>());
		prendas.put(Categoria.ACCESORIOS, new HashSet<>());
		prendas.put(Categoria.CABEZA, new HashSet<>());
		prendas.put(Categoria.CARA, new HashSet<>());
		prendas.put(Categoria.CUELLO, new HashSet<>());
		prendas.put(Categoria.MANOS, new HashSet<>());
	}

	public boolean incluye(Prenda prenda){
		return prendas.get(prenda.categoria()).contains(prenda);
	}

	public void agregarPrendas(Prenda prenda) {
		prendas.get(prenda.categoria()).add(prenda);
	}

	public int cantidadPrendas() {
		return prendas.values().stream().mapToInt(categoria -> categoria.size()).sum();
	}

	public Set<Prenda> prendasSegunCategoria(Categoria categoria) {
		return prendas.get(categoria);
	}


	public void mostrarAtuendosList (List<Prenda> atuendo){
		System.out.println("----------");
		atuendo.stream().forEach(prenda->System.out.println(prenda.tipo));
	}
	public void mostrarAtuendosSet (Set<Prenda> atuendo){
		System.out.println("----------");
		atuendo.stream().forEach(prenda->System.out.println(prenda.tipo));
	}

	//Cambiar este metodo. Al parecer hay un smellazo de code del tipo SO BIG METOD.
	public Set<Atuendo> generarAtuendos() {
		Set<Atuendo> atuendos = new HashSet<>();
		Set<Prenda> superiores = prendasSegunCategoria(Categoria.PARTE_SUPERIOR);
		Set<Prenda> inferiores = prendasSegunCategoria(Categoria.PARTE_INFERIOR);
		Set<Prenda> calzados = prendasSegunCategoria(Categoria.CALZADO);
		Set<Prenda> accesorios = conseguirAccesorios();

		//Method Mati

		List<Prenda> superioresDeCapaBaja = superiores.stream().filter(prenda -> prenda.tipo.Capa() <=2).collect(Collectors.toList());
		superioresDeCapaBaja.stream().forEach(prenda -> this.arnarCondicionInicialSuperiores(prenda));
		accesorios.stream().forEach(unAccesorio->armarCondicionInicialAccesorios(unAccesorio));

		Set<List<Prenda>> atuendosSinSuperior = Sets.cartesianProduct(
				inferiores,
				calzados
		);

		combinacionesAccesoriosValidasConRepeticion.forEach(lista -> combinacionesAccesoriosValidas.add(new ArrayList<>(lista)));

		Set<List<List<Prenda>>> atuendosObtenidos = Sets.cartesianProduct(
				combinacionesSuperioresValidas,
				atuendosSinSuperior,
				combinacionesAccesoriosValidas

		);

		Set<List<Prenda>> AtuendosFinales = new HashSet<>();
		atuendosObtenidos.stream().forEach(pipi->AtuendosFinales.add(this.aplanarLista(pipi)));
		AtuendosFinales.forEach(lista -> atuendos.add(new Atuendo(lista)));

		return atuendos;

	}

	public void armarCondicionInicialAccesorios (Prenda prenda){
		Set<Prenda> conjuntoAccesorios = new HashSet<>();
		conjuntoAccesorios.add(prenda);
		combinacionesAccesoriosValidasConRepeticion.add(conjuntoAccesorios);
		Set<Prenda> conjunto = new HashSet<>(conjuntoAccesorios);
		this.armarAccesorios(conjunto);
	}

	public void armarAccesorios(Set<Prenda> combinacion){
		List<Prenda> accesoriosACombinar = arnarAccesoriosACombinar(combinacion);
		accesoriosACombinar.stream().forEach(unAccesorio->completarAccesorios(combinacion,unAccesorio));
	}

	public void completarAccesorios(Set<Prenda> combinacion, Prenda accesorio){
		if (!combinacion.contains(accesorio)) {
			Set<Prenda> conjuntoNuevo = new HashSet<>();
			conjuntoNuevo.addAll(combinacion);
			conjuntoNuevo.add(accesorio);

			combinacionesAccesoriosValidasConRepeticion.add(conjuntoNuevo);

			this.armarAccesorios(conjuntoNuevo);
		}
	}

	public Set<Prenda> conseguirAccesorios(){

		Set<Prenda> accesorios = prendasSegunCategoria(Categoria.CABEZA);
		accesorios.addAll(prendasSegunCategoria(Categoria.CUELLO));
		accesorios.addAll(prendasSegunCategoria(Categoria.CARA));
		accesorios.addAll(prendasSegunCategoria(Categoria.MANOS));
		return accesorios;
	}

	public void arnarCondicionInicialSuperiores(Prenda prenda){
		List<Prenda> conjuntoHastaAhora = new ArrayList<>();

		conjuntoHastaAhora.add(prenda);

		combinacionesSuperioresValidas.add(conjuntoHastaAhora);
		List<Prenda> conjunto = new ArrayList<>(conjuntoHastaAhora);
		this.armarParteSuperior(conjunto);
	}

	public void armarParteSuperior(List<Prenda> conjunto){
		List<Prenda> superioresAPonerArriba = this.armarSuperioresAPonerArriba(conjunto);
		if (superioresAPonerArriba.stream().anyMatch(prenda -> prenda.tipo.Capa() > conjunto.get(conjunto.size()-1).tipo.Capa())){
			superioresAPonerArriba.stream().forEach(unaPrenda-> this.completarParteSuperior(conjunto,unaPrenda));
		}
	}

	public void completarParteSuperior (List<Prenda> conjuntoHastaAhora, Prenda prenda){
		if(this.seCumpleCondicionParaAgregarSuperior(conjuntoHastaAhora,prenda) && !conjuntoHastaAhora.contains(prenda)){
			List<Prenda> conjuntoNuevo = new ArrayList<>();
			conjuntoNuevo.addAll(conjuntoHastaAhora);
			conjuntoNuevo.add(prenda);
			combinacionesSuperioresValidas.add(conjuntoNuevo);
			this.armarParteSuperior(conjuntoNuevo);
		}
	}

	public List<Prenda> aplanarLista (List<List<Prenda>> ListaAProcesar){
		List<Prenda> aplanada = ListaAProcesar.stream().flatMap(x -> x.stream()).collect(Collectors.toList());
		return aplanada;
	}


	public boolean seCumpleCondicionParaAgregarSuperior(List<Prenda> conjunto, Prenda prenda){
		if (prenda.tipo.Capa() == conjunto.get(conjunto.size()-1).tipo.Capa()){
			return conjunto.stream().filter(unaPrenda -> unaPrenda.tipo.Capa() == prenda.tipo.Capa() ).collect(Collectors.toList()).size()<2;

		}
		return true;
	}

	public boolean seCumpleCondicionParaAgregarAccesorio(Set<Prenda> combinacion, Prenda prenda){
		return combinacion.stream().allMatch(accesorio->accesorio.tipo.categoria()!=prenda.tipo.categoria());
	}

	public List<Prenda> arnarAccesoriosACombinar (Set<Prenda> combinacion){

		Set<Prenda> accesorios = conseguirAccesorios();

		return accesorios.stream().filter(unaPrenda->seCumpleCondicionParaAgregarAccesorio(combinacion,unaPrenda)).collect(Collectors.toList());
	}

	public List<Prenda> armarSuperioresAPonerArriba (List<Prenda> conjunto){

		Set<Prenda> superiores = prendasSegunCategoria(Categoria.PARTE_SUPERIOR);
		return superiores.stream().filter(unaPrenda->unaPrenda.tipo.Capa() >=conjunto.get(conjunto.size() - 1).tipo.Capa()).collect(Collectors.toList());
	}



	public Set<Atuendo> generarSugerencia(Double temperatura, Set<Atuendo> atuendos, boolean flexible, Usuario usuario) {
		if (flexible) {
			return atuendos.stream().filter(atuendo -> estaBienVestidoFlexible(atuendo, temperatura, usuario) && !atuendo.estaEnUso()).collect(Collectors.toSet());

		}
		else{
			return atuendos.stream().filter(atuendo -> estaBienVestido(atuendo, temperatura, usuario) && !atuendo.estaEnUso()).collect(Collectors.toSet());

		}
	}

	public Set<Atuendo> sugerirParaEvento(Evento evento, ProveedorClima proveedor, boolean flexible, Usuario usuario){
		Set<Atuendo> atuendos = this.generarAtuendos();
		double temperatura = proveedor.temperatura(evento.getFecha());

		return this.generarSugerencia(temperatura, atuendos, flexible, usuario);
	}

	public boolean estaBienVestido(Atuendo atuendo, Double temperatura, Usuario usuario){
		return atuendo.abrigoTotal()>= 36-temperatura && atuendo.abrigoTotal() <= 46 - temperatura && estaDistribuidoElAbrigo(atuendo, temperatura, usuario);
	}
	public boolean estaDistribuidoElAbrigo (Atuendo atuendo, Double temperatura, Usuario usuario){
		return estaAbrigadoEnSuperior(atuendo, temperatura, usuario)
				&& estaAbrigadoEnInferior(atuendo, temperatura, usuario)
				&& estaAbrigadoEnCalzado(atuendo,temperatura,usuario)
				&& estaAbrigadoEnAccesorios(atuendo,temperatura,usuario) ;

	}

	public boolean estaAbrigadoEnSuperior (Atuendo atuendo, Double temperatura, Usuario usuario){
		return atuendo.abrigoSuperior()>= (coeficienteDeTemperaturaPrincipal(usuario,Categoria.PARTE_SUPERIOR)-temperatura)*0.6;
	}
	public boolean estaAbrigadoEnInferior (Atuendo atuendo, Double temperatura, Usuario usuario){
		return atuendo.abrigoInferior()>= (coeficienteDeTemperaturaPrincipal(usuario,Categoria.PARTE_INFERIOR)-temperatura)*0.3;
	}
	public boolean estaAbrigadoEnCalzado (Atuendo atuendo, Double temperatura, Usuario usuario){
		return atuendo.abrigoCalzado()>= (coeficienteDeTemperaturaPrincipal(usuario,Categoria.CALZADO)-temperatura)*0.1;
	}
	public boolean estaAbrigadoEnAccesorios(Atuendo atuendo, Double temperatura, Usuario usuario){
		return estaAbrigadoEnMano(atuendo,temperatura,usuario)
				&& estaAbrigadoEnCabeza(atuendo,temperatura,usuario)
				&& estaAbrigadoEnCara(atuendo,temperatura,usuario)
				&& estaAbrigadoEnCuello(atuendo,temperatura,usuario);
	}
	public boolean estaAbrigadoEnMano (Atuendo atuendo, Double temperatura, Usuario usuario){
		return atuendo.abrigoManos()>coeficienteDeTemperaturaAccesorios(usuario, Categoria.MANOS);
	}
	public boolean estaAbrigadoEnCuello(Atuendo atuendo, Double temperatura, Usuario usuario){
		return atuendo.abrigoCuello()>coeficienteDeTemperaturaAccesorios(usuario, Categoria.CUELLO);
	}
	public boolean estaAbrigadoEnCara (Atuendo atuendo, Double temperatura, Usuario usuario){
		return atuendo.abrigoCara()>coeficienteDeTemperaturaAccesorios(usuario, Categoria.CARA);
	}
	public boolean estaAbrigadoEnCabeza(Atuendo atuendo, Double temperatura, Usuario usuario){
		return atuendo.abrigoCabeza()>coeficienteDeTemperaturaAccesorios(usuario, Categoria.CABEZA);
	}




	public int coeficienteDeTemperaturaPrincipal(Usuario usuario, Categoria categoria){
		if(usuario.FriolentoEn().contains(categoria)) {
				return 40;//Da un valor mayor ya que al ser mayor, requiere de un abrigo mayor, significando que es friolento
		}
		if(usuario.CalurosoEn().contains(categoria)){
			return 30;    //Da un valor menor ya que al ser menor, requiere de un abrigo menor, significando que es caluroso
		}
		return 36;
	}
	public int coeficienteDeTemperaturaAccesorios(Usuario usuario, Categoria categoria){
		if(usuario.FriolentoEn().contains(categoria)) {
			return 5; //Da un valor porque si es friolento, requiere de abrigo en ese sector
		}
		return 0; // Da 0 porque si no es friolento, no requiere de abrigo en ese sector y puede usar accesorios que no abriguen (anillo)
	}





	public boolean estaBienVestidoFlexible(Atuendo atuendo, Double temperatura, Usuario usuario){
		return atuendo.abrigoTotal()>= 26-temperatura && atuendo.abrigoTotal() <= 56 - temperatura && estaDistribuidoElAbrigo(atuendo, temperatura,usuario);
	}
	public boolean estaDistribuidoElAbrigoFlexible (Atuendo atuendo, Double temperatura){
		return atuendo.abrigoSuperior()>= (26-temperatura)*0.6 && atuendo.abrigoInferior()>= (26-temperatura)*0.3 && atuendo.abrigoCalzado() >= (26-temperatura)*0.1;
	}

	public void agregarAAceptados(Atuendo atuendo){
		this.atuendosAceptados.add(atuendo);
	}
	public void agregarARechazados(Atuendo atuendo){
		this.atuendosRechazados.add(atuendo);
	}
	public void quitarDeAceptados(Atuendo atuendo){
		this.atuendosAceptados.remove(atuendo);
	}
	public void quitarDeRechazados(Atuendo atuendo){
		this.atuendosRechazados.remove(atuendo);
	}

	public boolean incluye(Atuendo atuendo){
		return atuendo.prendas().stream().allMatch(prenda -> incluye(prenda));
	}
}
