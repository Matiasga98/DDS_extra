package dominio;

import java.util.HashSet;
import java.util.Set;

public class Usuario {
	Set<Guardarropa> guardarropas = new HashSet<Guardarropa>();

	public Usuario(Set<Guardarropa> guardarropa) {
		this.guardarropas = guardarropa;
	}
	
	public void pedirTodasLasPrendas() {
		guardarropas.forEach(guardarropa->guardarropa.mostrarPrendas());
	}
	
	public void sugerirDeTodosLosGuardarropas(){
		guardarropas.forEach(guardarropa -> guardarropa.sugerir());
	}
}
