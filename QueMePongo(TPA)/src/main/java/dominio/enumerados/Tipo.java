package dominio.enumerados;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public enum Tipo {

    ZAPATO(Categoria.CALZADO, Material.CUERO, Material.GOMA),
    REMERA(Categoria.PARTE_SUPERIOR, Material.ALGODON, Material.LANA),
    CAMISA(Categoria.PARTE_SUPERIOR, Material.POLIESTER),
    PANTALON(Categoria.PARTE_INFERIOR, Material.JEAN),
    POLLERA(Categoria.PARTE_INFERIOR, Material.ALGODON, Material.LANA),
    ANILLO(Categoria.ACCESORIOS, Material.ORO, Material.DIAMANTE);

    List<Material> materiales = new ArrayList<>();
    Categoria categoria;

    Tipo(Categoria categoria, Material... materiales) {
        this.categoria = categoria;
        this.materiales.addAll(asList(materiales));
    }

    public boolean permiteMaterial(Material material) {
        return materiales.contains(material);
    }

    public Categoria categoria() {
        return this.categoria;
    }

}
