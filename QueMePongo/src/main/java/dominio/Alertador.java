package dominio;

import java.time.LocalDateTime;
import dominio.clima.ProveedorClima;
import dominio.enumerados.ModoDeRepeticion;
import it.sauronsoftware.cron4j.Scheduler;

public class Alertador {
	Scheduler planificador = new Scheduler();
	ModoDeRepeticion modoRepetitivo;
	Usuario usuario;
	Evento evento;
	
	public Alertador (Evento evento, ProveedorClima proveedor, LocalDateTime unaFecha, ModoDeRepeticion modo, Usuario usuario, boolean flexible) {
		this.usuario = usuario;
		this.evento = evento;
		this.modoRepetitivo = modo;
		switch (modo) {
			case DIARIO: planificador.schedule(unaFecha.getMinute() + " " + (unaFecha.getHour() - 1) + " * * *", new Runnable() {public void run() {usuario.notificarme(evento, proveedor, flexible);}}); break;
			case MENSUAL: planificador.schedule(unaFecha.getMinute() + " " + (unaFecha.getHour() - 1) + " " + unaFecha.getDayOfMonth() + " * *", new Runnable() {public void run() {usuario.notificarme(evento, proveedor, flexible);}}); break;
			case ANUAL: planificador.schedule(unaFecha.getMinute() + " " + (unaFecha.getHour() - 1) + " " + unaFecha.getDayOfMonth() + " " + unaFecha.getMonthValue() + " *", new Runnable() {public void run() {usuario.notificarme(evento, proveedor, flexible);}}); break;
		}
        planificador.start();
	}
}
