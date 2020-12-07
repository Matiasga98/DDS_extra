package server;

import dominio.*;
import dominio.enumerados.Material;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;
import repositorios.RepositorioGuardarropas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//import dominio.RepositorioGuardarropas;

public class ControllerLogin {

	public ModelAndView login(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();
		Boolean login_error = req.session().attribute("login_error");
		login_error = login_error==null? false:login_error;
		model.put("login_error",login_error);
		return new ModelAndView(model, "login.hbs");

	}

	public ModelAndView registrarse(Request req, Response res) {
		return new ModelAndView(null, "registrarse.hbs");
	}
	
	public Void postLogin(Request req, Response res){

		String nombre = req.queryParams("user");
		String contraseniaHasheada = Encriptador.hashear(req.queryParams("pass"));

		Optional<Usuario> usuario = Repositorio.getInstancia().buscarUsuario(nombre);
		if(!usuario.isPresent() || !contraseniaHasheada.equals(usuario.get().getContrasenia())) {
			req.session().attribute("login_error", true);
			res.redirect("/login");
			return null;
		}else{
			req.session().attribute("login_error", false);
			Integer id = usuario.get().getId();
			String string_id = id.toString();
			res.cookie("name",nombre);
			res.cookie("id",string_id);
			req.session().attribute("id",id);
			res.redirect("/perfil");
			return null;
		}
	}


	public ModelAndView perfil(Request req, Response res){
		Integer id = Integer.parseInt(req.cookie("id"));
		Usuario usuario = Repositorio.getInstancia().buscarUsuarioPorId(id);
		return new ModelAndView(usuario, "perfil.hbs");
	}

}
