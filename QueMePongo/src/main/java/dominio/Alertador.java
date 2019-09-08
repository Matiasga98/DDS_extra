package dominio;

import java.util.Set;
import java.time.LocalDateTime;
import dominio.clima.ProveedorClima;
import dominio.enumerados.ModoDeRepeticion;
import it.sauronsoftware.cron4j.Scheduler;
import javax.persistence.*;

@Entity
public class Alertador {

	@Id
	@GeneratedValue
	@Column(name="id_alertador")
	private long id;

	@Transient
	public Scheduler planificador = new Scheduler();

	@Enumerated
	private ModoDeRepeticion modoRepetitivo;

	@OneToOne
	private Usuario usuario;

	@OneToOne
	private Evento evento;
	
	public Alertador (Evento evento, ProveedorClima proveedor, LocalDateTime unaFecha, ModoDeRepeticion modo, Usuario usuario, boolean flexible) {
		this.usuario = usuario;
		this.evento = evento;
		this.modoRepetitivo = modo;
		switch (modo) {
			case DIARIO: planificador.schedule(unaFecha.getMinute() + " " + (unaFecha.getHour() - 1) + " * * *", new Runnable() {public void run() {Set<Atuendo> sugerencias = proveedor.temperatura(unaFecha) > 10? usuario.notificarme(evento, proveedor, flexible) : usuario.alertarme(evento, proveedor, flexible);}}); break;
			case MENSUAL: planificador.schedule(unaFecha.getMinute() + " " + (unaFecha.getHour() - 1) + " " + unaFecha.getDayOfMonth() + " * *", new Runnable() {public void run() {Set<Atuendo> sugerencias = proveedor.temperatura(unaFecha) > 10? usuario.notificarme(evento, proveedor, flexible) : usuario.alertarme(evento, proveedor, flexible);}}); break;
			case ANUAL: planificador.schedule(unaFecha.getMinute() + " " + (unaFecha.getHour() - 1) + " " + unaFecha.getDayOfMonth() + " " + unaFecha.getMonthValue() + " *", new Runnable() {public void run() {Set<Atuendo> sugerencias = proveedor.temperatura(unaFecha) > 10? usuario.notificarme(evento, proveedor, flexible) : usuario.alertarme(evento, proveedor, flexible);}}); break;
		}
        planificador.start();
	}
}
