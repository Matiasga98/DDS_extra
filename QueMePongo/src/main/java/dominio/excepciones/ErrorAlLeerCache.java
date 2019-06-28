package dominio.excepciones;

public class ErrorAlLeerCache extends RuntimeException {
    public ErrorAlLeerCache(String message, Throwable cause) {
        super(message, cause);
    }
}
