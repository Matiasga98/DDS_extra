package dominio;

import dominio.enumerados.EstadoAtuendo;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Usuario {
    Set<Guardarropa> guardarropas;
    Set<Evento> eventos;

    public Usuario() {
        this.guardarropas = new HashSet<>();
    }

    public Set<Guardarropa> guardarropas() {
        return guardarropas;
    }
    public void agregarGuardarropa(Guardarropa guardarropa) {
        guardarropas.add(guardarropa);
    }

    public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
        guardarropa.agregarPrendas(prenda);
    }

    public Set<Atuendo> sugerenciasDeAtuendosDeTodosLosGuardarropas() {
        return guardarropas.stream().flatMap(guardarropa -> guardarropa.generarAtuendos().stream()).collect(Collectors.toSet());
    }

    public void agregarEvento (Evento unEvento){
        eventos.add(unEvento);
    }

    public Set<Atuendo> pedirSugerenciaParaEvento (Evento evento, Guardarropa guardarropa, ProveedorClima unProveedor, boolean flexible){
        return guardarropa.sugerirParaEvento(evento, unProveedor, flexible );
    }
    public void aceptarSugerencia (Atuendo atuendo, Guardarropa guardarropa){
        guardarropa.agregarAAceptados(atuendo);
        atuendo.cambiarEstado(EstadoAtuendo.ACEPTADO);
    }
    public void rechazarSugerencia (Atuendo atuendo, Guardarropa guardarropa){
        guardarropa.agregarARechazados(atuendo);
        atuendo.cambiarEstado(EstadoAtuendo.RECHAZADO);
    }

    public void deshacerDecision (Atuendo atuendo, Guardarropa guardarropa){
        if (atuendo.estado .equals(EstadoAtuendo.RECHAZADO)){
            guardarropa.atuendosRechazados.remove(atuendo);
        }
        else{
            guardarropa.atuendosAceptados.remove(atuendo);
        }
        atuendo.cambiarEstado(EstadoAtuendo.NUEVO);
    }


}
