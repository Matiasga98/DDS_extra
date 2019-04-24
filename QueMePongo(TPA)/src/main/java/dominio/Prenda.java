package dominio;

public class Prenda {

	Tipo tipo;
	Color colorPrimario;
	Color colorSecundario;
	Trama trama;
	Material material;
	Guardarropa guardarropaAlQuePertenece;

	// Esta variable esta puesta para diferenciar entre 2 prendas iguales
	// en parametros pero que son diferentes
	String nombre;

	public Prenda(Tipo tipo, Color colorPrimario, Color colorSecundario, Trama trama, Material material,
			String nombre, Guardarropa unGuardarropa) {
		this.tipo = tipo;
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
		this.trama = trama;
		this.material = material;
		this.nombre = nombre;
		this.guardarropaAlQuePertenece = unGuardarropa;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public Categoria categoria() {
		return tipo.categoria();
	}

	public String getNombre() {
		return nombre;
	}

}
