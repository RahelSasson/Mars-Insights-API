import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WindDirections {
    @JsonProperty("0")
    private WindDirection wind0;

    @JsonProperty("1")
    private WindDirection wind1;

    // Include fields for other directions (up to "15") or use a Map if the number of directions varies.

    @JsonProperty("most_common")
    private WindDirection mostCommonDirection;

    // Getter and setter methods for all the fields


    public void setMostCommonDirection(WindDirection mostCommonDirection) {
        this.mostCommonDirection = mostCommonDirection;
    }

    public void setWind0(WindDirection wind0) {
        this.wind0 = wind0;
    }

    public void setWind1(WindDirection wind1) {
        this.wind1 = wind1;
    }

    public WindDirection getMostCommonDirection() {
        return mostCommonDirection;
    }

    public WindDirection getWind0() {
        return wind0;
    }

    public WindDirection getWind1() {
        return wind1;
    }

}