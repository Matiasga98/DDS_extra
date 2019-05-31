package dominio.clima.weatherBitData;

import com.google.gson.Gson;
import dominio.clima.Clima;
import dominio.clima.Pronostico;
import dominio.clima.ProveedorClima;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.google.gson.GsonBuilder;

public class WeatherBit implements ProveedorClima {

    public   Clima obtenerClima(){  //consultar si deberia ser static o no


        String url = "http://api.weatherbit.io/v2.0/forecast/hourly?key=36905efcce0846dd81efb1fe0cd613f3&city=Buenos%20Aires,07&country=AR";

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse resp = null;
        try {
            resp = client.execute(get);
            HttpEntity entity = resp.getEntity();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String infoClimatica = EntityUtils.toString(entity);
            ClimaWeatherBit dato = gson.fromJson(infoClimatica, ClimaWeatherBit.class);
            Clima clima = new Clima();
            dato.data.stream().forEach(pronostico -> this.guardarEnPronostico(clima,pronostico));
            // for (int i=0; i<5; i++){

            //   clima.pronosticos.add(new Pronostico(LocalDate.parse(dato.forecast.forecastday.get(i).date), dato.forecast.forecastday.get(i).day.avgtemp_c));

            //}

            try (FileWriter writer = new FileWriter(".\\Clima.json")) {
                gson.toJson(clima, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //String path = "..\\..\\..\\Clima.json";
            //gson.toJson(clima, new FileWriter(path));
            return clima;

        }
        catch (IOException ioe) { System.err.println("Algo salio mal ");  ioe.printStackTrace(); }
        catch (Exception e ){ System.err.println("Error desconocido "); e.printStackTrace(); }
        return null;
    }
    public void guardarEnPronostico( Clima clima,  pronosticoBit dato){
        clima.pronosticos.add(new Pronostico(LocalDateTime.parse(dato.timestamp_local), dato.temp));
    }
}