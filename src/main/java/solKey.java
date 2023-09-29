import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class solKey {

    @JsonProperty("AT")
    private AtmosphericTemperature atmosphericTemperature;

    @JsonProperty("First_UTC")
    private String firstUtc;

    @JsonProperty("HWS")
    private WindSpeed windSpeed;

    @JsonProperty("Last_UTC")
    private String lastUtc;

    @JsonProperty("Month_ordinal")
    private int monthOrdinal;

    @JsonProperty("Northern_season")
    private String northernSeason;

    @JsonProperty("PRE")
    private Pressure pressure;

    @JsonProperty("Season")
    private String season;

    @JsonProperty("Southern_season")
    private String southernSeason;

    @JsonProperty("WD")
    private WindDirections windDirections;

    // Getter and setter methods for all the fields
    public AtmosphericTemperature getAtmosphericTemperature() {
        return atmosphericTemperature;
    }

    public void setAtmosphericTemperature(AtmosphericTemperature atmosphericTemperature) {
        this.atmosphericTemperature = atmosphericTemperature;
    }

    public String getFirstUtc() {
        return firstUtc;
    }

    public void setFirstUtc(String firstUtc) {
        this.firstUtc = firstUtc;
    }

    public WindSpeed getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(WindSpeed windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getLastUtc() {
        return lastUtc;
    }

    public void setLastUtc(String lastUtc) {
        this.lastUtc = lastUtc;
    }

    public int getMonthOrdinal() {
        return monthOrdinal;
    }

    public void setMonthOrdinal(int monthOrdinal) {
        this.monthOrdinal = monthOrdinal;
    }

    public String getNorthernSeason() {
        return northernSeason;
    }

    public void setNorthernSeason(String northernSeason) {
        this.northernSeason = northernSeason;
    }

    public Pressure getPressure() {
        return pressure;
    }

    public void setPressure(Pressure pressure) {
        this.pressure = pressure;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getSouthernSeason() {
        return southernSeason;
    }

    public void setSouthernSeason(String southernSeason) {
        this.southernSeason = southernSeason;
    }

    public WindDirections getWindDirections() {
        return windDirections;
    }

    public void setWindDirections(WindDirections windDirections) {
        this.windDirections = windDirections;
    }

}



