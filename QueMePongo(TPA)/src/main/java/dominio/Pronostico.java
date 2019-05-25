package dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pronostico {
    public String fechaYHora;
    public double temperaturaPromedio;

    public Pronostico(String unaFecha, Double unaTemperatura){
        fechaYHora = unaFecha;
        temperaturaPromedio = unaTemperatura;
    }

    public void setFechaYHora(String unaFecha){
        fechaYHora = unaFecha;
    }
    public void setTemperaturaPromedio(Double unaTemperatura){
        temperaturaPromedio = unaTemperatura;
    }
}
