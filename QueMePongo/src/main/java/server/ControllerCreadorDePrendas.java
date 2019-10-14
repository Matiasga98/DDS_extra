package server;

import dominio.Borrador;
import dominio.Encriptador;
import dominio.Repositorio;
import dominio.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Optional;

//import dominio.RepositorioGuardarropas;

public class ControllerCreadorDePrendas {


	public ModelAndView IrACrearPrenda(Request req, Response res){
		String nombre = req.params("nombre");
		res.redirect("/perfil/"+nombre+"/CrearPrenda");
		return null;
	}
	public ModelAndView CrearPrenda(Request req, Response res){
		String nombre = req.params("nombre");
		Borrador borrador = new Borrador();
		System.out.println(nombre);

		return new ModelAndView(borrador,"CreadorDePrendas.hbs");
	}
	public ModelAndView PostCrearPrenda(Request req, Response res){
		String nombre = req.params("nombre");
		System.out.println(nombre);

		res.redirect("/perfil/"+nombre+"/CrearPrenda/2");

		return null;
	}
	public ModelAndView CrearPrendaColor(Request req, Response res){
		Borrador borrador = new Borrador();
		return new ModelAndView(borrador,"CreadorDePrendasColor.hbs");
	}



}
