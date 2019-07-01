package dominio;

import dominio.enumerados.*;

public class Prenda {

	Tipo tipo;
    Material material;
    Trama trama;
	Color colorPrimario;
	Color colorSecundario;
	boolean enUso;

    public Prenda(Tipo tipo, Material material, Trama trama, Color colorPrimario, Color colorSecundario) {
		this.tipo = tipo;
        this.material = material;
        this.trama = trama;
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
		this.enUso = false;
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

	public boolean enUso(){
		return this.enUso;
	}
	


	public void setEnUso(){
		this.enUso = true;
	}
	
	public void setNoEnUso(){
		this.enUso = false;
	}
	
    /*
    public boolean puedePonerseAbajo() {
        return this.tipo.estado().equals(PrioridadSuperior.MEDIABAJA) || this.tipo.estado().equals(PrioridadSuperior.BAJA);
    }
    public boolean puedePonerseArriba() {
        return this.tipo.estado().equals(PrioridadSuperior.MEDIA) || this.tipo.estado().equals(PrioridadSuperior.ALTA);
    }*/
}
