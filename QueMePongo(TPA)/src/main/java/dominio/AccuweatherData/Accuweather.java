package dominio.AccuweatherData;
import com.google.gson.Gson;

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

public class Accuweather implements ProveedorClima {

    public static void ObtenerClima(){
        //http://dataservice.accuweather.com/forecasts/v1/daily/1day/348735?apikey=<ApiKey>
        //http://dataservice.accuweather.com/forecasts/v1/daily/1day/<CITYID>?apikey=<ApiKey>

        String url = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/7894?apikey=" + "Cq6ZRPqswizBYVfSEulxqimVsrAGKZT9&metric=true";

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse resp = null;
        try {
            resp = client.execute(get);
            HttpEntity entity = resp.getEntity();
            Gson gson = new Gson();
            String infoClimatica = EntityUtils.toString(entity);
            EstadisticaClimaticaAccu dato = gson.fromJson(infoClimatica, EstadisticaClimaticaAccu.class);
            Clima clima = new Clima();
            for (int i=0; i<5; i++){
                clima.pronosticos.add(new Pronostico(dato.DailyForecasts.get(i).Date, (dato.DailyForecasts.get(i).Temperature.Maximum.Value + dato.DailyForecasts.get(i).Temperature.Minimum.Value)/2));
            }
            System.out.println(clima.pronosticos.get(0).temperaturaPromedio);
        }
        catch (IOException ioe) { System.err.println("Something went wrong getting the weather: ");  ioe.printStackTrace(); }
        catch (Exception e ){ System.err.println("Unknown error: "); e.printStackTrace(); }
    }
}