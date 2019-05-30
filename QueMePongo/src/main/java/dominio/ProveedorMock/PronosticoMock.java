package dominio.ProveedorMock;

import java.time.LocalDate;

public class PronosticoMock {
    public String fecha;
    public double temperaturaPromedio;

    public PronosticoMock(String unaFecha, Double unaTemperatura){
        fecha = unaFecha;
        temperaturaPromedio = unaTemperatura;
    }

    public void setFecha(String unaFecha){
        fecha = unaFecha;
    }
    public void setTemperaturaPromedio(Double unaTemperatura){
        temperaturaPromedio = unaTemperatura;
    }
}
