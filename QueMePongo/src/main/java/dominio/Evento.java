package dominio;

import java.time.LocalDateTime;
import it.sauronsoftware.cron4j.Scheduler;

public class Evento {
    private String tipo;
    private LocalDateTime fecha;
    private boolean repetitivo;
    
    public Evento (String elEvento, LocalDateTime unaFecha, boolean repetir){
        tipo = elEvento;
        fecha = unaFecha;
        this.repetitivo = repetir;
        Scheduler planificador = new Scheduler();
        if (this.repetitivo)
        	planificador.schedule("00" + unaFecha.getHour() + unaFecha.getDayOfMonth() + unaFecha.getMonthValue() + "*", new Runnable() {public void run() {System.out.println("Te aviso el evento!!");}});
        else
        	planificador.schedule("00" + unaFecha.getHour() + unaFecha.getDayOfMonth() + unaFecha.getMonthValue() + unaFecha.getYear(), new Runnable() {public void run() {System.out.println("Te aviso el evento!!");}});
        planificador.start();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    

}
