package dominio;

import java.util.Collection;
import java.util.HashSet;
import javax.persistence.*;

public class Repositorio {

    private Collection<Usuario> usuarios;

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

    public void avisarUsuarios () {
    	this.usuarios.forEach(usuario -> usuario.serAvisado());
    }
    
}
