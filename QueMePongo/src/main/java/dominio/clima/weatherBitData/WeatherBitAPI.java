package dominio.clima.weatherBitData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherBitAPI {

    @GET("forecast/hourly")
    Call<WeatherBitPronosticoDTO> pronosticosPorHora(@Query("city_id") String codigo, @Query("key") String key, @Query("lang") String lenguaje);

}
