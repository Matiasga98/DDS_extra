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
		String nombreEvento = req.queryParams("evento");

		res.cookie("nombreEvento",nombreEvento);

		System.out.println(nombreEvento);
		res.redirect("/perfil/"+nombre+"/PedirSugerencia/2");

		return null;
	}

	public ModelAndView SugerenciasAlEvento(Request req, Response res){

		String nombre = req.cookie("name");


		System.out.println( req.cookie("nombreEvento"));

		Usuario usuario = Repositorio.getInstancia().buscarUsuario(nombre).get();

		//Evento evento = usuario.getEventos().stream().filter(unEvento -> unEvento.getNombre().equals(nombreEvento)).findFirst().get();

		ProveedorClima mock = new AccuWeather();

		//Set<Atuendo> sugerencias = usuario.pedirSugerenciaParaEventoDeTodosLosGuadaropas(evento,mock,false);


		return new ModelAndView(usuario,"SugerirAtuendoListado.hbs");

	}



}
