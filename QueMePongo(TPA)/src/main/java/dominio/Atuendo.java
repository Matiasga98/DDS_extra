package dominio;

import java.util.List;

public class Atuendo {
    List<Prenda> prendasDelAtuendo;

    public Atuendo(List<Prenda> prendasDelAtuendo) {
        this.prendasDelAtuendo = prendasDelAtuendo;
    }

    public List<Prenda> prendas() {
        return prendasDelAtuendo;
    }

}
