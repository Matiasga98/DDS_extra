package dominio.ProveedorMock;
import com.google.gson.Gson;
import dominio.Clima;
import dominio.Pronostico;
import dominio.ProveedorClima;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.GsonBuilder;
public class ProveedorMock implements ProveedorClima {



    @Override
    public Clima obtenerClima() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Clima clima = new Clima();
        String path = ".\\src\\main\\java\\dominio\\ProveedorMock\\mock.json";
        FileReader reader = null;
        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ClimaMock dato = gson.fromJson(reader, ClimaMock.class);
        dato.pronosticos.stream().forEach(pronostico -> this.guardarEnPronostico(clima,pronostico));
        try (FileWriter writer = new FileWriter(".\\Clima.json")) {
            gson.toJson(clima, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //String path = "..\\..\\..\\Clima.json";
        //gson.toJson(clima, new FileWriter(path));
        return clima;

    }

    private void guardarEnPronostico(Clima clima, PronosticoMock pronostico) {
        clima.pronosticos.add(new Pronostico(LocalDate.parse(pronostico.fecha), pronostico.temperaturaPromedio));
    }


}
