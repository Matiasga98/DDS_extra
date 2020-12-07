package server;

import dominio.Evento;
import dominio.Repositorio;
import dominio.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class ControllerMostrarEventos {

    public ModelAndView listarEventos(Request req, Response res){
        Integer id = req.session().attribute("id");
        if (id == null) res.redirect("/login");
        Map<String,Object> model = new HashMap<String,Object>();
        Usuario usuario = Repositorio.getInstancia().buscarUsuarioPorId(id);
        List<Evento> eventos = new ArrayList<>(usuario.getEventos());
        eventos.sort((o1,o2)->o1.getFechaYHora().compareTo(o2.getFechaYHora()));
        model.put("eventos",eventos);
        model.put("nombre",usuario.getUsername());
        return new ModelAndView(model,"MostrarEventos.hbs");
    }
    public ModelAndView postEventos(Request req, Response res){
        req.session().attribute("nombreEvento", req.queryParams("botoncito2"));
        res.redirect("/Sugerencias");
        return null;
    }
}
