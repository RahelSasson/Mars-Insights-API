import com.fasterxml.jackson.annotation.JsonProperty;

public class WindDirection {
    @JsonProperty("compass_degrees")
    private double compassDegrees;

    @JsonProperty("compass_point")
    private String compassPoint;

    @JsonProperty("compass_right")
    private double compassRight;

    @JsonProperty("compass_up")
    private double compassUp;

    private int ct; // Count

    public void setCt(int ct) {
        this.ct = ct;
    }

    public void setCompassDegrees(double compassDegrees) {
        this.compassDegrees = compassDegrees;
    }

    public void setCompassPoint(String compassPoint) {
        this.compassPoint = compassPoint;
    }

    public void setCompassRight(double compassRight) {
        this.compassRight = compassRight;
    }

    public void setCompassUp(double compassUp) {
        this.compassUp = compassUp;
    }

    public int getCt() {
        return ct;
    }

    public double getCompassDegrees() {
        return compassDegrees;
    }

    public double getCompassRight() {
        return compassRight;
    }

    public double getCompassUp() {
        return compassUp;
    }

    public String getCompassPoint() {
        return compassPoint;
    }
}
