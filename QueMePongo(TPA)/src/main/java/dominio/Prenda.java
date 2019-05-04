package dominio;

import dominio.enumerados.Categoria;
import dominio.enumerados.Material;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;

public class Prenda {

	Tipo tipo;
    Material material;
    Trama trama;
	Color colorPrimario;
	Color colorSecundario;
	int temperaturaAdecuada;

    public Prenda(Tipo tipo, Material material, Trama trama, Color colorPrimario, Color colorSecundario) {
		this.tipo = tipo;
        this.material = material;
        this.trama = trama;
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
	}

    public Tipo tipo() {
		return tipo;
	}

    public Material material() {
        return material;
    }

    public Trama trama() {
        return trama;
    }

    public Color colorPrimario() {
        return colorPrimario;
    }

    public Color colorSecundario() {
        return colorSecundario;
    }

	public Categoria categoria() {
		return tipo.categoria();
	}

	public int temperaturaAdecuada() {
        return temperaturaAdecuada;
    }

    public boolean esAdecuada(int temperatura) {
        return this.temperaturaAdecuada >= temperatura;
    }
}
