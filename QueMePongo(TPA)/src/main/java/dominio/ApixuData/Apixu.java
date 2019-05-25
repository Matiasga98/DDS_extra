package dominio.ApixuData;

import com.google.gson.Gson;
import dominio.AccuweatherData.EstadisticaClimaticaAccu;
import dominio.Clima;
import dominio.Pronostico;
import dominio.ProveedorClima;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Apixu implements ProveedorClima {

    public static  void ObtenerClima(){


        String url = "http://api.apixu.com/v1/forecast.json?key=867f3bfe21674112a7830607192505&q=Buenos%20Aires&days=5";

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse resp = null;
        try {
            resp = client.execute(get);
            HttpEntity entity = resp.getEntity();
            Gson gson = new Gson();
            String infoClimatica = EntityUtils.toString(entity);
            EstadisticaApixu dato = gson.fromJson(infoClimatica, EstadisticaApixu.class);
            Clima clima = new Clima();

            for (int i=0; i<5; i++){
                
                clima.pronosticos.add(new Pronostico(dato.forecast.forecastday.get(i).date, dato.forecast.forecastday.get(i).day.avgtemp_c));

            }
            System.out.println(clima.pronosticos.get(0).temperaturaPromedio);

        }
        catch (IOException ioe) { System.err.println("Something went wrong getting the weather: ");  ioe.printStackTrace(); }
        catch (Exception e ){ System.err.println("Unknown error: "); e.printStackTrace(); }
    }
}