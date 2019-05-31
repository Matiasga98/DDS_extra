package dominio.clima.AccuweatherData;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.reflect.TypeToken;
import dominio.clima.Clima;
import dominio.clima.Pronostico;
import dominio.clima.ProveedorClima;
import org.apache.http.HttpEntity;
        import org.apache.http.client.methods.CloseableHttpResponse;
        import org.apache.http.client.methods.HttpGet;
        import org.apache.http.impl.client.CloseableHttpClient;
        import org.apache.http.impl.client.HttpClients;
        import org.apache.http.util.EntityUtils;

import java.lang.reflect.Type;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.google.gson.GsonBuilder;
public class Accuweather implements ProveedorClima {

    public  Clima obtenerClima(){       //consultar si deberia ser static o no
        //http://dataservice.accuweather.com/forecasts/v1/daily/1day/348735?apikey=<ApiKey>
        //http://dataservice.accuweather.com/forecasts/v1/daily/1day/<CITYID>?apikey=<ApiKey>

        String url = "http://dataservice.accuweather.com/forecasts/v1/hourly/12hour/7894?apikey=" + "Cq6ZRPqswizBYVfSEulxqimVsrAGKZT9&metric=true";

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse resp = null;
        try {
            resp = client.execute(get);
            HttpEntity entity = resp.getEntity();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String infoClimatica = EntityUtils.toString(entity);
            Type listType = new TypeToken<List<DatoClimaticoAccu>>(){}.getType();
            List<DatoClimaticoAccu> HourlyForecasts = gson.fromJson(infoClimatica, listType);
            Clima clima = new Clima();
            HourlyForecasts.stream().forEach(pronostico -> this.guardarEnPronostico(clima,pronostico));
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
        ZonedDateTime result = ZonedDateTime.parse(dato.DateTime, DateTimeFormatter.ISO_DATE_TIME);
        clima.pronosticos.add(new Pronostico(result.toLocalDateTime(), (dato.Temperature.Value)));
    }

}