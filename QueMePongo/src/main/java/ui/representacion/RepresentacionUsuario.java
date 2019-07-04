package ui.representacion;

import dominio.Usuario;
import org.uqbar.arena.bindings.PropertyAdapter;

public class RepresentacionUsuario extends PropertyAdapter {
    public RepresentacionUsuario(String propiedad) {
        super(Usuario.class, propiedad);
    }
}
