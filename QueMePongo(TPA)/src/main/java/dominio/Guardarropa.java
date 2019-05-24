package dominio;

import java.util.*;
import com.google.common.collect.Sets;
import dominio.enumerados.Categoria;
import dominio.enumerados.PrioridadSuperior;

public class Guardarropa {

	private Map<Categoria, Set<Prenda>> prendas = new HashMap<>();

	public Guardarropa() {
		prendas.put(Categoria.PARTE_SUPERIOR, new HashSet<>());
		prendas.put(Categoria.PARTE_INFERIOR, new HashSet<>());
		prendas.put(Categoria.CALZADO, new HashSet<>());
		prendas.put(Categoria.ACCESORIOS, new HashSet<>());
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

	public Set<Atuendo> generarAtuendos() {
		Set<Atuendo> atuendos = new HashSet<>();
		Set<Prenda> superiores = prendasSegunCategoria(Categoria.PARTE_SUPERIOR);
		Set<Prenda> inferiores = prendasSegunCategoria(Categoria.PARTE_INFERIOR);
		Set<Prenda> calzados = prendasSegunCategoria(Categoria.CALZADO);
		Set<Prenda> accesorios = prendasSegunCategoria(Categoria.ACCESORIOS);

		Set<Prenda> superioresBaja = (Set<Prenda>) superiores.stream().filter(prenda -> prenda.puedePonerseEn(PrioridadSuperior.BAJA));
		Set<Prenda> superioresMedia = (Set<Prenda>) superiores.stream().filter(prenda -> prenda.puedePonerseEn(PrioridadSuperior.MEDIA));
		Set<Prenda> superioresAlta = (Set<Prenda>) superiores.stream().filter(prenda -> prenda.puedePonerseEn(PrioridadSuperior.ALTA));

		Set<List<Prenda>> setDeAtuendosSinAccesoriosAbrigoBaja = Sets.cartesianProduct(
				superioresBaja,
				inferiores,
				calzados
		);

		setDeAtuendosSinAccesoriosAbrigoBaja.forEach(lista -> atuendos.add(new Atuendo(Arrays.asList(lista.get(0)),lista.get(1),lista.get(2),null)));

		Set<List<Prenda>> setDeAtuendosSinAccesoriosAbrigoMedio = Sets.cartesianProduct(
				superioresBaja,
				superioresMedia,
				inferiores,
				calzados
		);

		setDeAtuendosSinAccesoriosAbrigoMedio.forEach(lista -> atuendos.add(new Atuendo(Arrays.asList(lista.get(0),lista.get(1)),lista.get(2),lista.get(3),null)));


		Set<List<Prenda>> setDeAtuendosSinAccesoriosAbrigoAlta = Sets.cartesianProduct(
				superioresBaja,
				superioresMedia,
				superioresAlta,
				inferiores,
				calzados
		);

		setDeAtuendosSinAccesoriosAbrigoAlta.forEach(lista -> atuendos.add(new Atuendo(Arrays.asList(lista.get(0),lista.get(1),lista.get(2)),lista.get(3),lista.get(4),null)));

		if (accesorios.isEmpty()) {
			return atuendos;
		}
		
		Set<List<Prenda>> setDeAtuendosConAccesorios = Sets.cartesianProduct(
				superioresBaja,
				superioresMedia,
				superioresAlta,
				inferiores,
				calzados,
				accesorios);

		setDeAtuendosConAccesorios.forEach(lista -> atuendos.add(new Atuendo(Arrays.asList(lista.get(0)),lista.get(1),lista.get(2), lista.get(3))));

		return atuendos;
	}

	public Set<Atuendo> generarSugerencia(Temperatura temperatura){
		return (Set<Atuendo>) this.generarAtuendos().stream().filter(atuendo-> estaBienVestido(atuendo.abrigoTotal(), temperatura) );
	}

	public boolean estaBienVestido(int abrigo, Temperatura temperatura){
		return abrigo>= 36-temperatura.Value && abrigo <= 46 - temperatura.Value;
	}

}
