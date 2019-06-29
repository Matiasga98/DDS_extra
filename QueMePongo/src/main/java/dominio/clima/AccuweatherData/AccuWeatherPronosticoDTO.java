package dominio.clima.AccuweatherData;

import dominio.clima.Pronostico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*Esta clase solo es para obtener los datos de AccuWeather.
  DTO: Data Transfer
 */
public class AccuWeatherPronosticoDTO {
    Temperature Temperature;
    RealFeelTemperature RealFeelTemperature;
    WetBulbTemperature WetBulbTemperature;
    DewPoint DewPoint;
    Wind Wind;
    WindGust WindGust;
    Visibility Visibility;
    Ceiling Ceiling;
    TotalLiquid TotalLiquid;
    Rain Rain;
    Snow Snow;
    Ice Ice;
    private String DateTime;
    private double EpochDateTime;
    private double WeatherIcon;
    private String IconPhrase;
    private boolean HasPrecipitation;
    private String PrecipitationType;
    private String PrecipitationIntensity;
    private boolean IsDaylight;
    private double RelativeHumidity;
    private double UVIndex;
    private String UVIndexText;
    private double PrecipitationProbability;
    private double RainProbability;
    private double SnowProbability;
    private double IceProbability;
    private double CloudCover;
    private String MobileLink;
    private String Link;

    private DateTimeFormatter formato = DateTimeFormatter.ISO_OFFSET_DATE_TIME;


    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String DateTime) {
        this.DateTime = DateTime;
    }

    public double getEpochDateTime() {
        return EpochDateTime;
    }

    public void setEpochDateTime(double EpochDateTime) {
        this.EpochDateTime = EpochDateTime;
    }

    public double getWeatherIcon() {
        return WeatherIcon;
    }

    public void setWeatherIcon(double WeatherIcon) {
        this.WeatherIcon = WeatherIcon;
    }

    public String getIconPhrase() {
        return IconPhrase;
    }

    public void setIconPhrase(String IconPhrase) {
        this.IconPhrase = IconPhrase;
    }

    public boolean getHasPrecipitation() {
        return HasPrecipitation;
    }

    public void setHasPrecipitation(boolean HasPrecipitation) {
        this.HasPrecipitation = HasPrecipitation;
    }

    public String getPrecipitationType() {
        return PrecipitationType;
    }

    public void setPrecipitationType(String PrecipitationType) {
        this.PrecipitationType = PrecipitationType;
    }

    public String getPrecipitationIntensity() {
        return PrecipitationIntensity;
    }

    public void setPrecipitationIntensity(String PrecipitationIntensity) {
        this.PrecipitationIntensity = PrecipitationIntensity;
    }

    public boolean getIsDaylight() {
        return IsDaylight;
    }

    public void setIsDaylight(boolean IsDaylight) {
        this.IsDaylight = IsDaylight;
    }

    public Temperature getTemperature() {
        return Temperature;
    }

    public void setTemperature(Temperature Temperature) {
        this.Temperature = Temperature;
    }

    public RealFeelTemperature getRealFeelTemperature() {
        return RealFeelTemperature;
    }

    public void setRealFeelTemperature(RealFeelTemperature RealFeelTemperature) {
        this.RealFeelTemperature = RealFeelTemperature;
    }

    public WetBulbTemperature getWetBulbTemperature() {
        return WetBulbTemperature;
    }

    public void setWetBulbTemperature(WetBulbTemperature WetBulbTemperature) {
        this.WetBulbTemperature = WetBulbTemperature;
    }

    public DewPoint getDewPoint() {
        return DewPoint;
    }

    public void setDewPoint(DewPoint DewPoint) {
        this.DewPoint = DewPoint;
    }

    public Wind getWind() {
        return Wind;
    }

    public void setWind(Wind Wind) {
        this.Wind = Wind;
    }

    public WindGust getWindGust() {
        return WindGust;
    }

    public void setWindGust(WindGust WindGust) {
        this.WindGust = WindGust;
    }

    public double getRelativeHumidity() {
        return RelativeHumidity;
    }

    public void setRelativeHumidity(double RelativeHumidity) {
        this.RelativeHumidity = RelativeHumidity;
    }

    public Visibility getVisibility() {
        return Visibility;
    }

    public void setVisibility(Visibility Visibility) {
        this.Visibility = Visibility;
    }

    public Ceiling getCeiling() {
        return Ceiling;
    }

    public void setCeiling(Ceiling Ceiling) {
        this.Ceiling = Ceiling;
    }

    public double getUVIndex() {
        return UVIndex;
    }

    public void setUVIndex(double UVIndex) {
        this.UVIndex = UVIndex;
    }

    public String getUVIndexText() {
        return UVIndexText;
    }

    public void setUVIndexText(String UVIndexText) {
        this.UVIndexText = UVIndexText;
    }

    public double getPrecipitationProbability() {
        return PrecipitationProbability;
    }

    public void setPrecipitationProbability(double PrecipitationProbability) {
        this.PrecipitationProbability = PrecipitationProbability;
    }

    public double getRainProbability() {
        return RainProbability;
    }

    public void setRainProbability(double RainProbability) {
        this.RainProbability = RainProbability;
    }

    public double getSnowProbability() {
        return SnowProbability;
    }

    public void setSnowProbability(double SnowProbability) {
        this.SnowProbability = SnowProbability;
    }

    public double getIceProbability() {
        return IceProbability;
    }

    public void setIceProbability(double IceProbability) {
        this.IceProbability = IceProbability;
    }

    public TotalLiquid getTotalLiquid() {
        return TotalLiquid;
    }

    public void setTotalLiquid(TotalLiquid TotalLiquid) {
        this.TotalLiquid = TotalLiquid;
    }

    public Rain getRain() {
        return Rain;
    }

    public void setRain(Rain Rain) {
        this.Rain = Rain;
    }

    public Snow getSnow() {
        return Snow;
    }

    public void setSnow(Snow Snow) {
        this.Snow = Snow;
    }

    public Ice getIce() {
        return Ice;
    }

    public void setIce(Ice Ice) {
        this.Ice = Ice;
    }

    public double getCloudCover() {
        return CloudCover;
    }

    public void setCloudCover(double CloudCover) {
        this.CloudCover = CloudCover;
    }

    public String getMobileLink() {
        return MobileLink;
    }

    public void setMobileLink(String MobileLink) {
        this.MobileLink = MobileLink;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String Link) {
        this.Link = Link;
    }

    public Pronostico normalizar() {
        return new Pronostico(LocalDateTime.parse(DateTime,formato),Temperature.getValue());
    }
}

