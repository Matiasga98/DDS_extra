package dominio.clima;

import java.time.LocalDateTime;

public class Pronostico {
    private LocalDateTime fechaYHora;
    private double temperatura;
    private boolean tieneAlertasMeteorol�gicas;

    public Pronostico(LocalDateTime fechaYHora, Double temperatura){
        this.fechaYHora = fechaYHora;
        this.temperatura = temperatura;
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

    public boolean tieneAlertasMeteorol�gicas() {
    	return tieneAlertasMeteorol�gicas;
    }
    
    public void tieneAlertasMeteorol�gicas(boolean tieneAlertasMeteorol�gicas) {
    	this.tieneAlertasMeteorol�gicas = tieneAlertasMeteorol�gicas;
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
