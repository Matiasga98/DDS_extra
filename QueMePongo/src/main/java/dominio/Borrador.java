package dominio;

import dominio.enumerados.Material;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;
import dominio.excepciones.*;

import static java.util.Objects.requireNonNull;
import javax.persistence.*;

@Entity
public class Borrador {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Enumerated
	@Column(name = "tipo")
	private Tipo tipo;

	@AttributeOverrides({
			@AttributeOverride(name="rojo",column=@Column(name="RojoPrimario")),
			@AttributeOverride(name="verde",column=@Column(name="VerdePrimario")),
			@AttributeOverride(name="azul",column=@Column(name="AzulPrimario"))
	})
	@Embedded
	private Color colorPrimario;

	@AttributeOverrides({
			@AttributeOverride(name="rojo",column=@Column(name="RojoSecundario")),
			@AttributeOverride(name="verde",column=@Column(name="VerdeSecundario")),
			@AttributeOverride(name="azul",column=@Column(name="AzulSecundario"))
	})
	@Embedded
	private Color colorSecundario;
	
	@Enumerated
	@Column(name = "trama")
	private Trama trama = Trama.LISA;
	
	@Enumerated
	@Column(name = "material")
	Material material;

	public void definirNombre(String nombre) {
		this.nombre = nombre;
	}
	
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
		requireNonNull(nombre, "Se requiere un nombre");
		requireNonNull(tipo, "Se requiere un tipo");
		requireNonNull(material, "Se requiere un material");
		requireNonNull(colorPrimario, "Se requiere un color primario");

		chequearColorDistinto();
		chequearMaterialSegunTipoDePrenda();

		return new Prenda(nombre, tipo, material, trama, colorPrimario, colorSecundario);
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
