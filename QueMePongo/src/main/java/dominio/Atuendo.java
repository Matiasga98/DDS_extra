package dominio;

import dominio.enumerados.EstadoAtuendo;

import java.util.ArrayList;
import java.util.List;

public class Atuendo {
    List<Prenda> superiores = new ArrayList<Prenda>();
    Prenda inferior;
    Prenda calzado;
    Prenda accesorio;
    EstadoAtuendo estado;

    public Atuendo(List<Prenda> superiores, Prenda calzado, Prenda inferior, Prenda accesorio) {
        this.inferior = inferior;
        this.superiores.addAll(superiores);
        this.calzado = calzado;
        this.accesorio = accesorio;
    }

    public int abrigoTotal(){
        return inferior.tipo.PuntajeAbrigo() + calzado.tipo.PuntajeAbrigo() + accesorio.tipo.PuntajeAbrigo() + superiores.stream().mapToInt(prenda -> prenda.tipo.PuntajeAbrigo()).sum();
    }
    public void cambiarEstado(EstadoAtuendo unEstado){
        estado = unEstado;
    }

    /*public List<Prenda> prendas() {
        return prendasDelAtuendo;
    }*/

}
