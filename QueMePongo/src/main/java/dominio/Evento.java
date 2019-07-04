package dominio;

import java.time.LocalDateTime;
import dominio.clima.ProveedorClima;
import dominio.enumerados.ModoDeRepeticion;
import org.uqbar.commons.model.annotations.Observable;

@Observable
public class Evento {
    private String nombre;
    private LocalDateTime fechaYHora;
    private boolean tieneSugerencias;
    private Alertador alertador;

    public Evento(String nombre, LocalDateTime fechaYHora, boolean tieneSugerencias){
        this.nombre = nombre;
        this.fechaYHora = fechaYHora;
        this.tieneSugerencias = tieneSugerencias;
    }
    
    public Evento (String elEvento, ProveedorClima proveedor, LocalDateTime unaFecha, ModoDeRepeticion modo, Usuario usuario, boolean flexible){
        nombre = elEvento;
        fechaYHora = unaFecha;
        alertador = new Alertador(this, proveedor, unaFecha, modo, usuario, flexible);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public boolean isTieneSugerencias() {
        return tieneSugerencias;
    }
}
