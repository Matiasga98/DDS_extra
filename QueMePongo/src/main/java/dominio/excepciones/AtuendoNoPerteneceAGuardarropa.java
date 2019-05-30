package dominio.excepciones;

public class AtuendoNoPerteneceAGuardarropa extends RuntimeException{
    public AtuendoNoPerteneceAGuardarropa(String msg) {
        super(msg);
    }
}
