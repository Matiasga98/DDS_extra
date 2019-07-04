package ui;

import dominio.Evento;
import dominio.Usuario;
import org.uqbar.commons.model.annotations.Observable;
import ui.representacion.Fecha;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static repositorios.RepositorioUsuario.repositorioUsuario;

@Observable
public class VerEventos {

    private List<Usuario> usuarios = repositorioUsuario().getObjects();
    private Usuario seleccionado;
    private String inicio = "01-07-2019";
    private String fin = "01-07-2019";
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private List<Evento> eventosDeUnUsuario;

    public void buscarSucesoDeUsuario(){
        eventosDeUnUsuario = new ArrayList<>(seleccionado.getEventos());
        Collections.sort(eventosDeUnUsuario, Comparator.comparing(Evento::getFechaYHora));
    }

    public void filtarPorFecha(){

        fechaFin = new Fecha().viewToModel(fin);
        fechaInicio = new Fecha().viewToModel(inicio);

        List<Evento> filtrados = new ArrayList<>(
                eventosDeUnUsuario
                        .stream()
                        .filter(evento -> estaEnIntervalo(evento))
                        .collect(toList())
        );

        eventosDeUnUsuario = filtrados;
    }

    private boolean estaEnIntervalo(Evento evento){
        LocalDateTime fechaYHora = evento.getFechaYHora();
        return fechaInicio.isBefore(fechaYHora) && fechaFin.isAfter(fechaYHora);
    }

    public void limpiar() {
        inicio = null;
        fin = null;
        eventosDeUnUsuario.clear();
    }


    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Usuario seleccionado) {
        this.seleccionado = seleccionado;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Evento> getEventosDeUnUsuario() {
        return eventosDeUnUsuario;
    }

    public void setEventosDeUnUsuario(List<Evento> eventosDeUnUsuario) {
        this.eventosDeUnUsuario = eventosDeUnUsuario;
    }

}
