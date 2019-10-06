package dominio.enumerados;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public enum Tipo {
    //Dejamos esto asi porque consideramos la posibilidad que los demas tipos tambien
    // Tengan distintas capas de prendas como es el caso de Superiores en esta segunda
    // Entrega
    ZAPATO(Categoria.CALZADO, Capa.INFERIOR, 4,Material.CUERO, Material.GOMA),
    REMERA(Categoria.PARTE_SUPERIOR, Capa.INFERIOR, 5,Material.ALGODON, Material.LANA),
    PULOVER(Categoria.PARTE_SUPERIOR, Capa.MEDIA, 9,Material.ALGODON, Material.LANA),
    BUZO(Categoria.PARTE_SUPERIOR, Capa.ALTA, 10,Material.ALGODON),
    CAMISA(Categoria.PARTE_SUPERIOR, Capa.INFERIOR, 3,Material.POLIESTER),
    CAMPERA(Categoria.PARTE_SUPERIOR, Capa.FINAL, 13, Material.CUERO),
    CAMPERALANA(Categoria.PARTE_SUPERIOR,Capa.FINAL, 16, Material.LANA),
    PANTALON(Categoria.PARTE_INFERIOR, Capa.INFERIOR, 8, Material.JEAN),
    PANTALONINVIERNO(Categoria.PARTE_INFERIOR,Capa.INFERIOR,15,Material.ALGODON),
    SHORT(Categoria.PARTE_INFERIOR, Capa.INFERIOR, 4, Material.ALGODON),
    POLLERA(Categoria.PARTE_INFERIOR, Capa.INFERIOR, 2, Material.ALGODON, Material.LANA),
    ANILLO(Categoria.MANOS, Capa.INFERIOR, 0,Material.ORO, Material.DIAMANTE),
    GUANTES(Categoria.MANOS,Capa.INFERIOR,5,Material.LANA),
    LENTES(Categoria.CARA,Capa.INFERIOR,0,Material.VIDRIO),
    BALACLAVA(Categoria.CARA,Capa.INFERIOR,7,Material.LANA),
    GORRO(Categoria.CABEZA,Capa.INFERIOR,5,Material.LANA),
    BUFANDA(Categoria.CUELLO, Capa.INFERIOR, 7, Material.LANA);


    List<Material> materiales = new ArrayList<>();
    Categoria categoria;
    Capa capa;
    int puntajeAbrigo;

    Tipo(Categoria categoria, Capa unaCapa, int puntajeAbrigo, Material... materiales) {
        this.categoria = categoria;
        this.materiales.addAll(asList(materiales));

        this.capa = unaCapa;
        this.puntajeAbrigo = puntajeAbrigo;
    }

    public boolean permiteMaterial(Material material) {
        return materiales.contains(material);
    }

    public Categoria categoria() {
        return this.categoria;
    }
    public int PuntajeAbrigo() {
        return this.puntajeAbrigo;
    }
    public Capa Capa() {
        return this.capa;
    }
}
