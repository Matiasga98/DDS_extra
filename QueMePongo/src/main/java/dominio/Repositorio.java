package dominio;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.*;

@Entity
public class Repositorio {

	@Id
	@GeneratedValue
	@Column (name = "id")
	private long id;
	
	@OneToMany
	@JoinColumn (name = "repo_id")
    private Collection<Usuario> usuarios;

	@Transient
    private static Repositorio instancia = new Repositorio();

    private Repositorio(){
        usuarios = new HashSet<>();
    }

    public static Repositorio getInstancia(){
        return instancia;
    }

    public Collection<Usuario> usuarios(){
        return usuarios;
    }

    public void nuevoUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public void eliminar(Usuario usuario){
        usuarios.remove(usuario);
    }

}
