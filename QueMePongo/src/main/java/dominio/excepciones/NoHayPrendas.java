package dominio.excepciones;

public class NoHayPrendas extends RuntimeException {
	public NoHayPrendas(String msg){
		super(msg);
	}
}
