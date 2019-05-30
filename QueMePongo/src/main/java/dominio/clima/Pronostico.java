package dominio.clima;

import java.time.LocalDate;

public class Pronostico {
    public LocalDate fecha;
    public double temperaturaPromedio;

    public Pronostico(LocalDate unaFecha, Double unaTemperatura){
        fecha = unaFecha;
        temperaturaPromedio = unaTemperatura;
    }

    public void setFecha(LocalDate unaFecha){
        fecha = unaFecha;
    }
    public void setTemperaturaPromedio(Double unaTemperatura){
        temperaturaPromedio = unaTemperatura;
    }
}
