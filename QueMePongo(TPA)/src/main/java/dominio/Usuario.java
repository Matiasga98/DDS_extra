package dominio;

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

}
