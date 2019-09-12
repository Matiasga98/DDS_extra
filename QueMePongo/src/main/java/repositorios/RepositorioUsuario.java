package repositorios;

import dominio.Evento;
import dominio.Usuario;
import dominio.clima.AccuweatherData.AccuWeather;
import dominio.enumerados.ModoDeRepeticion;

import org.apache.commons.collections15.Predicate;
import org.uqbar.commons.model.CollectionBasedRepo;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

//Esta clase esta hecha para ver en UI como funciona no es un repo de verdad
public class RepositorioUsuario extends CollectionBasedRepo<Usuario> {

    private static List<String> nombres = new ArrayList<>(asList("Matias","Hernan","Francisco","Cristian","Elio"));

    private static RepositorioUsuario instancia = new RepositorioUsuario();

    public static RepositorioUsuario repositorioUsuario(){
        return instancia;
    }

    public RepositorioUsuario() {
        this.init();
    }

    private void init() {
        Set<Evento> eventos = new HashSet<Evento>(
                asList(
                        new Evento("Clase", new AccuWeather(), LocalDateTime.of(2019, Month.JULY, 1, 8, 30, 0), false, ModoDeRepeticion.ANUAL, new Usuario(false), false),
                        new Evento("Trabajo", new AccuWeather(), LocalDateTime.of(2019, Month.JULY, 2, 8, 0, 0), false, ModoDeRepeticion.ANUAL, new Usuario(false), false),
                        new Evento("Club", new AccuWeather(), LocalDateTime.of(2019, Month.JULY, 3, 13, 30, 0), false, ModoDeRepeticion.ANUAL, new Usuario(false), false),
                        new Evento("Viaje", new AccuWeather(), LocalDateTime.of(2019, Month.JULY, 4, 17, 30, 0), false, ModoDeRepeticion.ANUAL, new Usuario(false), false),
                        new Evento("Amigos", new AccuWeather(), LocalDateTime.of(2019, Month.JULY, 5, 19, 30, 0), false, ModoDeRepeticion.ANUAL, new Usuario(false), false)
                )
        );
        nombres.forEach(nombre->create(nombre,eventos));
    }

    public void create(String nombre, Set<Evento> eventos) {
        Usuario usuario = new Usuario(nombre,eventos);
        super.create(usuario);
    }

    public Usuario get(String nombre){
        return getObjects()
                .stream()
                .filter(usuario -> usuario.getNombre().equals(nombre))
                .findAny()
                .orElse(new Usuario("NoEncontro",null));
    }

    @Override
    protected Predicate<Usuario> getCriterio(Usuario example) {
        return null;
    }

    @Override
    public Class<Usuario> getEntityType() {
        return Usuario.class;
    }

    @Override
    public Usuario createExample() {
        return null;
    }
}
