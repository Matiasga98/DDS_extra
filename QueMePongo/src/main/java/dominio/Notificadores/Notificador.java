package dominio.Notificadores;

import java.util.Set;
import dominio.Atuendo;
import dominio.Evento;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(name = "tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Notificador {

	@Id
	@GeneratedValue
	private long id;

	public void notificar(Evento evento, Set<Atuendo> sugerencias) {
	}

}
