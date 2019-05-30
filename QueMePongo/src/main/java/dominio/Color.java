package dominio;

public class Color {
	int rojo, verde, azul;

	public Color(int rojo, int verde, int azul) {
		this.rojo = rojo;
		this.verde = verde;
		this.azul = azul;
	}

    public boolean esIgualA(Color color) {
        return color != null && this.rojo == color.rojo &&
                this.verde == color.verde &&
                this.azul == color.azul;
	}

}
