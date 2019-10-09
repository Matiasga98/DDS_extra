package server;

import dominio.Color;
import dominio.Guardarropa;
import dominio.Prenda;
import repositorios.RepositorioGuardarropas;
//import dominio.RepositorioGuardarropas;
import dominio.Usuario;
import dominio.enumerados.Material;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerGuardarropas {

	public ModelAndView prendas(Request req, Response res) {
		Guardarropa guardarropas = new Guardarropa("pepito el mago");
		
		guardarropas.agregarPrendas(new Prenda("carlitos bala", Tipo.ANILLO, Material.ORO, Trama.CUADROS, new Color(1, 1, 1), null));
				//RepositorioGuardarropas.instance()
				//.findByUsuario(new Usuario());
		
		return new ModelAndView(guardarropas, "guardarropas.hbs");
	}
	
	public ModelAndView guardarropa(Request req, Response res){
		Long id = Long.parseLong(req.params("id"));
		Guardarropa guardarropa = RepositorioGuardarropas.instance().findById(id);
		return new ModelAndView(guardarropa,"guardarropaPorId.hbs");
	}

}
