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
        if (this.modoRepetitivo.equals(ModoDeRepeticion.MENSUAL))
        	planificador.schedule(unaFecha.getMinute() + " " + (unaFecha.getHour() - 1) + " " + unaFecha.getDayOfMonth() + " " + "* *", new Runnable() {public void run() {System.out.println(usuario.pedirSugerenciaParaEventoDeTodosLosGuadaropas(evento, proveedor, flexible));}});
        else if (this.modoRepetitivo.equals(ModoDeRepeticion.ANUAL))
        	planificador.schedule(unaFecha.getMinute() + " " + (unaFecha.getHour() - 1) + " " + unaFecha.getDayOfMonth() + " " + unaFecha.getMonthValue() + " *", new Runnable() {public void run() {usuario.pedirSugerenciaParaEventoDeTodosLosGuadaropas(evento, proveedor, flexible);}});
        else
        	planificador.schedule(unaFecha.getMinute() + " " + (unaFecha.getHour() - 1) + " " + unaFecha.getDayOfMonth() + " " + unaFecha.getMonthValue() + " *", new Runnable() {public void run() {System.out.println(usuario.pedirSugerenciaParaEventoDeTodosLosGuadaropas(evento, proveedor, flexible));}});
        planificador.start();
	}
}
