package server;

import dominio.*;
import dominio.clima.AccuweatherData.AccuWeather;
import dominio.clima.ProveedorClima;
import dominio.enumerados.Material;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Set;

//import dominio.RepositorioGuardarropas;

public class ControllerSugerencia {




	public ModelAndView EleccionEvento(Request req, Response res){
		String nombre = req.cookie("name");
		Usuario usuario = Repositorio.getInstancia().buscarUsuario(nombre).get();

		return new ModelAndView(usuario,"SugerirAtuendoEvento.hbs");
	}
	public ModelAndView PostEleccionEvento(Request req, Response res){
		String nombre = req.cookie("name");
		res.cookie("evento", req.queryParams("Evento"));

		res.redirect("/perfil/"+nombre+"/PedirSugerencia/2");

		return null;
	}

	public ModelAndView SugerenciasAlEvento(Request req, Response res){
		String nombre = req.cookie("name");
		String nombreEvento = req.queryParams("evento");
		Usuario usuario = Repositorio.getInstancia().buscarUsuario(nombre).get();
		Evento evento = usuario.getEventos().stream().filter(unEvento -> unEvento.getNombre().equals(nombreEvento)).findFirst().get();
		ProveedorClima mock = new AccuWeather();
		Set<Atuendo> sugerencias = usuario.pedirSugerenciaParaEventoDeTodosLosGuadaropas(evento,mock,false);

		return new ModelAndView(sugerencias,"CreadorDePrendasColor.hbs");

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
