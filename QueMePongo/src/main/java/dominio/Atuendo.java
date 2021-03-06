package dominio;

import dominio.enumerados.Categoria;
import dominio.enumerados.EstadoAtuendo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Atuendo {

    @Id
    @GeneratedValue
    @Column(name="id")
    private long id;

    public long getId() {
        return id;
    }

    public Atuendo(long id, List<Prenda> prendas) {
        this.id = id;
        this.prendas = prendas;
        this.estado = EstadoAtuendo.NUEVO;
    }
    public Atuendo(){}

    @ManyToMany
    private List<Prenda> prendas = new ArrayList<Prenda>();

    @Enumerated
    private EstadoAtuendo estado;


    public void setPrendas(List<Prenda> prendas) {
        this.prendas = prendas;
    }
    public List<Prenda> getPrendas() {
        return prendas;
    }

    public void setEstado(EstadoAtuendo estado) {
        this.estado = estado;
    }
    public EstadoAtuendo getEstado(){
        return estado;
    }

    public void setAtuendo(EstadoAtuendo estado){
        this.estado = estado;
    }

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

    public int abrigoEn(Categoria categoria){
        if(categoria != Categoria.PARTE_SUPERIOR && tienePrendaEn(categoria)) {
            return ObtenerPrendaEnCategoria(categoria).tipo.PuntajeAbrigo();
        }
        else if (categoria != Categoria.PARTE_SUPERIOR  && !tienePrendaEn(categoria)){
            return 0;
        }
        return abrigoSuperior();
    }

    public boolean tienePrendaEn(Categoria categoria){
        return !(this.prendas().stream().filter(prenda -> prenda.tipo().categoria() == categoria).collect(Collectors.toList()).size() == 0);
    }

    public int abrigoSuperior(){
        return superiores().stream().mapToInt(prenda -> prenda.tipo.PuntajeAbrigo()).sum();
    }
    public int abrigoInferior(){ return  inferiores().tipo.PuntajeAbrigo(); }
    public int abrigoCalzado(){ return calzado().tipo.PuntajeAbrigo(); }
    public int abrigoManos() {return accesorioMano().tipo.PuntajeAbrigo();}
    public int abrigoCuello() {return accesorioCuello().tipo.PuntajeAbrigo();}
    public int abrigoCabeza() {return accesorioCabeza().tipo.PuntajeAbrigo();}
    public int abrigoCara() {return accesorioCara().tipo.PuntajeAbrigo();}


    public Prenda ObtenerPrendaEnCategoria(Categoria categoria){
            return prendas.stream().filter(prenda->prenda.tipo.categoria() == categoria).collect(Collectors.toList()).get(0);
    }


    public List<Prenda> superiores(){
        return prendas.stream().filter(prenda->prenda.tipo.categoria() == Categoria.PARTE_SUPERIOR).collect(Collectors.toList());
    }
    public Prenda inferiores(){
        return prendas.stream().filter(prenda->prenda.tipo.categoria() == Categoria.PARTE_INFERIOR).collect(Collectors.toList()).get(0);
    }
    public Prenda calzado(){
        return prendas.stream().filter(prenda->prenda.tipo.categoria() == Categoria.CALZADO).collect(Collectors.toList()).get(0);
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



    //public List<Prenda> accesorio(){
     //   return prendas.stream().filter(prenda->prenda.tipo.categoria() == Categoria.ACCESORIOS).collect(Collectors.toList());
    //}




    public void cambiarEstado(EstadoAtuendo unEstado){
        estado = unEstado;
    }

    public List<Prenda> prendas() {
        return prendas;
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
