package dominio;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public interface Usuario {

    Set<Guardarropa> guardarropas();

    void agregarGuardarropa(Guardarropa guardarropa);
    void agregarPrenda(Prenda prenda, Guardarropa guardarropa);
    Set<Atuendo> sugerenciasDeAtuendosDeTodosLosGuardarropas();


}
