package dominio.clima;

import java.time.LocalDateTime;

public interface ProveedorClima {

    double temperatura(LocalDateTime fechaYHora);

}
