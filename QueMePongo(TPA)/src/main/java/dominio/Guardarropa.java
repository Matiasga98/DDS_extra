package dominio;

import java.util.HashSet;
import java.util.List;

import java.util.Objects;
import java.util.Set;

import com.google.common.collect.Sets;

import excepciones.NoHayPrendas;

public class Guardarropa {
	Set<Prenda> superiores;
	Set<Prenda> inferiores;
	Set<Prenda> calzados;
	Set<Prenda> accesorios;

	public Guardarropa(Set<Prenda> superiores, Set<Prenda> inferiores, Set<Prenda> calzados, Set<Prenda> accesorios) {
		this.superiores = validarPrendasNoNulas(superiores, "Hay una prenda nula en las prendas superiores");
		this.inferiores = validarPrendasNoNulas(inferiores, "Hay una prenda nula en las prendas inferiores");
		this.calzados = validarPrendasNoNulas(calzados, "Hay una prenda nula en las prendas calzados");
		this.accesorios = validarPrendasNoNulas(accesorios, "Hay una prenda nula en las prendas accesorios");
	}

	// Este metodo es por el requerimiento de que "Las personas pueden acceder a sus
	// prendas a través de un guardarropas"

	public void mostrarPrendas() {
		System.out.println("Superiores:");
		superiores.forEach(prenda -> System.out.println(prenda.getNombre()));
		System.out.println();
		System.out.println("Inferiores:");
		inferiores.forEach(prenda -> System.out.println(prenda.getNombre()));
		System.out.println();
		System.out.println("Calzados:");
		calzados.forEach(prenda -> System.out.println(prenda.getNombre()));
		System.out.println();
		System.out.println("Accesorios:");
		accesorios.forEach(prenda -> System.out.println(prenda.getNombre()));
		System.out.println();
	}

	public void sugerir() {
		Objects.requireNonNull(superiores, "Se necesita una lista de prendas superiores");
		Objects.requireNonNull(inferiores, "Se necesita una lista de prendas inferiores");
		Objects.requireNonNull(calzados, "Se necesita una lista de calzados");

		Set<List<Prenda>> setDelistasDeAtuendosSinAccesorios = Sets.cartesianProduct(superiores, inferiores, calzados);
		if (accesorios.isEmpty()) {
			setDelistasDeAtuendosSinAccesorios.forEach(lista -> mostrarSugerenciasDeUnaListaDePrendas(lista));
		} else {
			Set<List<Prenda>> setDelistasDeAtuendosConAccesorios = Sets.cartesianProduct(superiores, inferiores,
					calzados, accesorios);
			// El producto cartesiano de la libreria me devuelve un set inmutable por esa
			// razon agregamos est
			Set<List<Prenda>> setDeListasDeAtuendosDefinitivo = new HashSet<List<Prenda>>();
			setDeListasDeAtuendosDefinitivo.addAll(setDelistasDeAtuendosSinAccesorios);
			setDeListasDeAtuendosDefinitivo.addAll(setDelistasDeAtuendosConAccesorios);
			setDeListasDeAtuendosDefinitivo.forEach(lista -> mostrarSugerenciasDeUnaListaDePrendas(lista));
		}

	}

	private void mostrarSugerenciasDeUnaListaDePrendas(List<Prenda> lista) {
		Atuendo miAtuendo;
		if(lista.size() == 4) {
			miAtuendo = new Atuendo(lista.get(0), lista.get(1), lista.get(2), lista.get(3));
		}
		else {
			miAtuendo = new Atuendo(lista.get(0), lista.get(1), lista.get(2), null);
		}
		miAtuendo.mostrarAtuendo();
		System.out.println();
		
		//lista.forEach(prenda -> System.out.println(prenda.getNombre()));
		//System.out.println();
	}

	private Set<Prenda> validarPrendasNoNulas(Set<Prenda> unasPrendas, String mensaje) throws NoHayPrendas {
		if (unasPrendas.stream().anyMatch((Prenda prenda) -> prenda == null)) {
			throw new NoHayPrendas(mensaje);
		}
		return unasPrendas;
	}

	public void setPrendaSuperior(Prenda unaPrenda) {
		superiores.add(unaPrenda);
	}

	public void setPrendaInferior(Prenda unaPrenda) {
		inferiores.add(unaPrenda);
	}

	public void setPrendaCalzado(Prenda unaPrenda) {
		calzados.add(unaPrenda);
	}

	public void setPrendaAccesorio(Prenda unaPrenda) {
		accesorios.add(unaPrenda);
	}
}
