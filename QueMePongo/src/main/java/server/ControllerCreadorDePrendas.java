package server;

import dominio.*;
import dominio.enumerados.Material;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;
import dominio.excepciones.MaterialInconsistente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import static spark.Spark.halt;

//import dominio.RepositorioGuardarropas;

public class ControllerCreadorDePrendas {

	Borrador borrador = new Borrador();

	public ModelAndView CrearPrenda(Request req, Response res){

		return new ModelAndView(borrador,"CreadorDePrendas.hbs");
	}

	public ModelAndView PostCrearPrenda(Request req, Response res){

		borrador.definirNombre(req.queryParams("nombrePrenda"));
		borrador.definirTipo(Tipo.valueOf(req.queryParams("tipoPrenda")));
		borrador.definirMaterial(Material.valueOf(req.queryParams("materialPrenda")));
		borrador.definirTrama(Trama.valueOf(req.queryParams("tramaPrenda")));

		try {
			borrador.chequearMaterialSegunTipoDePrenda();
		}
		catch (MaterialInconsistente ex){
			res.redirect("CrearPrenda");
			return null;
		}

		borrador.setMaterialInconsistente(false);
		res.redirect("CrearPrenda/Color");

		return null;
	}
	public ModelAndView CrearPrendaColor(Request req, Response res){
		Map<String, Object> model = new HashMap<>();
		model.clear();

		Boolean color_secundario_error= req.session().attribute("color_secundario_error");
		color_secundario_error = color_secundario_error==null? false:color_secundario_error;

		model.put("color_secundario_error",color_secundario_error);

		return new ModelAndView(model,"CreadorDePrendasColor.hbs");
	}

	public ModelAndView PostPrendaColor(Request req, Response res){

		String id = req.cookie("id");
		Usuario usuario = Repositorio.getInstancia().buscarUsuarioPorId(id).get();

		String hexaColorPrcpal = req.queryParams("group1");
		Integer rojoPrcpal = Integer.valueOf( hexaColorPrcpal.substring( 1, 3 ), 16 );
		Integer verdePrcpal = Integer.valueOf( hexaColorPrcpal.substring( 3, 5 ), 16 );
		Integer azulPrcpal = Integer.valueOf( hexaColorPrcpal.substring( 5, 7 ), 16 );

		String hexaColorSecundario =  req.queryParams("group2");
		Integer rojoSecundario = Integer.valueOf( hexaColorSecundario.substring( 1, 3 ), 16 );
		Integer verdeSecundario = Integer.valueOf( hexaColorSecundario.substring( 3, 5 ), 16 );
		Integer azulSecundario = Integer.valueOf( hexaColorSecundario.substring( 5, 7 ), 16 );

		String tieneColorSecundario = req.queryParams("tieneColorSecundario");

		Color colorPrimario = new Color(rojoPrcpal, verdePrcpal, azulPrcpal);
		borrador.definirColorPrimario(colorPrimario);
		borrador.definirColorSecundario(null);

		//System.out.println(req.session().attribute("nombreGuardarropa").toString());

		if((req.queryParams("tieneColorSecundario") != null)){
			Color colorSecundario =  new Color(rojoSecundario, verdeSecundario, azulSecundario);
			borrador.definirColorSecundario(colorSecundario);
		}

		if (hexaColorPrcpal.equals(hexaColorSecundario) && (tieneColorSecundario != null) ){

			req.session().attribute("color_secundario_error", true);
			res.redirect("Color");
			return null;
		}
		else {
			req.session().attribute("color_secundario_error", false);
			if (req.session().attribute("nombreGuardarropa") !=null){
				Guardarropa guardarropa = usuario.getGuardarropas().stream().filter(armario -> armario.getNombre().equals(req.session().attribute("nombreGuardarropa").toString())).findFirst().get();
				CrearPrendaFinal(guardarropa);
				req.session().attribute("nombreGuardarropa", null);
				res.redirect("/perfil");
				return null;
			}
			else {

				res.redirect("final");
			}
			return null;
		}

	}

	public ModelAndView PrendaFinal(Request req, Response res){
		Map<String, Object> model = new HashMap<>();

		Boolean error= req.session().attribute("error");
		error = error==null? false:error;

		model.put("error",error);
		if(error){
			model.put("mensaje",req.session().attribute("mensaje"));
			req.session().attribute("error",false);
		}


		String id = req.cookie("id");
		Usuario usuario = Repositorio.getInstancia().buscarUsuarioPorId(id).get();
		model.put("usuario",usuario);

		return new ModelAndView(model,"CreadorDePrendasGuardarropa.hbs");
	}

	public ModelAndView PostPrendaFinal(Request req, Response res) {

		// Traigo los atributos cargados en las pantallas
		String id = req.cookie("id");
		// Busca al usuario logeado
		Usuario usuario = Repositorio.getInstancia().buscarUsuarioPorId(id).get();

		String nombreGuardarropas = req.queryParams("guardarropas");

		if (nombreGuardarropas.equals("Guardarropas")){
			req.session().attribute("error",true);
			req.session().attribute("mensaje","Seleccione un Guardarropas");
			res.redirect("/CrearPrenda/final");
			return null;
		}

		req.session().attribute("error",false);

		// Busca el guardarropas en el que esta creando la prenda
		Guardarropa guardarropa = usuario.getGuardarropas().stream().filter(armario -> armario.getNombre().equals(nombreGuardarropas)).findFirst().get();
		CrearPrendaFinal(guardarropa);
		res.redirect("/perfil");
		return null;

	}
	public void CrearPrendaFinal(Guardarropa guardarropa){
		// Genera la instancia de prenda
		Prenda nuevaPrenda = borrador.crearPrenda();
		// Agrega la prenda al guardarropas
		guardarropa.agregarPrendas(nuevaPrenda);

		// Persistencia de los datos modificados y creados
		EntityManager entityManager = JPAUtility.getEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(nuevaPrenda);
		entityManager.merge(guardarropa);	// Aca va merge para que actualice el guardarropas con la prenda agregada

		entityManager.getTransaction().commit();




	}

}
