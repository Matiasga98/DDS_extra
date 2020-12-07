package server;

import com.sun.org.apache.xpath.internal.operations.Bool;
import dominio.*;
import dominio.clima.AccuweatherData.AccuWeather;
import dominio.clima.ProveedorClima;
import dominio.clima.ProveedorMock;
import dominio.enumerados.Material;
import dominio.enumerados.ModoDeRepeticion;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static spark.Spark.halt;

//import dominio.RepositorioGuardarropas;

public class ControllerSugerencia {
	String nombreDeEvento;
	int atuendoElegidoId;

	public ModelAndView EleccionEvento(Request req, Response res){
		Map<String, Object> model = new HashMap<>();

		String id = req.cookie("id");
		Usuario usuario = Repositorio.getInstancia().buscarUsuarioPorId(id).get();

		model.put("usuario",usuario);

		Boolean error = req.session().attribute("error");
		error = error==null? false:error;

		if (error){
			model.put("error",true);
			model.put("mensaje",req.session().attribute("mensaje"));
			req.session().attribute("error",false);
		}

		return new ModelAndView(model,"SugerirAtuendoEvento.hbs");
	}
	public ModelAndView PostEleccionEvento(Request req, Response res){

		Integer id = req.session().attribute("id");
		if (id == null) res.redirect("/login");


		if(req.queryParams("botoncito2")== null){
			nombreDeEvento=req.queryParams("evento");
		}
		else{
			req.session().attribute("nombreEvento", req.queryParams("botoncito2"));
			nombreDeEvento = req.session().attribute("nombreEvento").toString();
		}
		if (nombreDeEvento.equals("Evento")){
			req.session().attribute("error",true);
			req.session().attribute("mensaje","Seleccione un Evento");
			res.redirect("/Sugerir");
		}

		Usuario usuario = Repositorio.getInstancia().buscarUsuarioPorId(id);
		Evento evento = usuario.getEventos().stream().filter(unEvento -> unEvento.getNombre().equals(nombreDeEvento)).findFirst().get();

		ProveedorClima prov = new ProveedorMock();

		try{
			Set<Atuendo> sugerencias = usuario.pedirSugerenciaParaEventoDeTodosLosGuadaropas(evento,prov,evento.getFlexible());

			// Hay que hacer esto por inyeccion de dependencias para no pegarle a la BD directo en el controller
			EntityManager entityManager = JPAUtility.getEntityManager();
			entityManager.getTransaction().begin();

			sugerencias.stream().forEach(atuendo->entityManager.persist(atuendo));

			entityManager.merge(usuario);
			entityManager.getTransaction().commit();

			req.session().attribute("error",false);
			res.redirect("/Sugerencias");
		}
		catch (RuntimeException ex){

			req.session().attribute("error",true);
			req.session().attribute("mensaje",ex.getMessage());
			res.redirect("/Sugerir");

		}

		return null;
	}

	public ModelAndView SugerenciasAlEvento(Request req, Response res){
		Map<String, Object> model = new HashMap<>();

		Integer id = req.session().attribute("id");
		if (id == null) res.redirect("/login");

		Usuario usuario = Repositorio.getInstancia().buscarUsuarioPorId(id);
		model.put("usuario", usuario);

		return new ModelAndView(model,"SugerirAtuendoListado.hbs");

	}
	public ModelAndView PostSugerenciasAlEvento(Request req, Response res){
		String id = req.cookie("id");
		Usuario usuario = Repositorio.getInstancia().buscarUsuarioPorId(id).get();
		String atuendoId = req.queryParams("botoncito");

		atuendoElegidoId = Integer.parseInt(atuendoId);
		Atuendo atuendoAceptado = usuario.getLoQueMeSugirieron().stream().filter(atuendo -> Long.toString(atuendo.getId()).equals(atuendoId)).findFirst().get();
		usuario.aceptarSugerencia(atuendoAceptado);
		Set<Atuendo> atuendosRechazados = usuario.getLoQueMeSugirieron().stream().filter(atuendo -> !Long.toString(atuendo.getId()).equals(atuendoId)).collect(Collectors.toSet());
		atuendosRechazados.stream().forEach(atuendo -> usuario.rechazarSugerencia(atuendo));
		usuario.setUltimoAceptado(atuendoAceptado);
		res.redirect("/Sugerencia");
		return null;
	}


	public ModelAndView SugerenciaElegida(Request req, Response response) {
		String id = req.cookie("id");
		Usuario usuario = Repositorio.getInstancia().buscarUsuarioPorId(id).get();

		Atuendo atuendoAceptado = usuario.getLoQueMeSugirieron().stream().filter(atuendo ->atuendo.getId()==atuendoElegidoId).findFirst().get();
		return new ModelAndView(atuendoAceptado,"SugerirAtuendoElegido.hbs");
	}
}
