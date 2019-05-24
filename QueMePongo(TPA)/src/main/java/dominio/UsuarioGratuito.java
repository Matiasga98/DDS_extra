package dominio;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioGratuito implements Usuario {

    Set<Guardarropa> guardarropas;
    public int prendasMaximas = 20;
    public UsuarioGratuito() {
        this.guardarropas = new HashSet<>();
    }

    public Set<Guardarropa> guardarropas() {
        return guardarropas;
    }
   public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
        if (prendasMaximas == guardarropa.cantidadPrendas()){
            throw new RuntimeException("no te queda mas lugar pipi");
        }
       guardarropa.agregarPrendas(prenda);
    }

    public void agregarGuardarropa(Guardarropa guardarropa) {
        guardarropas.add(guardarropa);
    }


    public Set<Atuendo> sugerenciasDeAtuendosDeTodosLosGuardarropas() {
        return guardarropas.stream().flatMap(guardarropa -> guardarropa.generarAtuendos().stream()).collect(Collectors.toSet());
    }
}