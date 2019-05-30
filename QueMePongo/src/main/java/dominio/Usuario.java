package dominio;

import dominio.clima.ProveedorClima;
import dominio.enumerados.EstadoAtuendo;
import dominio.excepciones.AtuendoNoPerteneceAGuardarropa;
import dominio.excepciones.SuperoLaCantidadDePrendas;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Usuario {
    private Set<Guardarropa> guardarropas;
    private Set<Evento> eventos;
    private boolean esPremium;
    private int prendasMaximas = 20;

    public Usuario() {
        this.guardarropas = new HashSet<>();
        this.eventos = new HashSet<>();
        this.esPremium = false;
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
        return guardarropa.sugerirParaEvento(evento, unProveedor, flexible );
    }
    public void aceptarSugerencia (Atuendo atuendo, Guardarropa guardarropa){
        guardarropaIncluyeAtuendo(guardarropa,atuendo);
        guardarropa.agregarAAceptados(atuendo);
        atuendo.cambiarEstado(EstadoAtuendo.ACEPTADO);
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


}
