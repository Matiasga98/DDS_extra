package dominio;

import dominio.enumerados.*;

import javax.persistence.*;

@Entity
public class Prenda {
	
	@Id
	@GeneratedValue
    long id;

    @Column(name="nombre")
	String nombre;

	@Enumerated
    @Column(name="tipo")
	Tipo tipo;

	@Enumerated
    @Column(name="material")
    Material material;

	@Enumerated
    @Column(name="trama")
    Trama trama;

	@Embedded
    Color colorPrimario;

	@Embedded
    Color colorSecundario;

    @Column(name="enUso")
	boolean enUso;

    //Getters y Setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo(){
        return tipo;
    }
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Material getMaterial() {
        return material;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }

    public Trama getTrama() {
        return trama;
    }
    public void setTrama(Trama trama) {
        this.trama = trama;
    }

    public Color getColorPrimario() {
        return colorPrimario;
    }
    public void setColorPrimario(Color colorPrimario) {
        this.colorPrimario = colorPrimario;
    }

    public Color getColorSecundario() {
        return colorSecundario;
    }
    public void setColorSecundario(Color colorSecundario) {
        this.colorSecundario = colorSecundario;
    }

    public boolean getEnUso() {
        return enUso;
    }
    public void setEnUso(boolean enUso) {
        this.enUso = enUso;
    }

    public Prenda(String nombre, Tipo tipo, Material material, Trama trama, Color colorPrimario, Color colorSecundario) {
		this.nombre = nombre;
    	this.tipo = tipo;
        this.material = material;
        this.trama = trama;
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
		this.enUso = false;
	}

	public Prenda() {

	}

	public String nombre() {
    	return nombre;
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
