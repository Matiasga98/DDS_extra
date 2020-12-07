package dominio;

import dominio.enumerados.*;
import dominio.excepciones.*;

import static java.util.Objects.requireNonNull;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

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

	@Transient
	List<Tipo> Tipos = new ArrayList<Tipo>(EnumSet.allOf(Tipo.class));

	@Transient
	List<Material> Materiales = new ArrayList<Material>(EnumSet.allOf(Material.class));

	@Transient
	List<Trama> Tramas = new ArrayList<Trama>(EnumSet.allOf(Trama.class));

	@Transient
	public List<Categoria> categoria = new ArrayList<dominio.enumerados.Categoria>(EnumSet.allOf(Categoria.class));

	@Transient
	List<SuceptibilidadATemperatura> suceptibilidades = new ArrayList<dominio.enumerados.SuceptibilidadATemperatura>(EnumSet.allOf(SuceptibilidadATemperatura.class));

	@Transient
	Boolean materialInconsistente = false;

	public List<Tipo> getTipos() {
		return Tipos;
	}

	public List<Material> getMateriales() {
		return Materiales;
	}

	public List<Trama> getTramas() {
		return Tramas;
	}

	public List<Categoria> getCategoria() {
		return categoria;
	}

	public List<SuceptibilidadATemperatura> getSuceptibilidades() {
		return suceptibilidades;
	}

	public Boolean getMaterialInconsistente(){ return materialInconsistente;}
	public void setMaterialInconsistente(Boolean bool){ this.materialInconsistente = bool;}

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

	public void chequearColorDistinto() {
		if (colorPrimario.esIgualA(colorSecundario))
			throw new RequiereColorDistinto("El color secundario debe ser distinto del primario");
	}

	public void chequearMaterialSegunTipoDePrenda() {
		if (!tipo.permiteMaterial(material)) {
			this.materialInconsistente = true;
			throw new MaterialInconsistente("El material elegido no es compatible con el tipo de prenda");
		}
	}






}


