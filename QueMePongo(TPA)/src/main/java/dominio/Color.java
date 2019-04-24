package dominio;

public class Color {
	int rojo, verde, azul;

	public Color(int rojo, int verde, int azul) {
		this.rojo = rojo;
		this.verde = verde;
		this.azul = azul;
	}

	public boolean esIgualA(Color color2) {
		if(!(color2 == null)) {
			return (this.rojo == color2.rojo && 
					this.verde == color2.verde && 
					this.azul == color2.azul);
		}
		return false;
	}

}
