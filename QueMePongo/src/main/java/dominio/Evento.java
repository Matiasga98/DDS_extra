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
    
    @Column(name = "hay_alertas_meteorologicas")
    public boolean tieneAlertasMeteorologicas;

    @Column(name = "proveedor")
	private ProveedorClima proveedor;

    @Column(name = "flexible")
	private boolean flexible;
    
    public Evento (String elEvento, ProveedorClima proveedor, LocalDateTime unaFecha, boolean tieneSugerencias, ModoDeRepeticion modo, Usuario usuario, boolean flexible) {
        this.nombre = elEvento;
        this.fechaYHora = unaFecha;
        this.tieneSugerencias = tieneSugerencias;
        this.proveedor = proveedor;
        this.flexible = flexible;
        this.tieneAlertasMeteorologicas = false;
    }

    public Evento(){

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

	public ProveedorClima getProveedor() {
		return proveedor;
	}
	
	public boolean getFlexible() {
		return flexible;
	}
	
}
