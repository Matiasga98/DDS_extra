package dominio;

import java.util.ArrayList;
import java.util.List;

public class Atuendo {
    List<Prenda> superiores = new ArrayList<Prenda>();
    Prenda inferior;
    Prenda calzado;
    Prenda accesorio;

    public Atuendo(List<Prenda> superiores, Prenda calzado, Prenda inferior, Prenda accesorio) {
        this.inferior = inferior;
        this.superiores.addAll(superiores);
        this.calzado = calzado;
        this.accesorio = accesorio;
    }

    /*public List<Prenda> prendas() {
        return prendasDelAtuendo;
    }*/

}
