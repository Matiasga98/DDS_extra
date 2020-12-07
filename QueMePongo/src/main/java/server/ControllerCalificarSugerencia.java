package server;

import dominio.*;
import dominio.clima.AccuweatherData.AccuWeather;
import dominio.clima.ProveedorClima;
import dominio.enumerados.Categoria;
import dominio.enumerados.EstadoAtuendo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

//import dominio.RepositorioGuardarropas;

public class ControllerCalificarSugerencia {
	String nombreDeEvento;
	EntityManager entityManager = JPAUtility.getEntityManager();
	
	public ModelAndView CalificarSugerencias(Request req, Response res){
		String id = req.cookie("id");
		Usuario usuario = Repositorio.getInstancia().buscarUsuarioPorId(id).get();
		Atuendo atuendo = usuario.getUltimoAceptado();
		return new ModelAndView(atuendo,"CalificarSugerenciasElegir.hbs");
	}



	public ModelAndView CalificarSugerenciasPuntaje (Request req, Response res){
		String id = req.cookie("id");
		Usuario usuario = Repositorio.getInstancia().buscarUsuarioPorId(id).get();
		Atuendo atuendoElegido = usuario.getUltimoAceptado();
		Borrador holis = new Borrador();
		List<Prenda> prendasEnAtuendo = atuendoElegido.getPrendas();
		List<Categoria> categoriasEnElegido = prendasEnAtuendo.stream().map(prenda -> prenda.categoria()).collect(Collectors.toList());
		holis.categoria.retainAll(categoriasEnElegido);
		return new ModelAndView(holis,"CalificarSugerenciasElegirPuntaje.hbs");
	}
	public ModelAndView PostCalificarSugerenciasPuntaje (Request req, Response res){
		String id = req.cookie("id");
		Usuario usuario = Repositorio.getInstancia().buscarUsuarioPorId(id).get();
		Atuendo atuendoElegido = usuario.getUltimoAceptado();
		List<Prenda> prendasEnAtuendo = atuendoElegido.getPrendas();
		Set<Categoria> categoriasEnElegido = prendasEnAtuendo.stream().map(prenda -> prenda.categoria()).collect(Collectors.toSet());

		Calificacion calificacion = new Calificacion();
		calificacion.friolentoEn = categoriasEnElegido.stream().filter(categoria -> req.queryParams(categoria.name()).equals("Frio")).collect(Collectors.toList());
		calificacion.calurosoEn = categoriasEnElegido.stream().filter(categoria -> req.queryParams(categoria.name()).equals("Calor")).collect(Collectors.toList());
		calificacion.friolentoEn.forEach(categoria -> System.out.println(categoria.name()));
		calificacion.calurosoEn.forEach(categoria -> System.out.println(categoria.name()));

		usuario.procesarCalificacion(calificacion);

		EntityManager entityManager = JPAUtility.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(usuario);
		entityManager.getTransaction().commit();

		res.redirect("/perfil");
		return null;
	}



}
