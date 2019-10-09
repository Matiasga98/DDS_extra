package repositorios;

import dominio.Color;
import dominio.Guardarropa;
import dominio.Prenda;
import dominio.Usuario;

public class RepositorioGuardarropas {
	
	private static final RepositorioGuardarropas INSTANCE = new RepositorioGuardarropas();

	public static RepositorioGuardarropas instance() {
		return INSTANCE;
	}

	public Guardarropa findByUsuario(Usuario usuario) {
		Guardarropa guardarropas = new Guardarropa("ropa sport");
		return guardarropas;
	}
	
	public Guardarropa findById(Long id){
		return new Guardarropa("aca un guardarropa");
	}
}
