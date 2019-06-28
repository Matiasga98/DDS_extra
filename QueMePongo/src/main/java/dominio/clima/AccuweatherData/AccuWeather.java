package dominio.clima.AccuweatherData;

import java.io.IOException;

import dominio.clima.*;

import java.time.*;
import java.util.List;

import dominio.excepciones.ErrorAlConsumirAPI;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.stream.Collectors.toList;

public class AccuWeather implements ProveedorClima {

    private String URL = "http://dataservice.accuweather.com";
    private String CODIGO_PAIS = "7894";
    private String KEY = "Cq6ZRPqswizBYVfSEulxqimVsrAGKZT9";
    private String LENGUAJE = "es-ar";

    private AccuWeatherAPI getAccuWeatherAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(AccuWeatherAPI.class);
    }

    private List<AccuWeatherPronosticoDTO> pronosticosDesdeAccuWeather(){
        AccuWeatherAPI service = getAccuWeatherAPI();
        Call<List<AccuWeatherPronosticoDTO>> call = service.pronosticosPorHora(CODIGO_PAIS,KEY,LENGUAJE,"true","true");
        Response<List<AccuWeatherPronosticoDTO>> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new ErrorAlConsumirAPI("Error al consumir AccuWeather", e);
        }
        return response.body();
    }

    private List<Pronostico> pronosticosNormalizados(){
        return pronosticosDesdeAccuWeather()
                .stream()
                .map(dato->dato.normalizar())
                .collect(toList());
    }

    // La consulta tiene que estar en el rango de 12 por delante.
    public  double temperatura(LocalDateTime fechaYHora){
        if(!CachePronosticos.datoDisponible(fechaYHora)){
            CachePronosticos.actualizar(pronosticosNormalizados());
        }
        return CachePronosticos.temperatura(fechaYHora);
    }

}