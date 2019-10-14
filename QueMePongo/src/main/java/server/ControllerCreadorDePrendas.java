package server;

import dominio.*;
import dominio.enumerados.Tipo;
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
		String nombre = req.cookie("name");
		String nombrePrenda = req.queryParams("nombrePrenda");
		String tipo = req.queryParams("tipoPrenda");
		String material = req.queryParams("materialPrenda");
		String trama = req.queryParams("tramaPrenda");
		res.cookie("nombrePrenda",nombrePrenda);
		res.cookie("tipoPrenda",tipo);
		res.cookie("materialPrenda",material);
		res.cookie("tramaPrenda",trama);
		System.out.println(nombre);


		res.redirect("/perfil/"+nombre+"/CrearPrenda/2");

		return null;
	}
	public ModelAndView CrearPrendaColor(Request req, Response res){
		Borrador borrador = new Borrador();
		return new ModelAndView(borrador,"CreadorDePrendasColor.hbs");
	}

	public ModelAndView PostPrendaColor(Request req, Response res){
		Borrador borrador = new Borrador();
		String nombre = req.cookie("name");
		String tipoPrenda = req.cookie("tipoPrenda");
		String tramaPrenda = req.cookie("tramaPrenda");
		String materialPrenda = req.cookie("materialPrenda");
		String nombrePrenda = req.cookie("nombrePrenda");
		Color colorPrimario = new Color(Integer.parseInt(req.queryParams("rojo1")),Integer.parseInt(req.queryParams("verde1")),Integer.parseInt(req.queryParams("azul1")));
		//if(req.queryParams("tieneOolorSecundario"))
		Color colorSecundario =  new Color(Integer.parseInt(req.queryParams("rojo2")),Integer.parseInt(req.queryParams("verde2")),Integer.parseInt(req.queryParams("azul2")));

		System.out.println(tipoPrenda+tramaPrenda+materialPrenda+nombrePrenda);



		res.redirect("/perfil/"+nombre+"/CrearPrenda/final");
		return null;
	}

	public ModelAndView PrendaFinal(Request req, Response res){
		Borrador borrador = new Borrador();
		return new ModelAndView(borrador,"CreadorDePrendasColor.hbs");
	}

}
