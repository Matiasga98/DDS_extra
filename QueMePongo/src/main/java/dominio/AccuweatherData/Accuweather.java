package dominio.AccuweatherData;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import dominio.Clima;
import dominio.Pronostico;
import dominio.ProveedorClima;
import org.apache.http.HttpEntity;
        import org.apache.http.client.methods.CloseableHttpResponse;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.CloseableHttpClient;
        import org.apache.http.impl.client.HttpClients;
        import org.apache.http.util.EntityUtils;
import java.time.*;
import java.time.format.DateTimeFormatter;
import com.google.gson.GsonBuilder;
public class Accuweather implements ProveedorClima {

    public  Clima obtenerClima(){       //consultar si deberia ser static o no
        //http://dataservice.accuweather.com/forecasts/v1/daily/1day/348735?apikey=<ApiKey>
        //http://dataservice.accuweather.com/forecasts/v1/daily/1day/<CITYID>?apikey=<ApiKey>

        String url = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/7894?apikey=" + "Cq6ZRPqswizBYVfSEulxqimVsrAGKZT9&metric=true";

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse resp = null;
        try {
            resp = client.execute(get);
            HttpEntity entity = resp.getEntity();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String infoClimatica = EntityUtils.toString(entity);
            EstadisticaClimaticaAccu dato = gson.fromJson(infoClimatica, EstadisticaClimaticaAccu.class);
            Clima clima = new Clima();
            dato.DailyForecasts.stream().forEach(pronostico -> this.guardarEnPronostico(clima,pronostico));
            //for (int i=0; i<5; i++){
             //   ZonedDateTime result = ZonedDateTime.parse(dato.DailyForecasts.get(i).Date, DateTimeFormatter.ISO_DATE_TIME);
             //   clima.pronosticos.add(new Pronostico(result.toLocalDate(), (dato.DailyForecasts.get(i).Temperature.Maximum.Value + dato.DailyForecasts.get(i).Temperature.Minimum.Value)/2));
            //}

            //String path = "C:\\Users\\Public\\Clima.json";
            try (FileWriter writer = new FileWriter(".\\clima.json")) {
                gson.toJson(clima, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //FileWriter writer = new FileWriter(path);
            //gson.toJson(clima, writer);
            //String json = gson.toJson(clima);
            //writer.write(json);
            return clima;
        }
        catch (IOException ioe) { System.err.println("Algo salio mal ");  ioe.printStackTrace(); }
        catch (Exception e ){ System.err.println("Error desconocido "); e.printStackTrace(); }
        return null;
    }
    public void guardarEnPronostico( Clima clima, DatoClimaticoAccu dato){
        ZonedDateTime result = ZonedDateTime.parse(dato.Date, DateTimeFormatter.ISO_DATE_TIME);
        clima.pronosticos.add(new Pronostico(result.toLocalDate(), (dato.Temperature.Maximum.Value + dato.Temperature.Minimum.Value)/2));
    }

}