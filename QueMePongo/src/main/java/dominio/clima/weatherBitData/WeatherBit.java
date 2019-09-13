package dominio.clima.weatherBitData;

import dominio.clima.*;
import dominio.clima.AccuweatherData.AccuWeather;
import dominio.excepciones.ErrorAlConsumirAPI;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.stream.Collectors.toList;

public class WeatherBit implements ProveedorClima {

    private String URL = "http://api.weatherbit.io/v2.0/";
    private String CODIGO_PAIS = "3427408";
    private String KEY = "36905efcce0846dd81efb1fe0cd613f3";
    private String LENGUAJE = "es";

    private WeatherBitAPI getWeatherBitAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(WeatherBitAPI.class);
    }

    private WeatherBitPronosticoDTO pronosticosDesdeWeatherBit(){
        WeatherBitAPI service = getWeatherBitAPI();
        Call<WeatherBitPronosticoDTO> call = service.pronosticosPorHora(CODIGO_PAIS,KEY,LENGUAJE);
        Response<WeatherBitPronosticoDTO> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new ErrorAlConsumirAPI("Error al consumir WeatherBit", e);
        }
        return response.body();
    }

    private List<Pronostico> pronosticosNormalizados(){
        return pronosticosDesdeWeatherBit()
                .getData()
                .stream()
                .map(dato->dato.normalizar())
                .collect(toList());
    }

    // La consulta tiene que estar en el rango de 48 por delante.
    public  double temperatura(LocalDateTime fechaYHora){
        if(!CachePronosticos.datoDisponible(fechaYHora)){
            CachePronosticos.actualizar(pronosticosNormalizados());
        }
        return CachePronosticos.temperatura(fechaYHora);
    }

    public boolean tieneAlertasMeteorologicas(LocalDateTime fechaYHora) {
    	return CachePronosticos.tieneAlertasMeteorologicas(fechaYHora);
    }
    
}