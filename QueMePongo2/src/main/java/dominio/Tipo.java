package dominio;

import java.util.Arrays;
import java.util.List;

public class Tipo {

	// Constantes
	public static final Tipo ZAPATO = new Tipo(Categoria.CALZADO, Arrays.asList(Material.CUERO, Material.GOMA));
	public static final Tipo REMERA = new Tipo(Categoria.PARTE_SUPERIOR,
			Arrays.asList(Material.ALGODON, Material.LANA));
	public static final Tipo CAMISA = new Tipo(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.POLIESTER));
	public static final Tipo PANTALON = new Tipo(Categoria.PARTE_INFERIOR, Arrays.asList(Material.JEAN));
	public static final Tipo POLLERA = new Tipo(Categoria.PARTE_INFERIOR,
			Arrays.asList(Material.ALGODON, Material.LANA));
	public static final Tipo ANILLO = new Tipo(Categoria.ACCESORIOS, Arrays.asList(Material.ORO, Material.DIAMANTE));
	//public static final Tipo VACIO = new Tipo(Categoria.ACCESORIOS, Arrays.asList((Material)null));
	
	List<Material> materialesValidados;
	Categoria categoria;

	public Tipo(Categoria categoria, List<Material> materiales) {
		this.categoria = categoria;
		this.materialesValidados = materiales;
	}

	public boolean permiteMaterial(Material unMaterial) {
		return materialesValidados.contains(unMaterial);
	}

	public Categoria categoria() {
		return this.categoria;
	}

}
