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

		ControllerCreadorDePrendas controllerCreadorDePrendas= new ControllerCreadorDePrendas();
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

		Spark.post("/perfil/:nombre",controllerCreadorDePrendas::IrACrearPrenda,engine);
		Spark.get("/perfil/:nombre/CrearPrenda",controllerCreadorDePrendas::CrearPrenda,engine);
		Spark.post("/perfil/:nombre/CrearPrenda",controllerCreadorDePrendas::PostCrearPrenda,engine);
		Spark.get("/perfil/:nombre/CrearPrenda/2",controllerCreadorDePrendas::CrearPrendaColor,engine);
		DebugScreen.enableDebugScreen();


	}

}
