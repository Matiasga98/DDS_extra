package dominio.clima;

import java.time.LocalDateTime;

public class Pronostico {
    private LocalDateTime fechaYHora;
    private double temperatura;
    private boolean tieneAlertasMeteorologicas;

    public Pronostico(LocalDateTime fechaYHora, Double temperatura, boolean tieneAlertasMeteorologicas){
        this.fechaYHora = fechaYHora;
        this.temperatura = temperatura;
        this.tieneAlertasMeteorologicas = tieneAlertasMeteorologicas;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public boolean tieneAlertasMeteorologicas() {
    	return tieneAlertasMeteorologicas;
    }
    
    public void tieneAlertasMeteorologicas(boolean tieneAlertasMeteorologicas) {
    	this.tieneAlertasMeteorologicas = tieneAlertasMeteorologicas;
    }
    
    @Override
    public String toString() {
        return "Pronostico{" +
                "fechaYHora=" + fechaYHora +
                ", temperatura=" + temperatura +
                '}';
    }

    public boolean estaEnIntervalo(LocalDateTime fechaYHora) {
        return this.fechaYHora.getHour()==fechaYHora.getHour()
                &&this.fechaYHora.toLocalDate().isEqual(fechaYHora.toLocalDate());
    }

}
