package server;

//import dominio.RepositorioGuardarropas;
import java.util.HashSet;

import dominio.Repositorio;
import spark.Spark;
import spark.TemplateEngine;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

import javax.persistence.EntityManager;

import dominio.JPAUtility;
import dominio.Usuario;

public class Server {
	public static void main(String[] args) {
		EntityManager entityManager = JPAUtility.getEntityManager();

		Repositorio unRepo = Repositorio.getInstancia();
		unRepo.getInstancia().setUsuarios(entityManager.createQuery("SELECT U FROM Usuario U").getResultList());

		Spark.port(getHerokuAssignedPort());
		Spark.staticFiles.location("/public");
		Spark.init();

		ControllerGuardarropas controllerGuardarropas =
				new ControllerGuardarropas();
		ControllerLogin controllerLogin = new ControllerLogin();
		ControllerRedireccion controllerRedireccion = new ControllerRedireccion();
		ControllerCreadorDePrendas controllerCreadorDePrendas= new ControllerCreadorDePrendas();
		ControllerSugerencia controllerSugerencia = new ControllerSugerencia();
		ControllerCreadorDeEventos controllerCreadorDeEventos = new ControllerCreadorDeEventos();
		ControllerMostrarEventos controllerMostrarEventos = new ControllerMostrarEventos();
		ControllerCalificarSugerencia controllerCalificarSugerencia = new ControllerCalificarSugerencia();
		/*
		 * Tipo de parametros:
		 * - path param (para,etrp de ruta) se escribe como :nombre
		 * - query param va con el simbolo ?nombreParam=valor
		 * - body param va en el POST/PUT/...
		 */
		TemplateEngine engine = new HandlebarsTemplateEngine();
		Spark.get("/guardarropa/prendas",controllerGuardarropas::prendas, new HandlebarsTemplateEngine());
		Spark.get("/login",controllerLogin::login,engine);
		Spark.post("/login",controllerLogin::postLogin);
		Spark.get("/registrarse",controllerLogin::registrarse,engine);
		Spark.get("/perfil",controllerLogin::perfil,engine);

		Spark.post("/perfil",controllerRedireccion::PostPerfil,engine);
		Spark.get("/CrearPrenda",controllerCreadorDePrendas::CrearPrenda,engine);

		Spark.post("/CrearPrenda",controllerCreadorDePrendas::PostCrearPrenda,engine);
		Spark.get("CrearPrenda/Color",controllerCreadorDePrendas::CrearPrendaColor,engine);

		Spark.post("/CrearPrenda/Color",controllerCreadorDePrendas::PostPrendaColor,engine);
		Spark.get("/CrearPrenda/final",controllerCreadorDePrendas::PrendaFinal,engine);

		Spark.post("/CrearPrenda/final",controllerCreadorDePrendas::PostPrendaFinal,engine);

		Spark.get("/Sugerir",controllerSugerencia::EleccionEvento,engine);
		Spark.post("/Sugerir",controllerSugerencia::PostEleccionEvento,engine);

		Spark.get("/Sugerencias",controllerSugerencia::SugerenciasAlEvento,engine);
		Spark.post("/Sugerencias",controllerSugerencia::PostSugerenciasAlEvento,engine);

		Spark.get("/Sugerencia",controllerSugerencia::SugerenciaElegida,engine);

		Spark.get("/evento",controllerCreadorDeEventos::CrearEvento,engine);
		Spark.post("/evento",controllerCreadorDeEventos::PostCrearEvento);

		Spark.get("/eventos",controllerMostrarEventos::listarEventos,engine);
		Spark.post("/eventos",controllerMostrarEventos::postEventos,engine);

		Spark.get("/AtuendoPorCalificar",controllerCalificarSugerencia::CalificarSugerencias,engine);

		Spark.get("/Calificacion",controllerCalificarSugerencia::CalificarSugerenciasPuntaje,engine);
		Spark.post("/Calificacion",controllerCalificarSugerencia::PostCalificarSugerenciasPuntaje,engine);


		DebugScreen.enableDebugScreen();


	}

	static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		}

		return 9002; //return default port if heroku-port isn't set (i.e. on localhost)
	}


}
