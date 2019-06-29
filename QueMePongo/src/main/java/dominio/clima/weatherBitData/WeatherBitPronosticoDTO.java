package dominio.clima.weatherBitData;

import dominio.clima.Pronostico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WeatherBitPronosticoDTO {
    private List<Data> data;
    private String city_name;
    private String lon;
    private String timezone;
    private String lat;
    private String country_code;
    private String state_code;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }
}

class Data {
    Weather Weather;
    private String wind_cdir;
    private double rh;
    private String pod;
    private String timestamp_utc;
    private double pres;
    private double solar_rad;
    private double ozone;
    private double wind_gust_spd;
    private String timestamp_local;
    private double snow_depth;
    private double clouds;
    private double ts;
    private double wind_spd;
    private double pop;
    private String wind_cdir_full;
    private double slp;
    private double dni;
    private double dewpt;
    private double snow;
    private double uv;
    private double wind_dir;
    private double clouds_hi;
    private double precip;
    private double vis;
    private double dhi;
    private double app_temp;
    private String datetime;
    private double temp;
    private double ghi;
    private double clouds_mid;
    private double clouds_low;

    private DateTimeFormatter formato = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public String getWind_cdir() {
        return wind_cdir;
    }

    public void setWind_cdir(String wind_cdir) {
        this.wind_cdir = wind_cdir;
    }

    public double getRh() {
        return rh;
    }

    public void setRh(double rh) {
        this.rh = rh;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public String getTimestamp_utc() {
        return timestamp_utc;
    }

    public void setTimestamp_utc(String timestamp_utc) {
        this.timestamp_utc = timestamp_utc;
    }

    public double getPres() {
        return pres;
    }

    public void setPres(double pres) {
        this.pres = pres;
    }

    public double getSolar_rad() {
        return solar_rad;
    }

    public void setSolar_rad(double solar_rad) {
        this.solar_rad = solar_rad;
    }

    public double getOzone() {
        return ozone;
    }

    public void setOzone(double ozone) {
        this.ozone = ozone;
    }

    public Weather getWeather() {
        return Weather;
    }

    public void setWeather(Weather weather) {
        this.Weather = weather;
    }

    public double getWind_gust_spd() {
        return wind_gust_spd;
    }

    public void setWind_gust_spd(double wind_gust_spd) {
        this.wind_gust_spd = wind_gust_spd;
    }

    public String getTimestamp_local() {
        return timestamp_local;
    }

    public void setTimestamp_local(String timestamp_local) {
        this.timestamp_local = timestamp_local;
    }

    public double getSnow_depth() {
        return snow_depth;
    }

    public void setSnow_depth(double snow_depth) {
        this.snow_depth = snow_depth;
    }

    public double getClouds() {
        return clouds;
    }

    public void setClouds(double clouds) {
        this.clouds = clouds;
    }

    public double getTs() {
        return ts;
    }

    public void setTs(double ts) {
        this.ts = ts;
    }

    public double getWind_spd() {
        return wind_spd;
    }

    public void setWind_spd(double wind_spd) {
        this.wind_spd = wind_spd;
    }

    public double getPop() {
        return pop;
    }

    public void setPop(double pop) {
        this.pop = pop;
    }

    public String getWind_cdir_full() {
        return wind_cdir_full;
    }

    public void setWind_cdir_full(String wind_cdir_full) {
        this.wind_cdir_full = wind_cdir_full;
    }

    public double getSlp() {
        return slp;
    }

    public void setSlp(double slp) {
        this.slp = slp;
    }

    public double getDni() {
        return dni;
    }

    public void setDni(double dni) {
        this.dni = dni;
    }

    public double getDewpt() {
        return dewpt;
    }

    public void setDewpt(double dewpt) {
        this.dewpt = dewpt;
    }

    public double getSnow() {
        return snow;
    }

    public void setSnow(double snow) {
        this.snow = snow;
    }

    public double getUv() {
        return uv;
    }

    public void setUv(double uv) {
        this.uv = uv;
    }

    public double getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(double wind_dir) {
        this.wind_dir = wind_dir;
    }

    public double getClouds_hi() {
        return clouds_hi;
    }

    public void setClouds_hi(double clouds_hi) {
        this.clouds_hi = clouds_hi;
    }

    public double getPrecip() {
        return precip;
    }

    public void setPrecip(double precip) {
        this.precip = precip;
    }

    public double getVis() {
        return vis;
    }

    public void setVis(double vis) {
        this.vis = vis;
    }

    public double getDhi() {
        return dhi;
    }

    public void setDhi(double dhi) {
        this.dhi = dhi;
    }

    public double getApp_temp() {
        return app_temp;
    }

    public void setApp_temp(double app_temp) {
        this.app_temp = app_temp;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getGhi() {
        return ghi;
    }

    public void setGhi(double ghi) {
        this.ghi = ghi;
    }

    public double getClouds_mid() {
        return clouds_mid;
    }

    public void setClouds_mid(double clouds_mid) {
        this.clouds_mid = clouds_mid;
    }

    public double getClouds_low() {
        return clouds_low;
    }

    public void setClouds_low(double clouds_low) {
        this.clouds_low = clouds_low;
    }

    public Pronostico normalizar() {
        return new Pronostico(LocalDateTime.parse(timestamp_local,formato),temp);
    }
}

class Weather {
    private String icon;
    private double code;
    private String description;


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getCode() {
        return code;
    }

    public void setCode(double code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}