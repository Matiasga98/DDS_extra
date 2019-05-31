package dominio;

import dominio.enumerados.EstadoAtuendo;

import java.util.ArrayList;
import java.util.List;

public class Atuendo {
    private List<Prenda> prendas = new ArrayList<Prenda>();
    private EstadoAtuendo estado;

    public Atuendo(List<Prenda> prendas) {
        this.prendas.addAll(prendas);
        this.estado = EstadoAtuendo.NUEVO;
    }

    public int abrigoTotal(){
        return prendas.stream().mapToInt(prenda -> prenda.tipo.PuntajeAbrigo()).sum();
    }
    public void cambiarEstado(EstadoAtuendo unEstado){
        estado = unEstado;
    }

    public List<Prenda> prendas() {
        return prendas;
    }

    public EstadoAtuendo getEstado() {
        return estado;
    }

    public void setEstado(EstadoAtuendo estado) {
        this.estado = estado;
    }

    public boolean aceptado(){
        return EstadoAtuendo.ACEPTADO.equals(estado);
    }

    public boolean rechazado(){
        return EstadoAtuendo.RECHAZADO.equals(estado);
    }


}
