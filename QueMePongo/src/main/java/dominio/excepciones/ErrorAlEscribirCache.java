package dominio.excepciones;

public class ErrorAlEscribirCache extends RuntimeException{
    public ErrorAlEscribirCache(String message, Throwable cause) {
        super(message, cause);
    }
}
