package dominio;

import java.time.LocalDateTime;
import dominio.clima.ProveedorClima;
import dominio.enumerados.ModoDeRepeticion;
import org.uqbar.commons.model.annotations.Observable;
import javax.persistence.*;

@Entity
@Observable
public class Evento {

    @Id
    @GeneratedValue
    @Column(name="id_evento")
    private long id;

    @Column(name="nombre")
    private String nombre;

    @Column(name="fecha_hora")
    private LocalDateTime fechaYHora;

    @Column(name="tiene_sugerencias")
    private boolean tieneSugerencias;
    
    @Column(name="id_del_job")
    private String idDelJob;
    
    @Column(name = "hay_alertas_meteorologicas")
    public boolean tieneAlertasMeteorologicas;
    
    public Evento (String elEvento, ProveedorClima proveedor, LocalDateTime unaFecha, boolean tieneSugerencias, ModoDeRepeticion modo, Usuario usuario, boolean flexible){
        this.nombre = elEvento;
        this.fechaYHora = unaFecha;
        this.tieneSugerencias = tieneSugerencias;
        this.tieneAlertasMeteorologicas = false;
        this.idDelJob = Alertador.planificame_porfi(this, proveedor, unaFecha, modo, usuario, flexible);
    }

    public void destruirEvento () {
    	Alertador.destruirJob(this.getIdDelJob());
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
    
    public String getIdDelJob() {
    	return idDelJob;
    }
}
