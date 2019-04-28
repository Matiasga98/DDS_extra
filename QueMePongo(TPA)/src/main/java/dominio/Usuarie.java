package dominio;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Usuarie {

    Set<Guardarropa> guardarropas;

    public Usuarie() {
        this.guardarropas = new HashSet<>();
    }

    public Set<Guardarropa> guardarropas() {
        return guardarropas;
    }

    public void agregarGuardarropa(Guardarropa guardarropa) {
        guardarropas.add(guardarropa);
    }

    public Set<Atuendo> sugerenciasDeAtuendosDeTodosLosGuardarropas() {
        return guardarropas.stream().flatMap(guardarropa -> guardarropa.sugerenciasDeAtuendos().stream()).collect(Collectors.toSet());
    }


}
