package dominio.clima;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pronostico {
    public LocalDateTime fecha;
    public double temperaturaPromedio;

    public Pronostico(LocalDateTime unaFecha, Double unaTemperatura){
        fecha = unaFecha;
        temperaturaPromedio = unaTemperatura;
    }

    public void setFecha(LocalDateTime unaFecha){
        fecha = unaFecha;
    }
    public void setTemperaturaPromedio(Double unaTemperatura){
        temperaturaPromedio = unaTemperatura;
    }
}
