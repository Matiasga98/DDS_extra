package dominio.excepciones;

public class NoSeEncuentraLaFecha extends RuntimeException {
    public NoSeEncuentraLaFecha(String msg){
        super(msg);
    }
}

