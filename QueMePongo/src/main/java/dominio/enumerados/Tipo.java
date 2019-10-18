package dominio.enumerados;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public enum Tipo {
    //Dejamos esto asi porque consideramos la posibilidad que los demas tipos tambien
    // Tengan distintas capas de prendas como es el caso de Superiores en esta segunda
    // Entrega
    ZAPATO("zapato",Categoria.CALZADO, Capa.INFERIOR, 4,Material.CUERO, Material.GOMA),
    ZAPATOGRUESO("zapatoGrueso",Categoria.CALZADO,Capa.INFERIOR,8,Material.CUERO,Material.GOMA),
    REMERA("remera",Categoria.PARTE_SUPERIOR, Capa.INFERIOR, 5,Material.ALGODON, Material.LANA),
    PULOVER("pulover",Categoria.PARTE_SUPERIOR, Capa.MEDIA, 9,Material.ALGODON, Material.LANA),
    BUZO("buzo",Categoria.PARTE_SUPERIOR, Capa.ALTA, 10,Material.ALGODON),
    CAMISA("camisa",Categoria.PARTE_SUPERIOR, Capa.INFERIOR, 3,Material.POLIESTER),
    CAMPERA("campera",Categoria.PARTE_SUPERIOR, Capa.FINAL, 13, Material.CUERO),
    CAMPERALANA("camperalana",Categoria.PARTE_SUPERIOR,Capa.FINAL, 16, Material.LANA),
    PANTALON("pantalon",Categoria.PARTE_INFERIOR, Capa.INFERIOR, 8, Material.JEAN),
    PANTALONINVIERNO("pantaloninvierno",Categoria.PARTE_INFERIOR,Capa.INFERIOR,15,Material.ALGODON),
    SHORT("short",Categoria.PARTE_INFERIOR, Capa.INFERIOR, 4, Material.ALGODON),
    POLLERA("pollera",Categoria.PARTE_INFERIOR, Capa.INFERIOR, 2, Material.ALGODON, Material.LANA),
    ANILLO("anillo",Categoria.MANOS, Capa.INFERIOR, 0,Material.ORO, Material.DIAMANTE),
    GUANTES("guantes",Categoria.MANOS,Capa.INFERIOR,5,Material.LANA),
    LENTES("lentes",Categoria.CARA,Capa.INFERIOR,0,Material.VIDRIO),
    BALACLAVA("balaclava",Categoria.CARA,Capa.INFERIOR,7,Material.LANA),
    GORRO("gorro",Categoria.CABEZA,Capa.INFERIOR,7,Material.LANA),
    BUFANDA("bufanda",Categoria.CUELLO, Capa.INFERIOR, 7, Material.LANA);


    List<Material> materiales = new ArrayList<>();
    Categoria categoria;
    Capa capa;
    int puntajeAbrigo;
    String nombre;

    public String getNombre() {
        return nombre;
    }

    Tipo(String nombre, Categoria categoria, Capa unaCapa, int puntajeAbrigo, Material... materiales) {
        this.nombre= nombre;
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
