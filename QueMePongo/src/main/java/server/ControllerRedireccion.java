package server;

import dominio.*;
import dominio.enumerados.Material;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

//import dominio.RepositorioGuardarropas;

public class ControllerRedireccion {

	public ModelAndView PostPerfil(Request req, Response res){
		/*System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
		String id = req.params("id");
		System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEE");
		switch(req.queryParams("botoncito")){
			case("Crear una prenda"):{
				System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIII");
				res.redirect("/CrearPrenda");
			}
			case("Pedir sugerencia"):{
				res.redirect("/Sugerir");
			}
			case("Crear Evento"):{
				res.redirect("/evento");
			}
			case("Mostrar Eventos"):{
				res.redirect("/eventos");
			}
			case("perfil"):{
				res.redirect("/perfil");
			}
			case("Calificar sugerencia"):{
				res.redirect("/CalificarSugerencia");
			}
		}*/
		req.session().attribute("nombreGuardarropa", req.queryParams("botoncito"));

		res.redirect("/CrearPrenda");

		return null;
	}


}
