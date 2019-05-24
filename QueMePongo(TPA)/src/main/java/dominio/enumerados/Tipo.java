package dominio.enumerados;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public enum Tipo {
    //Dejamos esto asi porque consideramos la posibilidad que los demas tipos tambien
    // Tengan distintas capas de prendas como es el caso de Superiores en esta segunda
    // Entrega
    ZAPATO(Categoria.CALZADO, PrioridadSuperior.SINPRIORIDAD, 1,Material.CUERO, Material.GOMA),
    REMERA(Categoria.PARTE_SUPERIOR, PrioridadSuperior.BAJA, 5,Material.ALGODON, Material.LANA),
    PULOVER(Categoria.PARTE_SUPERIOR, PrioridadSuperior.MEDIA, 9,Material.ALGODON, Material.LANA),
    BUZO(Categoria.PARTE_SUPERIOR, PrioridadSuperior.MEDIA, 10,Material.ALGODON),
    CAMISA(Categoria.PARTE_SUPERIOR, PrioridadSuperior.MEDIABAJA, 3,Material.POLIESTER),
    CAMPERA(Categoria.PARTE_SUPERIOR, PrioridadSuperior.ALTA, 13, Material.CUERO),
    CAMPERALANA(Categoria.PARTE_SUPERIOR, PrioridadSuperior.ALTA, 16, Material.LANA),
    PANTALON(Categoria.PARTE_INFERIOR, PrioridadSuperior.SINPRIORIDAD, 5, Material.JEAN),
    SHORT(Categoria.PARTE_INFERIOR, PrioridadSuperior.SINPRIORIDAD, 2, Material.ALGODON),
    POLLERA(Categoria.PARTE_INFERIOR, PrioridadSuperior.SINPRIORIDAD, 2, Material.ALGODON, Material.LANA),
    ANILLO(Categoria.ACCESORIOS, PrioridadSuperior.SINPRIORIDAD, 0,Material.ORO, Material.DIAMANTE),
    BUFANDA(Categoria.ACCESORIOS, PrioridadSuperior.SINPRIORIDAD, 7, Material.LANA);

    List<Material> materiales = new ArrayList<>();
    Categoria categoria;
    PrioridadSuperior prioridadSuperior;
    int puntajeAbrigo;

    Tipo(Categoria categoria, PrioridadSuperior prioridadSuperior, int puntajeAbrigo, Material... materiales) {
        this.categoria = categoria;
        this.materiales.addAll(asList(materiales));
        this.prioridadSuperior = prioridadSuperior;
        this.puntajeAbrigo = puntajeAbrigo;
    }

    public boolean permiteMaterial(Material material) {
        return materiales.contains(material);
    }

    public Categoria categoria() {
        return this.categoria;
    }
    public PrioridadSuperior estado() {
        return this.prioridadSuperior;
    }
    public int PuntajeAbrigo() {
        return this.puntajeAbrigo;
    }
}
