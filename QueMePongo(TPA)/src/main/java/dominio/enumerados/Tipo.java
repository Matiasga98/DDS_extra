package dominio.enumerados;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public enum Tipo {
    //Dejamos esto asi porque consideramos la posibilidad que los demas tipos tambien
    // Tengan distintas capas de prendas como es el caso de Superiores en esta segunda
    // Entrega
    ZAPATO(Categoria.CALZADO,Estado.SINESTADO, Material.CUERO, Material.GOMA),
    REMERA(Categoria.PARTE_SUPERIOR, Estado.INTERIOR, Material.ALGODON, Material.LANA),
    CAMISA(Categoria.PARTE_SUPERIOR, Estado.AMBOS, Material.POLIESTER),
    CAMPERA(Categoria.PARTE_SUPERIOR,Estado.EXTERIOR, Material.CUERO),
    PANTALON(Categoria.PARTE_INFERIOR,Estado.SINESTADO, Material.JEAN),
    POLLERA(Categoria.PARTE_INFERIOR,Estado.SINESTADO, Material.ALGODON, Material.LANA),
    ANILLO(Categoria.ACCESORIOS,Estado.SINESTADO, Material.ORO, Material.DIAMANTE);

    List<Material> materiales = new ArrayList<>();
    Categoria categoria;
    Estado estado;

    Tipo(Categoria categoria, Estado estado, Material... materiales) {
        this.categoria = categoria;
        this.materiales.addAll(asList(materiales));
        this.estado = estado;
    }

    public boolean permiteMaterial(Material material) {
        return materiales.contains(material);
    }

    public Categoria categoria() {
        return this.categoria;
    }
    public Estado estado() {
        return this.estado;
    }
}
