package dominio;

import java.util.Objects;

import excepciones.MaterialInconsistente;
import excepciones.RequiereColorDistinto;

public class Borrador {
	Tipo tipo;
	Color colorPrimario;
	Color colorSecundario;
	Trama trama = Trama.LISA;
	Material material;
	String nombre;
	Guardarropa guardarropa;

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
	
	public void definirNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void definirGuardarropa(Guardarropa guardarropa) {
		this.guardarropa = guardarropa;
	}

	public Prenda crearPrenda() throws MaterialInconsistente {
		Objects.requireNonNull(colorPrimario, "Se requiere un color primario");
		chequearColorDistinto(colorPrimario, colorSecundario);
		Objects.requireNonNull(trama, "Se requiere una trama");
		Objects.requireNonNull(material, "Se requiere un material");
		Objects.requireNonNull(tipo, "Se requiere un tipo");
		Objects.requireNonNull(guardarropa, "Se requiere un guardarropa al que pertenece la prenda");
		if (tipo.permiteMaterial(material)) {
			Prenda unaPrenda = new Prenda(tipo, colorPrimario, colorSecundario, trama, material, nombre, guardarropa);
			
			 switch(unaPrenda.tipo.categoria) {
				case CALZADO:
					guardarropa.setPrendaCalzado(unaPrenda);
					break;
				case PARTE_SUPERIOR:
					guardarropa.setPrendaSuperior(unaPrenda);
					break;
				case PARTE_INFERIOR:
					guardarropa.setPrendaInferior(unaPrenda);
					break;
				case ACCESORIOS:
					guardarropa.setPrendaAccesorio(unaPrenda);
					break;
				default:
					break;
			}
			 
			 return unaPrenda;
		}
			throw new MaterialInconsistente("El material elegido no es compatible con el tipo de prenda");
	}

	private void chequearColorDistinto(Color color1, Color color2) {
		if (color1.esIgualA(color2))
			throw new RequiereColorDistinto("El color secundario debe ser distinto del primario");
	}

}
