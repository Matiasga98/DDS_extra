package server;

import dominio.*;
import dominio.enumerados.Material;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;
import repositorios.RepositorioGuardarropas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Optional;

//import dominio.RepositorioGuardarropas;

public class ControllerLogin {

	public ModelAndView login(Request req, Response res) {
		return new ModelAndView(null, "login.hbs");
	}

	public ModelAndView errorDeLogeo(Request req, Response res) {
		return new ModelAndView(null, "errorDeLogeo.hbs");
	}
	
	public ModelAndView postLogin(Request req, Response res){
		String nombre = req.queryParams("user");
		String contraseniaHasheada = Encriptador.hashear(req.queryParams("pass"));
		Optional<Usuario> usuario = Repositorio.getInstancia().buscarUsuario(nombre);
		if(!usuario.isPresent() || !contraseniaHasheada.equals(usuario.get().getContrasenia())) {
			res.redirect("/errorDeLogeo");
			return null;
		}
		else{
			res.redirect("/perfil/"+nombre);
			return null;
		}
	}

	/*public boolean verificarContrasenia(String contrasenia, Usuario){
		return
	}*/

	public ModelAndView perfil(Request req, Response res){
		String nombre = req.params("nombre");
		Optional<Usuario> usuario = Repositorio.getInstancia().buscarUsuario(nombre);
		return new ModelAndView(usuario.get(), "perfil.hbs");
	}

}
