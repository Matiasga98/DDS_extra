package dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Color {
	@Id
	@GeneratedValue
	private long id;
	int rojo, verde, azul;

	public long getId(){
	    return id;
    }
    public void setId(long id){
	    this.id = id;
    }

    public int getRojo() {
        return rojo;
    }

    public void setRojo(int rojo) {
        this.rojo = rojo;
    }

    public int getAzul() {
        return azul;
    }

    public void setAzul(int azul) {
        this.azul = azul;
    }

    public int getVerde() {
        return verde;
    }

    public void setVerde(int verde) {
        this.verde = verde;
    }

    public Color(int rojo, int verde, int azul) {
		this.rojo = rojo;
		this.verde = verde;
		this.azul = azul;
	}

	public Color() {

	}

	public boolean esIgualA(Color color) {
        return color != null && this.rojo == color.rojo &&
                this.verde == color.verde &&
                this.azul == color.azul;
	}

}
