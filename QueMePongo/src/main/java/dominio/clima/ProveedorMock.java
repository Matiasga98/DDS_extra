package dominio.clima;

import dominio.clima.weatherBitData.WeatherBitAPI;
import dominio.clima.weatherBitData.WeatherBitPronosticoDTO;
import dominio.excepciones.ErrorAlConsumirAPI;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProveedorMock implements ProveedorClima {


    public  double temperatura(LocalDateTime fechaYHora){

        return 20;
    }

    public boolean tieneAlertasMeteorologicas(LocalDateTime fechaYHora) {
    	return false;
    }
    
}