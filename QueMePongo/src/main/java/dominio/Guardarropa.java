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

   	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "guardarropaId")
	private Set<PrendasPorCategoria> prendas;

	@ElementCollection
	@CollectionTable(name = "atuendos_aceptados", joinColumns = @JoinColumn(name = "guardarropaId"))
	@Column(name = "atuendoId")
	private Set<Atuendo> atuendosAceptados;

	@ElementCollection
	@CollectionTable(name = "atuendos_rechazados", joinColumns = @JoinColumn(name = "guardarropaId"))
	@Column(name = "atuendoId")
	private Set<Atuendo> atuendosRechazados;

	private String nombre;
	
	public String getNombre() {
		return nombre;
	}
	
    public long getGuardarropaId() {
        return guardarropaId;
    }
    public void setGuardarropaId(long guardarropaId) {
        this.guardarropaId = guardarropaId;
    }

    public Set<PrendasPorCategoria> getPrendas() {
        return prendas;
    }
    public void setPrendas(Set<PrendasPorCategoria> prendas) {
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

    public Guardarropa(String nombre) {
    	this.nombre = nombre;
		prendas = new HashSet<>();
		atuendosAceptados = new HashSet<>();
		atuendosRechazados = new HashSet<>();
		prendas.add(new PrendasPorCategoria(Categoria.PARTE_SUPERIOR));
		prendas.add(new PrendasPorCategoria(Categoria.PARTE_INFERIOR));
		prendas.add(new PrendasPorCategoria(Categoria.CALZADO));
		//prendas.put(Categoria.ACCESORIOS, new HashSet<>());
		prendas.add(new PrendasPorCategoria(Categoria.CABEZA));
		prendas.add(new PrendasPorCategoria(Categoria.CARA));
		prendas.add(new PrendasPorCategoria(Categoria.CUELLO));
		prendas.add(new PrendasPorCategoria(Categoria.MANOS));
	}

	public boolean incluye(Prenda prenda){
		return prendas.stream().anyMatch(prendas -> prendas.getPrendas().contains(prenda));
	}

	public void agregarPrendas(Prenda prenda) {
		prendas.stream().forEach(prendas -> {if (prendas.getCategoria().equals(prenda.categoria())) prendas.getPrendas().add(prenda);});
	}

	public void removerPrenda(Prenda prenda) {
		prendas.stream().forEach(prendas -> {if (prendas.getCategoria().equals(prenda.categoria())) prendas.getPrendas().remove(prenda);});
	}
	
	public int cantidadPrendas() {
		return prendas.stream().mapToInt(prendas -> prendas.getPrendas().size()).sum();
	}

	public HashSet<Prenda> prendasSegunCategoria(Categoria categoria) {
		HashSet<Prenda> misPrendas = new HashSet<>();
		prendas.stream().forEach(prendas -> {if (prendas.getCategoria().equals(categoria)) misPrendas.addAll(prendas.getPrendas());});
		return misPrendas;
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
		Set<List<Prenda>> combinacionesSuperiores = new HashSet<List<Prenda>>();
		Set<List<Prenda>> combinacionesSuperioresValidas = new HashSet<List<Prenda>>();
		Set<List<Prenda>> combinacionesAccesorios = new HashSet<List<Prenda>>();
		Set<List<Prenda>> combinacionesAccesoriosValidas = new HashSet<List<Prenda>>();

		//Method Mati
		combinacionesSuperiores = Combinador.generarTodasLasCombinacionesPosibles(superiores.stream().toArray(Prenda[] :: new),7);
		combinacionesSuperioresValidas = Categoria.PARTE_SUPERIOR.eliminarCombinacionesInvalidasDeSuperior(combinacionesSuperiores);

		combinacionesAccesorios = Combinador.generarTodasLasCombinacionesPosibles(accesorios.stream().toArray(Prenda[] :: new),5);
		combinacionesAccesoriosValidas = Categoria.eliminarCombinacionesInvalidasDeAccesorios(combinacionesAccesorios);

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
		atuendosObtenidos.stream().forEach(pipi -> AtuendosFinales.add(this.aplanarLista(pipi)));
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
		evento.tieneAlertasMeteorologicas = proveedor.tieneAlertasMeteorologicas(evento.getFechaYHora());
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
    	calificacion.friolentoEn.stream().forEach(categoria -> usuario.calentarEn(categoria));
    	calificacion.calurosoEn.stream().forEach(categoria -> usuario.friolentarEn(categoria));
	}

}
