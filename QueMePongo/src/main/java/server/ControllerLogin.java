package server;

import dominio.Color;
import dominio.Guardarropa;
import dominio.Prenda;
import dominio.Usuario;
import dominio.enumerados.Material;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;
import repositorios.RepositorioGuardarropas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

//import dominio.RepositorioGuardarropas;

public class ControllerLogin {

	public ModelAndView login(Request req, Response res) {
		return new ModelAndView(null, "login.hbs");
	}

	public ModelAndView contraseniaMalaGet(Request req, Response res) {
		return new ModelAndView(null, "contraseniaMala.hbs");
	}
	
	public ModelAndView postLogin(Request req, Response res){
		String nombre = req.queryParams("user");
		String contrasenia = req.queryParams("pass");
		if(contrasenia.equals("Hola")) {
			res.redirect("/perfil");
			return null;
		}
		else{
			res.redirect("/contraseniaMala");
			return null;
		}
	}

	/*public boolean verificarContrasenia(String contrasenia, Usuario){
		return
	}*/

	public ModelAndView perfil(Request req, Response res){
		Color negro;
		Color rojo;
		Color blanco;
		Color azul;
		Color verde;
		Color amarillo;
		negro = new Color(0, 0, 0);
		rojo = new Color(255, 0, 0);
		blanco = new Color(255, 255, 255);
		azul = new Color(0, 0, 255);
		verde = new Color(0, 100, 0);
		amarillo = new Color(255, 233, 0);
		Usuario usuario = new Usuario(false);
		usuario.setNombre("fran");
		Prenda remerita = new Prenda("remerita", Tipo.REMERA, Material.ALGODON, Trama.LISA, negro, null);
		Prenda pantaloncito =new Prenda("pantaloncito", Tipo.PANTALON, Material.JEAN, Trama.LISA, rojo, null);
		Prenda inviernito =new Prenda("inviernito", Tipo.PANTALONINVIERNO, Material.JEAN, Trama.LISA, rojo, null);
		Prenda zapatito =new Prenda("zapatito", Tipo.ZAPATO, Material.CUERO, Trama.LISA, azul, null);
		Prenda buzito =new Prenda("buzito", Tipo.BUZO, Material.ALGODON, Trama.LISA, blanco, null);
		Prenda anillito =new Prenda("anillito", Tipo.ANILLO, Material.ORO, Trama.LISA, verde, null);
		Prenda guantitos =new Prenda("guantitos", Tipo.GUANTES, Material.LANA, Trama.LISA, verde, null);
		Prenda bufandita =new Prenda("bufandita", Tipo.BUFANDA, Material.LANA, Trama.LISA, verde, null);
		Prenda camperita = new Prenda("camperita", Tipo.CAMPERA,Material.ALGODON,Trama.LISA,rojo,null);
		Prenda camisita = new Prenda ("camisita", Tipo.CAMISA,Material.ALGODON,Trama.LISA,azul,null);
		Prenda lentitos = new Prenda ("lentitos", Tipo.LENTES,Material.VIDRIO,Trama.LISA,negro,null);
		Guardarropa testito = new Guardarropa("testito");
		usuario.agregarGuardarropa(testito);

		//testito.agregarPrendas(guantitos);
		testito.agregarPrendas(bufandita);
		testito.agregarPrendas(anillito);
		testito.agregarPrendas(remerita);
		testito.agregarPrendas(zapatito);
		testito.agregarPrendas(lentitos);
		testito.agregarPrendas(buzito);
		testito.agregarPrendas(pantaloncito);
		//testito.agregarPrendas(anillito);
		testito.agregarPrendas(camperita);
		//testito.agregarPrendas(camisita);
		//testito.agregarPrendas(inviernito);

		return new ModelAndView(usuario, "perfil.hbs");
	}

}
