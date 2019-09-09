package dominio;

import java.util.*;
import java.util.stream.Collectors;
import com.google.common.collect.Sets;
import dominio.clima.ProveedorClima;
import dominio.enumerados.Categoria;

import javax.persistence.*;

@Entity
public class Guardarropa {
   	@Id
    @GeneratedValue
    private long guardarropaId;

   	//Esperando resoluciÃ³n de Roli
	@Transient
	private Map<Categoria, Set<Prenda>> prendas;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "atuendos_aceptados", joinColumns = @JoinColumn(name = "guardarropaId"))
	@Column(name = "atuendoId")
	private Set<Atuendo> atuendosAceptados;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "atuendos_rechazados", joinColumns = @JoinColumn(name = "guardarropaId"))
	@Column(name = "atuendoId")
	private Set<Atuendo> atuendosRechazados;

    public long getGuardarropaId() {
        return guardarropaId;
    }
    public void setGuardarropaId(long guardarropaId) {
        this.guardarropaId = guardarropaId;
    }

    public Map<Categoria, Set<Prenda>> getPrendas() {
        return prendas;
    }
    public void setPrendas(Map<Categoria, Set<Prenda>> prendas) {
        this.prendas = prendas;
    }

    public Set<Atuendo> getAtuendosAceptados() {
        return atuendosAceptados;
    }
    public void setAtuendosAceptados(Set<Atuendo> atuendosAceptados) {
        this.atuendosAceptados = atuendosAceptados;
    }

    public Set<Atuendo> getAtuendosRechazados() {
        return atuendosRechazados;
    }
    public void setAtuendosRechazados(Set<Atuendo> atuendosRechazados) {
        this.atuendosRechazados = atuendosRechazados;
    }

    public Guardarropa() {
		prendas = new HashMap<>();
		atuendosAceptados = new HashSet<>();
		atuendosRechazados = new HashSet<>();
		prendas.put(Categoria.PARTE_SUPERIOR, new HashSet<>());
		prendas.put(Categoria.PARTE_INFERIOR, new HashSet<>());
		prendas.put(Categoria.CALZADO, new HashSet<>());
		//prendas.put(Categoria.ACCESORIOS, new HashSet<>());
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

	public void removerPrenda(Prenda prenda) {
		prendas.get(prenda.categoria()).remove(prenda);
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
		Set<List<Prenda>> combinacionesSuperioresValidas = new HashSet<List<Prenda>>();
		Set<List<Prenda>> combinacionesAccesoriosValidas = new HashSet<List<Prenda>>();


		//Method Mati
		combinacionesSuperioresValidas = Categoria.PARTE_SUPERIOR.obtenerCombinacionSuperiores(superiores);
		combinacionesAccesoriosValidas = Categoria.obtenerCombinacionAccesorios(accesorios);

		Set<List<Prenda>> atuendosSinSuperior = Sets.cartesianProduct(
				inferiores,
				calzados
		);



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

	public Set<Prenda> conseguirAccesorios(){

		Set<Prenda> accesorios = prendasSegunCategoria(Categoria.CABEZA);
		accesorios.addAll(prendasSegunCategoria(Categoria.CUELLO));
		accesorios.addAll(prendasSegunCategoria(Categoria.CARA));
		accesorios.addAll(prendasSegunCategoria(Categoria.MANOS));
		return accesorios;
	}



	public List<Prenda> aplanarLista (List<List<Prenda>> ListaAProcesar){
		List<Prenda> aplanada = ListaAProcesar.stream().flatMap(x -> x.stream()).collect(Collectors.toList());
		return aplanada;
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
		double temperatura = proveedor.temperatura(evento.getFechaYHora());
		evento.tieneAlertasMeteorológicas = proveedor.tieneAlertasMeteorológicas(evento.getFechaYHora());

		return this.generarSugerencia(temperatura, atuendos, flexible, usuario);
	}

	public boolean estaBienVestido(Atuendo atuendo, Double temperatura, Usuario usuario){
		return estaDistribuidoElAbrigo(atuendo, temperatura, usuario);
	}
	public boolean estaDistribuidoElAbrigo (Atuendo atuendo, Double temperatura, Usuario usuario){
		return Categoria.estaAbrigadoEn(atuendo, temperatura, usuario, usuario.coeficienteEn(Categoria.PARTE_SUPERIOR), Categoria.PARTE_SUPERIOR)
				&& Categoria.estaAbrigadoEn(atuendo, temperatura, usuario,usuario.coeficienteEn(Categoria.PARTE_INFERIOR), Categoria.PARTE_INFERIOR)
				&& Categoria.estaAbrigadoEn(atuendo,temperatura,usuario, usuario.coeficienteEn(Categoria.CALZADO), Categoria.CALZADO)
				&& Categoria.estaAbrigadoEn(atuendo,temperatura,usuario, usuario.coeficienteEn(Categoria.CARA), Categoria.CARA)
				&& Categoria.estaAbrigadoEn(atuendo,temperatura,usuario, usuario.coeficienteEn(Categoria.CABEZA), Categoria.CABEZA)
				&& Categoria.estaAbrigadoEn(atuendo,temperatura,usuario, usuario.coeficienteEn(Categoria.CUELLO), Categoria.CUELLO)
				&& Categoria.estaAbrigadoEn(atuendo,temperatura,usuario,usuario.coeficienteEn(Categoria.MANOS), Categoria.MANOS);

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

	public void procesarCalificacion (Calificacion calificacion, Usuario usuario){
		usuario.FriolentoEn().addAll(calificacion.friolentoEn);
		calificacion.friolentoEn.stream().forEach(categoria -> usuario.friolentarEn(categoria));
		usuario.CalurosoEn().addAll(calificacion.calurosoEn);
		calificacion.calurosoEn.stream().forEach(categoria -> usuario.calentarEn(categoria));

	}

}