class Ice {
    private double Value;
    private String Unit;
    private double UnitType;


    public double getValue() {
        return Value;
    }

    public void setValue(double Value) {
        this.Value = Value;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public double getUnitType() {
        return UnitType;
    }

    public void setUnitType(double UnitType) {
        this.UnitType = UnitType;
    }
}

class Snow {
    private double Value;
    private String Unit;
    private double UnitType;


    public double getValue() {
        return Value;
    }

    public void setValue(double Value) {
        this.Value = Value;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public double getUnitType() {
        return UnitType;
    }

    public void setUnitType(double UnitType) {
        this.UnitType = UnitType;
    }
}

class Rain {
    private double Value;
    private String Unit;
    private double UnitType;


    public double getValue() {
        return Value;
    }

    public void setValue(double Value) {
        this.Value = Value;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public double getUnitType() {
        return UnitType;
    }

    public void setUnitType(double UnitType) {
        this.UnitType = UnitType;
    }
}

class TotalLiquid {
    private double Value;
    private String Unit;
    private double UnitType;

    public double getValue() {
        return Value;
    }

    public void setValue(double Value) {
        this.Value = Value;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public double getUnitType() {
        return UnitType;
    }

    public void setUnitType(double UnitType) {
        this.UnitType = UnitType;
    }
}

class Ceiling {
    private double Value;
    private String Unit;
    private double UnitType;

    public double getValue() {
        return Value;
    }

    public void setValue(double Value) {
        this.Value = Value;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public double getUnitType() {
        return UnitType;
    }

    public void setUnitType(double UnitType) {
        this.UnitType = UnitType;
    }
}

class Visibility {
    private double Value;
    private String Unit;
    private double UnitType;


    public double getValue() {
        return Value;
    }

    public void setValue(double Value) {
        this.Value = Value;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public double getUnitType() {
        return UnitType;
    }

    public void setUnitType(double UnitType) {
        this.UnitType = UnitType;
    }
}

class WindGust {
    Speed Speed;
    Direction Direction;


    public Speed getSpeed() {
        return Speed;
    }

    public void setSpeed(Speed Speed) {
        this.Speed = Speed;
    }

    public Direction getDirection() {
        return Direction;
    }

    public void setDirection(Direction Direction) {
        this.Direction = Direction;
    }
}

class Direction {
    private double Degrees;
    private String Localized;
    private String English;


    public double getDegrees() {
        return Degrees;
    }

    public void setDegrees(double Degrees) {
        this.Degrees = Degrees;
    }

    public String getLocalized() {
        return Localized;
    }

    public void setLocalized(String Localized) {
        this.Localized = Localized;
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String English) {
        this.English = English;
    }
}

class Speed {
    private double Value;
    private String Unit;
    private double UnitType;


    public double getValue() {
        return Value;
    }

    public void setValue(double Value) {
        this.Value = Value;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public double getUnitType() {
        return UnitType;
    }

    public void setUnitType(double UnitType) {
        this.UnitType = UnitType;
    }
}

class Wind {
    Speed Speed;
    Direction Direction;


    public Speed getSpeed() {
        return Speed;
    }

    public void setSpeed(Speed Speed) {
        this.Speed = Speed;
    }

    public Direction getDirection() {
        return Direction;
    }

    public void setDirection(Direction Direction) {
        this.Direction = Direction;
    }
}

class DewPoint {
    private double Value;
    private String Unit;
    private double UnitType;

    public double getValue() {
        return Value;
    }

    public void setValue(double Value) {
        this.Value = Value;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public double getUnitType() {
        return UnitType;
    }

    public void setUnitType(double UnitType) {
        this.UnitType = UnitType;
    }
}

class WetBulbTemperature {
    private double Value;
    private String Unit;
    private double UnitType;


    public double getValue() {
        return Value;
    }

    public void setValue(double Value) {
        this.Value = Value;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public double getUnitType() {
        return UnitType;
    }

    public void setUnitType(double UnitType) {
        this.UnitType = UnitType;
    }
}

class RealFeelTemperature {
    private double Value;
    private String Unit;
    private double UnitType;


    public double getValue() {
        return Value;
    }

    public void setValue(double Value) {
        this.Value = Value;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public double getUnitType() {
        return UnitType;
    }

    public void setUnitType(double UnitType) {
        this.UnitType = UnitType;
    }
}

class Temperature {
    private double Value;
    private String Unit;
    private double UnitType;


    public double getValue() {
        return Value;
    }

    public void setValue(double Value) {
        this.Value = Value;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public double getUnitType() {
        return UnitType;
    }

    public void setUnitType(double UnitType) {
        this.UnitType = UnitType;
    }
}
