package dominio.Notificadores.Twilio;

import java.util.HashSet;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import dominio.Notificadores.Notificador;
import dominio.Atuendo;
import dominio.Evento;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="T")
public class twilio extends Notificador {
    // Replace these placeholders with your Account Sid and Auth Token
    public static final String ACCOUNT_SID = "ACbc8872b862fd5ec0173bdde9e8803b16";
    public static final String AUTH_TOKEN = "02a147f536fb9781ae82cd6a1d1b35c3";

    @Override
    public void notificar(Evento evento, HashSet<Atuendo> sugerencias) {
    	MandarWpp(evento, sugerencias);
    }
    
    public static void MandarWpp(Evento evento, HashSet<Atuendo> sugerencias) {
    	String mensaje;
    	if (evento.tieneAlertasMeteorologicas) {
    		mensaje = "Se ha detectado una nueva alerta meteorológica por lo que tus posibles atuendos deben ser reformulados. ";
    	}
    	else {
    		mensaje = "Se acerca un nuevo evento!! Vestite bien!! ";
    	}
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+5491131200062"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                mensaje + sugerencias)
                .create();
    }
    
}