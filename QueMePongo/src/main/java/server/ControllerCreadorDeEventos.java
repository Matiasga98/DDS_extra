package server;

import dominio.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

import javax.persistence.EntityManager;

import static spark.Spark.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

import java.time.LocalDateTime;

public class ControllerCreadorDeEventos {

    BorradorEvento borrador;

    public ModelAndView IrACrearEvento(Request req, Response res){

        res.redirect("/CrearEvento");
        return null;
    }

    public ModelAndView CrearEvento(Request req, Response res){
        Map<String, Object> model = new HashMap<>();

        borrador = new BorradorEvento();
        model.put("borrador",borrador);

        Boolean error = req.session().attribute("errorFormato");
        error = error==null? false:error;
        model.put("errorFormato",error);

        return new ModelAndView(model,"CreadorDeEventos.hbs");
    }

    public Void PostCrearEvento(Request req, Response res){

        // Obtiene el usuario loggeado
        Integer id = req.session().attribute("id");
        if (id == null) res.redirect("/login");
        Usuario usuario = Repositorio.getInstancia().buscarUsuarioPorId(id);

        // Carga los atributos del Evento:
        borrador.definirNombre(req.queryParams("nombreEvento"));

        // La fecha generaba excepcion si es inválida, por lo que la tengo que manejar si o si
        try {
            LocalDateTime fechaYHora = LocalDateTime.parse(req.queryParams("fechaYHora"));
            borrador.definirFechaYHora(fechaYHora);
        } catch (DateTimeParseException e) {

            req.session().attribute("errorFormato",true);
            res.redirect("/evento");
            return null;
        }

        borrador.definirFlexible(Boolean.parseBoolean(req.queryParams("flexible")));
        // Uso por default Accuweather
        borrador.definirProveedor(borrador.ProveedoresDic.get("Accuweather"));
        //borrador.definirProveedor(borrador.ProveedoresDic.get(req.queryParams("proveedor")));

        // Crea el evento
        Evento nuevoEvento = borrador.crearEvento();

        // Asigna el evento al usuario logeado
        usuario.agregarEvento(nuevoEvento);

        // Persistencia de los datos modificados y creados
        EntityManager entityManager = JPAUtility.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(nuevoEvento);
        entityManager.merge(usuario);
        entityManager.getTransaction().commit();

        res.redirect("/perfil");
        return null;
    }

}