package dominio.clima;

import com.google.gson.Gson;
import dominio.excepciones.DatoNoDisponible;
import dominio.excepciones.ErrorAlEscribirCache;
import dominio.excepciones.ErrorAlLeerCache;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class CachePronosticos {

    private static File archivo = new File("pronostico.cache");
    private static List<Pronostico> pronosticosGuardadosEnCache = new ArrayList<>();

    private CachePronosticos(){

    }

    public static boolean datoDisponible(LocalDateTime fechaYHora){
        try(FileReader lector = new FileReader(archivo)) {
            Pronostico[] pronosticos = new Gson().fromJson(lector,Pronostico[].class);
            pronosticosGuardadosEnCache = new ArrayList<>(asList(pronosticos));
        } catch (IOException e) {
            throw  new ErrorAlLeerCache("No se pudo leer de cache",e);
        }
        return pronosticosGuardadosEnCache.stream().anyMatch(dato -> dato.estaEnIntervalo(fechaYHora));
    }

    public static double temperatura(LocalDateTime fechaYHora){
        if(!datoDisponible(fechaYHora)){
            throw new DatoNoDisponible("No se encuentra la temperatura para la fecha" + fechaYHora);
        }
        return pronosticosGuardadosEnCache.stream().filter(dato -> dato.estaEnIntervalo(fechaYHora)).collect(toList()).get(0).getTemperatura();
    }
    
    public static boolean tieneAlertasMeteorologicas(LocalDateTime fechaYHora) {
    	if (!datoDisponible(fechaYHora)) {
    		throw new DatoNoDisponible("No se encuentra el dato para la fecha" + fechaYHora);
    	}
    	
    	return pronosticosGuardadosEnCache.stream().filter(dato -> dato.estaEnIntervalo(fechaYHora)).collect(toList()).get(0).tieneAlertasMeteorologicas();
    }

    public static void actualizar(List<Pronostico> pronosticos){
        borrarAntiguos();
        guardarNuevos(pronosticos);
    }

    private static void guardarNuevos(List<Pronostico> porHoraFecha){
        porHoraFecha.addAll(porHoraFecha);
        try(FileWriter escritor = new FileWriter(archivo)) {
            new Gson().toJson(porHoraFecha,escritor);
        } catch (IOException e) {
            throw new ErrorAlEscribirCache("No se pudo escribir en cache", e);
        }
    }

    private static void borrarAntiguos(){
        pronosticosGuardadosEnCache.clear();
    }

}
