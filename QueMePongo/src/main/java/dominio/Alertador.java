package dominio;

import it.sauronsoftware.cron4j.Scheduler;

public class Alertador {

	public static Scheduler planificador = new Scheduler();
	
	public static void sugerirUsuarios () {
		Repositorio.getInstancia().avisarUsuarios();
	}
	
	public static void planificame_porfi () {
		planificador.schedule("* * * * *", new Runnable() {public void run() {sugerirUsuarios();}});
		planificador.start();
	}

}
