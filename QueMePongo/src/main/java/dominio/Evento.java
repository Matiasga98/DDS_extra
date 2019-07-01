package dominio;

import java.time.LocalDateTime;
import dominio.clima.ProveedorClima;
import dominio.enumerados.ModoDeRepeticion;

public class Evento {
    private String tipo;
    private LocalDateTime fecha;
    private Alertador alertador;
    
    public Evento (String elEvento, ProveedorClima proveedor, LocalDateTime unaFecha, ModoDeRepeticion modo, Usuario usuario, boolean flexible){
        tipo = elEvento;
        fecha = unaFecha;
        alertador = new Alertador(this, proveedor, unaFecha, modo, usuario, flexible);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    

}
