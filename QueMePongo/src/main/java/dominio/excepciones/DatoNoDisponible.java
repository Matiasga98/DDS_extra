package dominio.excepciones;

public class DatoNoDisponible extends RuntimeException {
    public DatoNoDisponible(String message) {
        super(message);
    }
}
