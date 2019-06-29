package dominio.clima.AccuweatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface AccuWeatherAPI {

    @GET("/forecasts/v1/hourly/12hour/{codigo}")
    Call<List<AccuWeatherPronosticoDTO>> pronosticosPorHora(@Path("codigo") String codigo, @Query("apikey") String key, @Query("languague") String lenguaje,@Query("details") String detalle, @Query("metric") String metrica);
/*
    @GET("/forecasts/v1/daily/5day/{codigo}?apikey={key}")
    Call<PronosticoDeCincoDias> pronosticosPorFecha(@Path("codigo") String codigo, @Path("key") String key);

    @GET("/currentconditions/v1/{codigo}?apikey={key}")
    Call<ClimaActual> climaActual(@Path("codigo") String codigo, @Path("key") String key);
*/
}
