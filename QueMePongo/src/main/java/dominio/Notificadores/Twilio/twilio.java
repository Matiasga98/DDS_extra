package dominio.Notificadores.Twilio;

import java.util.Set;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import dominio.Notificadores.Notificador;
import dominio.Atuendo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="T")
public class twilio extends Notificador {
    // Replace these placeholders with your Account Sid and Auth Token
    public static final String ACCOUNT_SID = "ACbc8872b862fd5ec0173bdde9e8803b16";
    public static final String AUTH_TOKEN = "02a147f536fb9781ae82cd6a1d1b35c3";

    @Override
    public void notificar(Set<Atuendo> sugerencias) {
    	MandarWpp(sugerencias);
    }
    
    @Override
    public void alertar(Set<Atuendo> sugerencias) {
    	AlertaMeteorologica(sugerencias);
    }
    
    public static void MandarWpp(Set<Atuendo> sugerencias) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+5491131200062"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                "Se acerca un nuevo evento!! Vestite bien!! " + sugerencias)
                .create();
    }
    
    public static void AlertaMeteorologica(Set<Atuendo> sugerencias) {
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+5491131200062"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                "Se ha detectado una nueva alerta meteorol�gica por lo que tus posibles atuendos deben ser reformulados. " + sugerencias)
                .create();
    }
}