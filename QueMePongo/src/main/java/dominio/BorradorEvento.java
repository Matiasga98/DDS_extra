package dominio;

import dominio.clima.AccuweatherData.AccuWeather;
import dominio.clima.ProveedorClima;
import dominio.clima.weatherBitData.WeatherBit;
import dominio.enumerados.ModoDeRepeticion;

import java.time.LocalDateTime;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.*;

public class BorradorEvento {

    private String nombre;
    private LocalDateTime fechaYHora;
    private boolean tieneSugerencias;
    private boolean tieneAlertasMeteorologicas;
    private ProveedorClima proveedor;
    private boolean flexible;

    public List<String> Proveedores = new ArrayList<String>();
    public Dictionary<String, ProveedorClima> ProveedoresDic = new Hashtable();

    public List<String> getProveedores() {
        return Proveedores;
    }

    public BorradorEvento(){
        Proveedores.add("Accuweather");
        Proveedores.add("WeatherBit");

        ProveedoresDic.put("Accuweather", new AccuWeather());
        ProveedoresDic.put("WeatherBit", new WeatherBit());
    }

    public void definirNombre(String nombre) {
        this.nombre = nombre;
    }
    public void definirFechaYHora(LocalDateTime fechaYHora){ this.fechaYHora = fechaYHora;}
    public void definirTieneSugerencias(boolean tieneSug){ this.tieneSugerencias = tieneSug;}
    public void definirTieneAltertas(boolean tieneAlertas){ this.tieneSugerencias = tieneAlertas;}
    public void definirProveedor(ProveedorClima prov){ this.proveedor = prov;}
    public void definirFlexible(boolean flex){ this.flexible = flex; }

    public Evento crearEvento(){
        return new Evento(nombre, proveedor, fechaYHora, false, ModoDeRepeticion.DIARIO, null, flexible);
    }
}
