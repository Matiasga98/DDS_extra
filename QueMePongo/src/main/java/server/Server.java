package server;

import dominio.Guardarropa;
//import dominio.RepositorioGuardarropas;
import dominio.Usuario;
import spark.ModelAndView;
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
		Spark.get("/perfil",controllerLogin::perfil,engine);

		Spark.get("/contraseniaMala",controllerLogin::contraseniaMalaGet,engine);
		Spark.post("/contraseniaMala",controllerLogin::postLogin,engine);
		DebugScreen.enableDebugScreen();
	}

}
