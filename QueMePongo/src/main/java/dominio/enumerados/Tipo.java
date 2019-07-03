package dominio.enumerados;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public enum Tipo {
    //Dejamos esto asi porque consideramos la posibilidad que los demas tipos tambien
    // Tengan distintas capas de prendas como es el caso de Superiores en esta segunda
    // Entrega
    ZAPATO(Categoria.CALZADO, 0, 4,Material.CUERO, Material.GOMA),
    REMERA(Categoria.PARTE_SUPERIOR, 1, 5,Material.ALGODON, Material.LANA),
    PULOVER(Categoria.PARTE_SUPERIOR, 3, 9,Material.ALGODON, Material.LANA),
    BUZO(Categoria.PARTE_SUPERIOR, 3, 10,Material.ALGODON),
    CAMISA(Categoria.PARTE_SUPERIOR, 2, 3,Material.POLIESTER),
    CAMPERA(Categoria.PARTE_SUPERIOR, 4, 13, Material.CUERO),
    CAMPERALANA(Categoria.PARTE_SUPERIOR, 4, 16, Material.LANA),
    PANTALON(Categoria.PARTE_INFERIOR, 0, 8, Material.JEAN),
    PANTALONINVIERNO(Categoria.PARTE_INFERIOR,0,15,Material.ALGODON),
    SHORT(Categoria.PARTE_INFERIOR, 0, 4, Material.ALGODON),
    POLLERA(Categoria.PARTE_INFERIOR, 0, 2, Material.ALGODON, Material.LANA),
    ANILLO(Categoria.ACCESORIOS, 1, 0,Material.ORO, Material.DIAMANTE),
    GUANTES(Categoria.ACCESORIOS,1,5,Material.LANA),
    LENTES(Categoria.ACCESORIOS,3,0,Material.VIDRIO),
    GORRO(Categoria.ACCESORIOS,4,5,Material.LANA),
    BUFANDA(Categoria.ACCESORIOS, 2, 7, Material.LANA);


    List<Material> materiales = new ArrayList<>();
    Categoria categoria;
    int capa;
    int puntajeAbrigo;

    Tipo(Categoria categoria, int unaCapa, int puntajeAbrigo, Material... materiales) {
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
    public int estado() {
        return this.capa;
    }
    public int PuntajeAbrigo() {
        return this.puntajeAbrigo;
    }
    public int Capa() {
        return this.capa;
    }
}
