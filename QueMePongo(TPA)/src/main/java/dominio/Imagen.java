package dominio;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Imagen {

    int anchoDeDestino;
    int largoDeDestino;

    public void cargarImagen(String origen, String destino) throws IOException {
        guardarImagen(normalizar(archivoImagen(origen),anchoDeDestino,largoDeDestino),destino);
    }

    private File archivoImagen(String direccion) {
        return new File(direccion);
    }

    private BufferedImage normalizar(File archivoImagen, int ancho, int largo) throws IOException {

        BufferedImage imagen = ImageIO.read(archivoImagen);

        int anchoAnterior = imagen.getWidth();
        int largoAnterior = imagen.getHeight();

        BufferedImage imagenNormalizada = new BufferedImage(ancho, largo, imagen.getType());

        Graphics2D grafico = imagenNormalizada.createGraphics();

        grafico.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        grafico.drawImage(imagen, 0, 0, ancho, largo, 0, 0, anchoAnterior, largoAnterior, null);
        grafico.dispose();
        return imagenNormalizada;
    }

    private void guardarImagen(BufferedImage imagen, String direccion) throws IOException {
        String format = (direccion.endsWith(".png")) ? "png" : "jpg";
        ImageIO.write(imagen, format, new File(direccion));
    }

    public int getAnchoDeDestino() {
        return anchoDeDestino;
    }

    public void setAnchoDeDestino(int anchoDeDestino) {
        this.anchoDeDestino = anchoDeDestino;
    }

    public int getLargoDeDestino() {
        return largoDeDestino;
    }

    public void setLargoDeDestino(int largoDeDestino) {
        this.largoDeDestino = largoDeDestino;
    }
}
