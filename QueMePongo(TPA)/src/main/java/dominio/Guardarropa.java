package dominio;

import java.util.*;

import com.google.common.collect.Sets;

import dominio.enumerados.Categoria;

public class Guardarropa {

	private Map<Categoria, Set<Prenda>> prendas;

	public Guardarropa() {
		prendas = new HashMap<>();
		prendas.put(Categoria.PARTE_SUPERIOR, new HashSet<>());
		prendas.put(Categoria.PARTE_INFERIOR, new HashSet<>());
		prendas.put(Categoria.CALZADO, new HashSet<>());
		prendas.put(Categoria.ACCESORIOS, new HashSet<>());
	}

	public void agregarPrendas(Prenda prenda) {
		prendas.get(prenda.categoria()).add(prenda);
	}

	public Set<Prenda> prendasSegunCategoria(Categoria categoria) {
		return prendas.get(categoria);
	}

	public Set<Atuendo> sugerenciasDeAtuendos() {
		Set<Atuendo> atuendos = new HashSet<>();


		Set<Prenda> superiores = prendasSegunCategoria(Categoria.PARTE_SUPERIOR);
		Set<Prenda> inferiores = prendasSegunCategoria(Categoria.PARTE_INFERIOR);
		Set<Prenda> calzados = prendasSegunCategoria(Categoria.CALZADO);
		Set<Prenda> accesorios = prendasSegunCategoria(Categoria.ACCESORIOS);

		Set<Prenda> superioresQueSePuedenPonerDebajo = (Set<Prenda>) superiores.stream().filter(prenda -> prenda.puedePonerseAbajo());

		Set<Prenda> superioresQueSePuedenPonerArriba = (Set<Prenda>) superiores.stream().filter(prenda -> prenda.puedePonerseArriba());

		Set<List<Prenda>> setDeAtuendosSinAccesoriosSinAbrigo = Sets.cartesianProduct(
				superioresQueSePuedenPonerDebajo,
				inferiores,
				calzados
		);

		setDeAtuendosSinAccesoriosSinAbrigo.forEach(lista -> atuendos.add(new Atuendo(Arrays.asList(lista.get(0)),lista.get(1),lista.get(2),null));

		Set<List<Prenda>> setDeAtuendosSinAccesoriosConAbrigo = Sets.cartesianProduct(
				superioresQueSePuedenPonerDebajo,
				superioresQueSePuedenPonerArriba,
				inferiores,
				calzados
		);


		setDeAtuendosSinAccesoriosConAbrigo.forEach(lista -> atuendos.add(new Atuendo(lista)));

		if (accesorios.isEmpty()) {
			return atuendos;
		}
		Set<List<Prenda>> setDeAtuendosConAccesorios = Sets.cartesianProduct(
				superioresQueSePuedenPonerDebajo,
				superioresQueSePuedenPonerArriba,
				calzados,
				accesorios);

		setDeAtuendosConAccesorios.forEach(lista -> atuendos.add(new Atuendo(lista)));

		return atuendos;
	}

}
