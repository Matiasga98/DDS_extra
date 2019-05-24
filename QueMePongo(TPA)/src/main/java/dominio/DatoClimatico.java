package dominio;

public class DatoClimatico {

    public String DateTime;
    public long EpochDateTime;
    public int WeatherIcon;
    public String IconPhrase;
    public Boolean IsDaylight;
    public Temperatura Temperature;
    public int PrecipitationProbability;
    public String MobileLink;
    public String Link;

    public String getIconPhrase() {
        return IconPhrase;
    }

}

