package dominio;

import java.util.Objects;

public class Atuendo {
	Prenda superior;
	Prenda inferior;
	Prenda calzado;
	Prenda accesorio;

	public Atuendo(Prenda superior, Prenda inferior, Prenda calzado, Prenda accesorio) {
		this.superior = Objects.requireNonNull(superior, "Falta prenda superior en el atuendo");
		this.inferior = Objects.requireNonNull(inferior, "Falta prenda inferior en el atuendo");
		this.calzado = Objects.requireNonNull(calzado, "Falta calzado en el atuendo");
		this.accesorio = accesorio;
	}
	
	public Prenda getSuperior() {
		return superior;
	}
	
	public Prenda getInferior() {
		return inferior;
	}
	
	public Prenda getCalzado() {
		return calzado;
	}
	
	public Prenda getAccesorio() {
		return accesorio;
	}
}
