package dominio.excepciones;

public class ErrorAlConsumirAPI extends RuntimeException {
    public ErrorAlConsumirAPI(String mensaje, Throwable exception) {
        super(mensaje,exception);
    }
}
