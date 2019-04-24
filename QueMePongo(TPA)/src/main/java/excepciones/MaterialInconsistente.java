package excepciones;

public class MaterialInconsistente extends RuntimeException {
	public MaterialInconsistente(String msg){
		super(msg);
	}
}