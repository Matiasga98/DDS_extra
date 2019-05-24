package dominio;

import dominio.enumerados.*;

public class Prenda {

	Tipo tipo;
    Material material;
    Trama trama;
	Color colorPrimario;
	Color colorSecundario;


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

    public boolean puedePonerseAbajo() {
        return this.tipo.estado().equals(Estado.AMBOS) || this.tipo.estado().equals(Estado.INTERIOR);
    }
    public boolean puedePonerseArriba() {
        return this.tipo.estado().equals(Estado.AMBOS) || this.tipo.estado().equals(Estado.EXTERIOR);
    }
}
