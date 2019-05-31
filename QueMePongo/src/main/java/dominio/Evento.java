package dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Evento {
    private String tipo;
    private LocalDateTime fecha;

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
