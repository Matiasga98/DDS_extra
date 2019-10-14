package dominio;

import dominio.clima.AccuweatherData.AccuWeather;
import dominio.clima.ProveedorClima;
import dominio.enumerados.Material;
import dominio.enumerados.ModoDeRepeticion;
import dominio.enumerados.Tipo;
import dominio.enumerados.Trama;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class Repositorio {

    private Collection<Usuario> usuarios;

    private static Repositorio instancia = new Repositorio();

    private Repositorio(){
        usuarios = new HashSet<>();
        tester();
    }

    public static Repositorio getInstancia(){
        return instancia;
    }

    public Collection<Usuario> usuarios(){
        return usuarios;
    }

    public void nuevoUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public void eliminar(Usuario usuario){
        usuarios.remove(usuario);
    }

    public void avisarUsuarios () {
    	this.usuarios.forEach(usuario -> usuario.serAvisado());
    }

    public Optional<Usuario> buscarUsuario(String nombreUsuario){
        return this.usuarios.stream().filter(usuario -> usuario.getUsername().equals(nombreUsuario)).findFirst();
    }

    private void tester(){
        //Esto es para testear, no me pegues roli

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
        Guardarropa testito2 = new Guardarropa("testito2");


        //testito.agregarPrendas(guantitos);
        testito.agregarPrendas(bufandita);
        testito.agregarPrendas(anillito);
        testito.agregarPrendas(remerita);
        testito.agregarPrendas(zapatito);
        testito.agregarPrendas(lentitos);
        testito.agregarPrendas(buzito);
        testito.agregarPrendas(pantaloncito);
        testito2.agregarPrendas(anillito);
        testito.agregarPrendas(camperita);
        testito2.agregarPrendas(camisita);
        testito2.agregarPrendas(inviernito);

        Usuario fran = new Usuario("Fran", "Pongo ruido de fondo");
        Usuario cris = new Usuario("Cris", "h");
        fran.agregarGuardarropa(testito);
        cris.agregarGuardarropa(testito2);
        cris.agregarGuardarropa(testito);
        ProveedorClima mock = new AccuWeather();
        Evento cumpleDeHernan = new Evento("cumple Hernan AAAAA", mock, LocalDateTime.parse("2019-10-15T02:00:00"),false, ModoDeRepeticion.ANUAL, cris, false);
        Evento tiagoTerminaLaCarrera = new Evento("Tiago se recibio O M G", mock, LocalDateTime.parse("2019-10-15T03:00:00"),false, ModoDeRepeticion.ANUAL, cris, false);

        cris.agregarEvento(cumpleDeHernan);
        cris.agregarEvento(tiagoTerminaLaCarrera);
        fran.agregarEvento(tiagoTerminaLaCarrera);
        this.usuarios.add(fran);
        this.usuarios.add(cris);
    }
    
}
