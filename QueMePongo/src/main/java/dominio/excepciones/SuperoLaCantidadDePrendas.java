package dominio.excepciones;

public class SuperoLaCantidadDePrendas extends RuntimeException {
    public SuperoLaCantidadDePrendas(String msg) {
        super(msg);
    }
}
