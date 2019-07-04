package dominio;

import dominio.enumerados.Categoria;
import dominio.enumerados.EstadoAtuendo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Atuendo {
    private List<Prenda> prendas = new ArrayList<Prenda>();
    private EstadoAtuendo estado;

    public Atuendo(List<Prenda> prendas) {
        this.prendas.addAll(prendas);
        this.estado = EstadoAtuendo.NUEVO;
    }

    public void mostrarPrendas (){
        prendas.stream().forEach(prenda -> System.out.println(prenda.tipo));
        System.out.println("---");
    }

    public int abrigoTotal(){
        return prendas.stream().mapToInt(prenda -> prenda.tipo.PuntajeAbrigo()).sum();
    }

    public int abrigoSuperior(){
        return superiores().stream().mapToInt(prenda -> prenda.tipo.PuntajeAbrigo()).sum();
    }
    public int abrigoInferior(){
        return inferiores().stream().mapToInt(prenda -> prenda.tipo.PuntajeAbrigo()).sum();
    }
    public int abrigoCalzado(){
        return calzado().stream().mapToInt(prenda -> prenda.tipo.PuntajeAbrigo()).sum();
    }
    public int abrigoManos() {return accesorioMano().tipo.PuntajeAbrigo();}
    public int abrigoCuello() {return accesorioCuello().tipo.PuntajeAbrigo();}
    public int abrigoCabeza() {return accesorioCabeza().tipo.PuntajeAbrigo();}
    public int abrigoCara() {return accesorioCara().tipo.PuntajeAbrigo();}

    public List<Prenda> superiores(){
        return prendas.stream().filter(prenda->prenda.tipo.categoria() == Categoria.PARTE_SUPERIOR).collect(Collectors.toList());
    }
    public List<Prenda> inferiores(){
        return prendas.stream().filter(prenda->prenda.tipo.categoria() == Categoria.PARTE_INFERIOR).collect(Collectors.toList());
    }
    public List<Prenda> calzado(){
        return prendas.stream().filter(prenda->prenda.tipo.categoria() == Categoria.CALZADO).collect(Collectors.toList());
    }
    public Prenda accesorioMano(){
        return prendas.stream().filter(prenda->prenda.tipo.categoria() == Categoria.MANOS).collect(Collectors.toList()).get(0);
    }
    public Prenda accesorioCuello(){
        return prendas.stream().filter(prenda->prenda.tipo.categoria() == Categoria.CUELLO).collect(Collectors.toList()).get(0);
    }
    public Prenda accesorioCabeza(){
        return prendas.stream().filter(prenda->prenda.tipo.categoria() == Categoria.CABEZA).collect(Collectors.toList()).get(0);
    }
    public Prenda accesorioCara(){
        return prendas.stream().filter(prenda->prenda.tipo.categoria() == Categoria.CARA).collect(Collectors.toList()).get(0);
    }



    public List<Prenda> accesorio(){
        return prendas.stream().filter(prenda->prenda.tipo.categoria() == Categoria.ACCESORIOS).collect(Collectors.toList());
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

    public void enUso(){
    	this.prendas().forEach(prenda -> prenda.setEnUso());
    }
    
    public boolean estaEnUso(){
    	return this.prendas().stream().anyMatch(prenda -> prenda.enUso());
    }
    
    public String toString() {
    	String string = "Atuendo {";
    	for (Prenda prenda : this.prendas)
    		string += prenda.nombre() + ", ";
    	string += "}\n";
    	return string;
    }
}
