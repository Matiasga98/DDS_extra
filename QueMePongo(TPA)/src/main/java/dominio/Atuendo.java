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
	
	public void mostrarAtuendo() {
		System.out.println(superior.getNombre());
		System.out.println(inferior.getNombre());
		System.out.println(calzado.getNombre());
		if(accesorio!=null)
			System.out.println(accesorio.getNombre());
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
