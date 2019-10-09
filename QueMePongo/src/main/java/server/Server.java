package server;

import dominio.Guardarropa;
//import dominio.RepositorioGuardarropas;
import dominio.Usuario;
import spark.ModelAndView;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Server {
	public static void main(String[] args) {
		//RepositorioGuardarropas.instance().findByUsuario(new Usuario());
		Spark.port(9000);
		Spark.staticFiles.location("/public");
		Spark.init();
		ControllerGuardarropas controller =
				new ControllerGuardarropas();
		
		/*
		 * Tipo de parametros:
		 * - path param (para,etrp de ruta) se escribe como :nombre
		 * - query param va con el simbolo ?nombreParam=valor
		 * - body param va en el POST/PUT/...
		 */

		Spark.get("/guardarropa/prendas",
				controller::prendas, 
				new HandlebarsTemplateEngine());
		
		DebugScreen.enableDebugScreen();
	}

}
