package dominio;

import dominio.enumerados.Material;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;
import dominio.excepciones.*;

import static java.util.Objects.requireNonNull;

public class Borrador {
	Tipo tipo;
	Color colorPrimario;
	Color colorSecundario;
	Trama trama = Trama.LISA;
	Material material;

	public void definirTipo(Tipo tipoPrenda) {
		this.tipo = tipoPrenda;
	}

	public void definirColorPrimario(Color colorPrimario) {
		this.colorPrimario = colorPrimario;
	}

	public void definirColorSecundario(Color colorSecundario) {
		this.colorSecundario = colorSecundario;
	}

	public void definirTrama(Trama trama) {
		this.trama = trama;
	}

	public void definirMaterial(Material material) {
		this.material = material;
	}

	public Prenda crearPrenda() {
		requireNonNull(tipo, "Se requiere un tipo");
		requireNonNull(material, "Se requiere un material");
		requireNonNull(colorPrimario, "Se requiere un color primario");

		chequearColorDistinto();
		chequearMaterialSegunTipoDePrenda();

		return new Prenda(tipo, material, trama, colorPrimario, colorSecundario);
	}

	private void chequearColorDistinto() {
		if (colorPrimario.esIgualA(colorSecundario))
			throw new RequiereColorDistinto("El color secundario debe ser distinto del primario");
	}

	private void chequearMaterialSegunTipoDePrenda() {
		if (!tipo.permiteMaterial(material))
			throw new MaterialInconsistente("El material elegido no es compatible con el tipo de prenda");
	}

}
