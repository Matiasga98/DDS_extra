package dominio.Notificadores;

import java.util.Set;
import dominio.Atuendo;

public interface Notificador {
	
	public void notificar(Set<Atuendo> sugerencias);
	
	public void alertar(Set<Atuendo> sugerencias);
	
}
