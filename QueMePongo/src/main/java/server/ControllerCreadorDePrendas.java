package server;

import dominio.*;
import dominio.enumerados.Material;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;
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
		res.cookie("rojo1",req.queryParams("rojo1"));
		res.cookie("azul1",req.queryParams("azul1"));
		res.cookie("verde1",req.queryParams("verde1"));
		res.cookie("rojo2",req.queryParams("rojo2"));
		res.cookie("azul2",req.queryParams("azuk2"));
		res.cookie("verde2",req.queryParams("verde2"));
		res.cookie("tieneColorSecundario",req.queryParams("tieneOolorSecundario"));

		String nombre = req.cookie("name");



		res.redirect("/perfil/"+nombre+"/CrearPrenda/final");
		return null;
	}

	public ModelAndView PrendaFinal(Request req, Response res){
		String nombre = req.cookie("name");
		Usuario usuario = Repositorio.getInstancia().buscarUsuario(nombre).get();
		return new ModelAndView(usuario,"CreadorDePrendasGuardarropa.hbs");
	}

	public ModelAndView PostPrendaFinal(Request req, Response res){
		Borrador borrador = new Borrador();
		String nombre = req.cookie("name");
		String tipoPrenda = req.cookie("tipoPrenda");
		String tramaPrenda = req.cookie("tramaPrenda");
		String materialPrenda = req.cookie("materialPrenda");
		String nombrePrenda = req.cookie("nombrePrenda");
		Color colorPrimario = new Color(Integer.parseInt(req.cookie("rojo1")),Integer.parseInt(req.cookie("verde1")),Integer.parseInt(req.cookie("azul1")));
		if(!(req.cookie("tieneOolorSecundario")== null)){
			Color colorSecundario =  new Color(Integer.parseInt(req.cookie("rojo2")),Integer.parseInt(req.cookie("verde2")),Integer.parseInt(req.cookie("azul2")));
			borrador.definirColorSecundario(colorSecundario);
		}

		borrador.definirNombre(nombrePrenda);
		borrador.definirTipo(Tipo.valueOf(tipoPrenda));
		borrador.definirMaterial(Material.valueOf(materialPrenda));
		borrador.definirTrama(Trama.valueOf(tramaPrenda));
		borrador.definirColorPrimario(colorPrimario);



		Usuario usuario = Repositorio.getInstancia().buscarUsuario(nombre).get();

		Guardarropa guardarropa = usuario.getGuardarropas().stream().filter(armario -> armario.getNombre().equals(req.queryParams("guardarropas"))).findFirst().get();
		guardarropa.agregarPrendas(borrador.crearPrenda());
		res.redirect("/perfil/"+nombre);
		return null;
	}

}
