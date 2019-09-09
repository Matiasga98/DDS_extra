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
	public static Scheduler planificador = new Scheduler();
	
	public static String planificame_porfi (Evento evento, ProveedorClima proveedor, LocalDateTime unaFecha, ModoDeRepeticion modo, Usuario usuario, boolean flexible) {
		String id = "";
		switch (modo) {
			case DIARIO: id = planificador.schedule(unaFecha.getMinute() + " " + (unaFecha.getHour() - 1) + " * * *", new Runnable() {public void run() {Set<Atuendo> sugerencias = usuario.notificarme(evento, proveedor, flexible);}}); break;
			case MENSUAL: id = planificador.schedule(unaFecha.getMinute() + " " + (unaFecha.getHour() - 1) + " " + unaFecha.getDayOfMonth() + " * *", new Runnable() {public void run() {Set<Atuendo> sugerencias = usuario.notificarme(evento, proveedor, flexible);}}); break;
			case ANUAL: id = planificador.schedule(unaFecha.getMinute() + " " + (unaFecha.getHour() - 1) + " " + unaFecha.getDayOfMonth() + " " + unaFecha.getMonthValue() + " *", new Runnable() {public void run() {Set<Atuendo> sugerencias = usuario.notificarme(evento, proveedor, flexible);}}); break;
		}
        planificador.start();
        return id;
	}
	
	public static void destruirJob (String id) {
		planificador.deschedule(id);
	}
}
