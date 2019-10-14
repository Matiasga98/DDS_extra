package server;

//import dominio.RepositorioGuardarropas;
import spark.Spark;
import spark.TemplateEngine;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Server {
	public static void main(String[] args) {
		//RepositorioGuardarropas.instance().findByUsuario(new Usuario());
		Spark.port(9000);
		Spark.staticFiles.location("/public");
		Spark.init();
		ControllerGuardarropas controllerGuardarropas =
				new ControllerGuardarropas();
		ControllerLogin controllerLogin = new ControllerLogin();
		ControllerRedireccion controllerRedireccion = new ControllerRedireccion();
		ControllerCreadorDePrendas controllerCreadorDePrendas= new ControllerCreadorDePrendas();
		ControllerSugerencia controllerSugerencia = new ControllerSugerencia();
		/*
		 * Tipo de parametros:
		 * - path param (para,etrp de ruta) se escribe como :nombre
		 * - query param va con el simbolo ?nombreParam=valor
		 * - body param va en el POST/PUT/...
		 */
		TemplateEngine engine = new HandlebarsTemplateEngine();
		Spark.get("/guardarropa/prendas",
				controllerGuardarropas::prendas,
				new HandlebarsTemplateEngine());
		Spark.get("/login",controllerLogin::login,engine);
		Spark.post("/login",controllerLogin::postLogin,engine);
		Spark.get("/perfil/:nombre",controllerLogin::perfil,engine);

		Spark.get("/errorDeLogeo",controllerLogin::errorDeLogeo,engine);
		Spark.post("/errorDeLogeo",controllerLogin::postLogin,engine);

		Spark.post("/perfil/:nombre",controllerRedireccion::PostPerfil,engine);
		Spark.get("/perfil/:nombre/CrearPrenda",controllerCreadorDePrendas::CrearPrenda,engine);

		Spark.post("/perfil/:nombre/CrearPrenda",controllerCreadorDePrendas::PostCrearPrenda,engine);
		Spark.get("/perfil/:nombre/CrearPrenda/2",controllerCreadorDePrendas::CrearPrendaColor,engine);

		Spark.post("/perfil/:nombre/CrearPrenda/2",controllerCreadorDePrendas::PostPrendaColor,engine);
		Spark.get("/perfil/:nombre/CrearPrenda/final",controllerCreadorDePrendas::PrendaFinal,engine);

		Spark.post("/perfil/:nombre/CrearPrenda/final",controllerCreadorDePrendas::PostPrendaFinal,engine);

		Spark.get("/perfil/:nombre/PedirSugerencia",controllerSugerencia::EleccionEvento,engine);
		Spark.post("/perfil/:nombre/PedirSugerencia",controllerSugerencia::PostEleccionEvento,engine);

		Spark.get("/perfil/:nombre/PedirSugerencia/2",controllerSugerencia::SugerenciasAlEvento,engine);

		DebugScreen.enableDebugScreen();


	}

}
