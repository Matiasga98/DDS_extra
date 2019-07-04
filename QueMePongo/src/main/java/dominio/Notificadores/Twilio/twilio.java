package dominio.Notificadores.Twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class twilio {
    // Replace these placeholders with your Account Sid and Auth Token
    public static final String ACCOUNT_SID = "ACbc8872b862fd5ec0173bdde9e8803b16";
    public static final String AUTH_TOKEN = "02a147f536fb9781ae82cd6a1d1b35c3";

    public static void MandarWpp() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+5491131200062"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                "Hello from your friendly neighborhood Java application!")
                .create();
    }
}