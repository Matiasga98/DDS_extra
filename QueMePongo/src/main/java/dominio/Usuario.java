package dominio;

import dominio.clima.ProveedorClima;
import dominio.enumerados.Categoria;
import dominio.enumerados.EstadoAtuendo;
import dominio.excepciones.AtuendoNoPerteneceAGuardarropa;
import dominio.excepciones.SuperoLaCantidadDePrendas;
import org.uqbar.commons.model.Entity;
import org.uqbar.commons.model.annotations.Observable;
import dominio.Notificadores.Notificador;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Observable
public class Usuario extends Entity {

	private Set<Notificador> mediosDeNotificacion;
    private String nombre;
    private Set<Guardarropa> guardarropas;
    private Set<Evento> eventos;
    private boolean esPremium = false;
    private int prendasMaximas = 20;
    private Set<Categoria> friolentoEn = new HashSet<>();
    private Set<Categoria> calurosoEn = new HashSet<>();

    public  Usuario(String nombre, Set<Evento> eventos){
        this.nombre = nombre;
        this.eventos = eventos;
    }

    public Usuario(boolean esPago) {
        this.guardarropas = new HashSet<>();
        this.eventos = new HashSet<>();
        this.esPremium = esPago;
        this.prendasMaximas = 20;
    }
    public Set<Categoria> FriolentoEn(){
        return friolentoEn;
    }

    public Set<Categoria> CalurosoEn(){
        return calurosoEn;
    }

    public Set<Guardarropa> guardarropas() {
        return guardarropas;
    }
    public void agregarGuardarropa(Guardarropa guardarropa) {
        guardarropas.add(guardarropa);
    }

    public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
        if(esPremium){
            guardarropa.agregarPrendas(prenda);
        }else{
            if (superoElLimiteDePrendas(guardarropa)) {
                throw new SuperoLaCantidadDePrendas("No te queda mas lugar pipi");
            }
            guardarropa.agregarPrendas(prenda);
        }
    }

    public Set<Atuendo> sugerenciasDeAtuendosDeTodosLosGuardarropas() {
        return guardarropas.stream().flatMap(guardarropa -> guardarropa.generarAtuendos().stream()).collect(Collectors.toSet());
    }

    public void agregarEvento (Evento unEvento){
        eventos.add(unEvento);
    }

    public Set<Atuendo> pedirSugerenciaParaEvento (Evento evento, Guardarropa guardarropa, ProveedorClima unProveedor, boolean flexible){
        return guardarropa.sugerirParaEvento(evento, unProveedor, flexible, this );
    }
    public void aceptarSugerencia (Atuendo atuendo, Guardarropa guardarropa){
        guardarropaIncluyeAtuendo(guardarropa,atuendo);
        guardarropa.agregarAAceptados(atuendo);
        atuendo.cambiarEstado(EstadoAtuendo.ACEPTADO);
        atuendo.enUso();
    }
    public void rechazarSugerencia (Atuendo atuendo, Guardarropa guardarropa){
        guardarropaIncluyeAtuendo(guardarropa,atuendo);
        guardarropa.agregarARechazados(atuendo);
        atuendo.cambiarEstado(EstadoAtuendo.RECHAZADO);
    }

    public void deshacerDecision (Atuendo atuendo, Guardarropa guardarropa){
        guardarropaIncluyeAtuendo(guardarropa,atuendo);

        if (atuendo.rechazado()){
            guardarropa.quitarDeRechazados(atuendo);
        }

        if(atuendo.aceptado()){
            guardarropa.quitarDeAceptados(atuendo);
        }

        atuendo.cambiarEstado(EstadoAtuendo.NUEVO);
    }

    private void guardarropaIncluyeAtuendo(Guardarropa guardarropa, Atuendo atuendo){
        if(guardarropa.incluye(atuendo)){
            return;
        }
        throw new AtuendoNoPerteneceAGuardarropa("El atuendo no pertenece al guardarropa");
    }

    private boolean superoElLimiteDePrendas(Guardarropa guardarropa){
        return prendasMaximas < guardarropa.cantidadPrendas();
    }

    public void compartirGuardarropa(Guardarropa guardarropa, Usuario usuario){
    	usuario.agragarGuardarropa(guardarropa);
    }
    
    public void agragarGuardarropa(Guardarropa guardarropa){
    	this.guardarropas().add(guardarropa);
    }
    
    public Set<Atuendo> pedirSugerenciaParaEventoDeTodosLosGuadaropas(Evento evento, ProveedorClima proveedor, boolean flexible) {
    	Set<Atuendo> atuendos = new HashSet<Atuendo>();
    	this.guardarropas().forEach(guardarropa -> atuendos.addAll(guardarropa.sugerirParaEvento(evento, proveedor, flexible, this)));
    	return atuendos;
    }

    public Set<Evento> getEventos() {
        return eventos;
    }

    public String getNombre() {
        return nombre;
    }
    
    public Set<Notificador> getMediosDeNotificacion() {
    	return mediosDeNotificacion;
    }
    
    public void setMediosDeNotificacion(Set<Notificador> medios) {
    	this.mediosDeNotificacion = medios;
    }
    
    public void agregarMedioDeNotificacion(Notificador medio) {
    	this.getMediosDeNotificacion().add(medio);
    }
    
    public void removerMedioDeNotificacion(Notificador medio) {
    	this.getMediosDeNotificacion().remove(medio);
    }
    
    public Set<Atuendo> notificarme(Evento evento, ProveedorClima proveedor, boolean flexible) {
    	Set<Atuendo> sugerencias = this.pedirSugerenciaParaEventoDeTodosLosGuadaropas(evento, proveedor, flexible);
    	this.getMediosDeNotificacion().forEach(medio -> medio.notificar(sugerencias));
    	return sugerencias;
    }
}
