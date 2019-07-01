package dominio;

import java.util.*;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
import dominio.clima.ProveedorClima;
import dominio.enumerados.Categoria;


public class Guardarropa {

	private Map<Categoria, Set<Prenda>> prendas;
	private Set<Atuendo> atuendosAceptados;
	private Set<Atuendo> atuendosRechazados;
	private Set<List<Prenda>> combinacionesSuperioresValidas = new HashSet<List<Prenda>>();

	public Guardarropa() {
		prendas = new HashMap<>();
		atuendosAceptados = new HashSet<>();
		atuendosRechazados = new HashSet<>();
		prendas.put(Categoria.PARTE_SUPERIOR, new HashSet<>());
		prendas.put(Categoria.PARTE_INFERIOR, new HashSet<>());
		prendas.put(Categoria.CALZADO, new HashSet<>());
		prendas.put(Categoria.ACCESORIOS, new HashSet<>());
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
		Set<Prenda> accesorios = prendasSegunCategoria(Categoria.ACCESORIOS);

		//Method Mati
		List<Prenda> superioresDeCapaBaja = superiores.stream().filter(prenda -> prenda.tipo.Capa() <=2).collect(Collectors.toList());
		superioresDeCapaBaja.stream().forEach(prenda -> this.arnarCondicionInicialSuperiores(prenda));
		Set<List<Prenda>> atuendosSinSuperior = Sets.cartesianProduct(
				inferiores,
				calzados,
				accesorios
		);
		Set<List<List<Prenda>>> atuendosObtenidos = Sets.cartesianProduct(
				combinacionesSuperioresValidas,
				atuendosSinSuperior
		);
		Set<List<Prenda>> AtuendosFinales = new HashSet<>();

		atuendosObtenidos.stream().forEach(pipi->AtuendosFinales.add(this.aplanarLista(pipi)));

		AtuendosFinales.forEach(lista -> atuendos.add(new Atuendo(lista)));
		return atuendos;

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
		if(this.seCumpleCondicionParaAgregar(conjuntoHastaAhora,prenda) && !conjuntoHastaAhora.contains(prenda)){
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


	public boolean seCumpleCondicionParaAgregar(List<Prenda> conjunto, Prenda prenda){
		if (prenda.tipo.Capa() == conjunto.get(conjunto.size()-1).tipo.Capa()){
			return conjunto.stream().filter(unaPrenda -> unaPrenda.tipo.Capa() == prenda.tipo.Capa() ).collect(Collectors.toList()).size()<2;

		}
		return true;
	}

	public List<Prenda> armarSuperioresAPonerArriba (List<Prenda> conjunto){

		Set<Prenda> superiores = prendasSegunCategoria(Categoria.PARTE_SUPERIOR);
		return superiores.stream().filter(unaPrenda->unaPrenda.tipo.Capa() >=conjunto.get(conjunto.size() - 1).tipo.Capa()).collect(Collectors.toList());
	}



	public Set<Atuendo> generarSugerencia(Double temperatura, Set<Atuendo> atuendos, boolean flexible) {
		if (flexible) {
			return atuendos.stream().filter(atuendo -> estaBienVestidoFlexible(atuendo, temperatura) && !atuendo.estaEnUso()).collect(Collectors.toSet());

		}
		else{
			return atuendos.stream().filter(atuendo -> estaBienVestido(atuendo, temperatura) && !atuendo.estaEnUso()).collect(Collectors.toSet());

		}
	}

	public Set<Atuendo> sugerirParaEvento(Evento evento, ProveedorClima proveedor, boolean flexible){
		Set<Atuendo> atuendos = this.generarAtuendos();
		double temperatura = proveedor.temperatura(evento.getFecha());

		return this.generarSugerencia(temperatura, atuendos, flexible);
	}

	public boolean estaBienVestido(Atuendo atuendo, Double temperatura){
		return atuendo.abrigoTotal()>= 36-temperatura && atuendo.abrigoTotal() <= 46 - temperatura && estaDistribuidoElAbrigo(atuendo, temperatura);
	}
	public boolean estaDistribuidoElAbrigo (Atuendo atuendo, Double temperatura){
		return atuendo.abrigoSuperior()>= (36-temperatura)*0.6 && atuendo.abrigoInferior()>= (36-temperatura)*0.3 && atuendo.abrigoCalzado() >= (36-temperatura)*0.1;
	}

	public boolean estaBienVestidoFlexible(Atuendo atuendo, Double temperatura){
		return atuendo.abrigoTotal()>= 26-temperatura && atuendo.abrigoTotal() <= 56 - temperatura && estaDistribuidoElAbrigo(atuendo, temperatura);
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
