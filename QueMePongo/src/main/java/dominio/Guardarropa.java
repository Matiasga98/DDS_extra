package dominio;

import java.util.*;
import java.util.stream.Collectors;
import com.google.common.collect.Sets;
import dominio.clima.ProveedorClima;
import dominio.enumerados.Categoria;
import dominio.enumerados.PrioridadSuperior;

public class Guardarropa {

	private Map<Categoria, Set<Prenda>> prendas;
	private Set<Atuendo> atuendosAceptados;
	private Set<Atuendo> atuendosRechazados;
	
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

	//Cambiar este metodo. Al parecer hay un smellazo de code del tipo SO BIG METOD.
	public Set<Atuendo> generarAtuendos() {
		Set<Atuendo> atuendos = new HashSet<>();
		Set<Prenda> superiores = prendasSegunCategoria(Categoria.PARTE_SUPERIOR);
		Set<Prenda> inferiores = prendasSegunCategoria(Categoria.PARTE_INFERIOR);
		Set<Prenda> calzados = prendasSegunCategoria(Categoria.CALZADO);
		Set<Prenda> accesorios = prendasSegunCategoria(Categoria.ACCESORIOS);

		Set<Prenda> superioresBaja = superiores.stream().filter(prenda -> prenda.puedePonerseEn(PrioridadSuperior.BAJA)).collect(Collectors.toSet());
		Set<Prenda> superioresMedia = superiores.stream().filter(prenda -> prenda.puedePonerseEn(PrioridadSuperior.MEDIA)).collect(Collectors.toSet());
		Set<Prenda> superioresAlta = superiores.stream().filter(prenda -> prenda.puedePonerseEn(PrioridadSuperior.ALTA)).collect(Collectors.toSet());

		Set<List<Prenda>> setDeAtuendosSinAccesoriosAbrigoBaja = Sets.cartesianProduct(
				superioresBaja,
				inferiores,
				calzados
		);

		setDeAtuendosSinAccesoriosAbrigoBaja.forEach(lista -> atuendos.add(new Atuendo(lista)));

		Set<List<Prenda>> setDeAtuendosSinAccesoriosAbrigoMedio = Sets.cartesianProduct(
				superioresBaja,
				superioresMedia,
				inferiores,
				calzados
		);

		setDeAtuendosSinAccesoriosAbrigoMedio.forEach(lista -> atuendos.add(new Atuendo(lista)));


		Set<List<Prenda>> setDeAtuendosSinAccesoriosAbrigoAlta = Sets.cartesianProduct(
				superioresBaja,
				superioresMedia,
				superioresAlta,
				inferiores,
				calzados
		);

		setDeAtuendosSinAccesoriosAbrigoAlta.forEach(lista -> atuendos.add(new Atuendo(lista)));

		if (accesorios.isEmpty()) {
			return atuendos;
		}

		Set<List<Prenda>> setDeAtuendosConAccesoriosAbrigoBaja = Sets.cartesianProduct(
				superioresBaja,
				inferiores,
				calzados,
				accesorios
		);

		setDeAtuendosConAccesoriosAbrigoBaja.forEach(lista -> atuendos.add(new Atuendo(lista)));

		Set<List<Prenda>> setDeAtuendosConAccesoriosAbrigoMedio = Sets.cartesianProduct(
				superioresBaja,
				superioresMedia,
				inferiores,
				calzados,
				accesorios
		);

		setDeAtuendosConAccesoriosAbrigoMedio.forEach(lista -> atuendos.add(new Atuendo(lista)));

		Set<List<Prenda>> setDeAtuendosConAccesoriosAbrigoAlta = Sets.cartesianProduct(
				superioresBaja,
				superioresMedia,
				superioresAlta,
				inferiores,
				calzados,
				accesorios
		);

		setDeAtuendosConAccesoriosAbrigoAlta.forEach(lista -> atuendos.add(new Atuendo(lista)));

		return atuendos;
	}

	public Set<Atuendo> generarSugerencia(Double temperatura, Set<Atuendo> atuendos, boolean flexible) {
		if (flexible) {
			return atuendos.stream().filter(atuendo -> estaBienVestidoFlexible(atuendo.abrigoTotal(), temperatura) && !atuendo.estaEnUso()).collect(Collectors.toSet());

		}
		else{
			return atuendos.stream().filter(atuendo -> estaBienVestido(atuendo.abrigoTotal(), temperatura) && !atuendo.estaEnUso()).collect(Collectors.toSet());

		}
	}

	public Set<Atuendo> sugerirParaEvento(Evento evento, ProveedorClima proveedor, boolean flexible){
		Set<Atuendo> atuendos = this.generarAtuendos();
		double temperatura = proveedor.temperatura(evento.getFecha());

		return this.generarSugerencia(temperatura, atuendos, flexible);
	}

	public boolean estaBienVestido(int abrigo, Double temperatura){
		return abrigo>= 36-temperatura && abrigo <= 46 - temperatura;
	}
	public boolean estaBienVestidoFlexible(int abrigo, Double temperatura){
		return abrigo>= 26-temperatura && abrigo <= 56 - temperatura;
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
