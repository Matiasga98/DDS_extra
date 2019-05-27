package dominio;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import dominio.enumerados.Categoria;
import dominio.enumerados.PrioridadSuperior;
import dominio.excepciones.NoSeEncuentraLaFecha;

public class Guardarropa {

	private Map<Categoria, Set<Prenda>> prendas = new HashMap<>();

	public Set<Atuendo> atuendosAceptados;
	public Set<Atuendo> atuendosRechazados;

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

	public Set<Atuendo> generarSugerencia(Double temperatura, Set<Atuendo> atuendos, boolean flexible) {
		if (flexible) {
			return (Set<Atuendo>) atuendos.stream().filter(atuendo -> estaBienVestidoFlexible(atuendo.abrigoTotal(), temperatura));

		}
		else{
			return (Set<Atuendo>) atuendos.stream().filter(atuendo -> estaBienVestido(atuendo.abrigoTotal(), temperatura));

		}
	}

	public Set<Atuendo> sugerirParaEvento(Evento evento, ProveedorClima proveedor, boolean flexible){
		Set<Atuendo> atuendos = this.generarAtuendos();
		Gson gson = new Gson();
		//String path = "C:\\Users\\ALUMNO\\Desktop\\Nueva carpeta\\2019-vi-no-group-12\\QueMePongo(TPA)\\src\\main\\Clima.json";
		Clima clima = new Clima();
		String path = ".\\Clima.json";
		try (FileReader reader = new FileReader(path)) {
			clima = gson.fromJson(reader, Clima.class);
			System.out.println(clima.pronosticos.get(1).temperaturaPromedio);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		if (clima.pronosticos.stream().noneMatch(pronostico -> pronostico.fecha.equals(evento.fecha))){
			clima = proveedor.obtenerClima();
		}
		Pronostico pronosticoParaElEvento = clima.pronosticos.stream().filter(pronostico -> pronostico.fecha.equals(evento.fecha)).findAny().orElse(null);
		if (pronosticoParaElEvento.equals(null)){
			throw new NoSeEncuentraLaFecha("Falta demasiado para el evento, probar mas proximo al mismo");
		}


			return this.generarSugerencia(pronosticoParaElEvento.temperaturaPromedio, atuendos, flexible);
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

}
