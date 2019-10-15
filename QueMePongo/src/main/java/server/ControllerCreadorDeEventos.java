package server;

import dominio.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import static spark.Spark.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

import java.time.LocalDateTime;

public class ControllerCreadorDeEventos {

    public ModelAndView IrACrearEvento(Request req, Response res){
        String nombre = req.params("nombre");
        res.redirect("/perfil/"+nombre+"/CrearEvento");
        return null;
    }

    public ModelAndView CrearEvento(Request req, Response res){
        String nombre = req.params("nombre");
        BorradorEvento borrador = new BorradorEvento();

        return new ModelAndView(borrador,"CreadorDeEventos.hbs");
    }

    public ModelAndView PostCrearEvento(Request req, Response res){

        BorradorEvento borrador = new BorradorEvento();

        // Obtiene el usuario loggeado
        String username = req.cookie("name");
        Usuario usuario = Repositorio.getInstancia().buscarUsuario(username).get();

        // Carga los atributos del Evento:
        borrador.definirNombre(req.queryParams("nombreEvento"));

        // La fecha generaba excepcion si es inválida, por lo que la tengo que manejar si o si
        try {
            LocalDateTime fechaYHora = LocalDateTime.parse(req.queryParams("fechaYHora"));
            borrador.definirFechaYHora(fechaYHora);
        } catch (DateTimeParseException e) {
            halt(400, "Roli, cargá bien la fecha");
        }

        borrador.definirFlexible(Boolean.parseBoolean(req.queryParams("flexible")));
        borrador.definirProveedor(borrador.ProveedoresDic.get(req.queryParams("proveedor")));

        // Crea el evento
        Evento nuevoEvento = borrador.crearEvento();

        // Asigna el evento al usuario logeado
        usuario.agregarEvento(nuevoEvento);

        res.redirect("/perfil/"+username);
        return null;
    }

}