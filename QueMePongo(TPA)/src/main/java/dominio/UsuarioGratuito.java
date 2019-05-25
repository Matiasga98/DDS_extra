package dominio;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioGratuito extends Usuario {     //Usamos herencia para no volver a escribir todos los metodos

    public int prendasMaximas = 20;

    public UsuarioGratuito() {
        this.guardarropas = new HashSet<>();
    }

    @Override
    public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
        if (prendasMaximas == guardarropa.cantidadPrendas()) {
            throw new RuntimeException("no te queda mas lugar pipi");
        }
        guardarropa.agregarPrendas(prenda);
    }
}


