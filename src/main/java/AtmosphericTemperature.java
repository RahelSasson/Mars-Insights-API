
public class AtmosphericTemperature {
    private double av; // Average temperature
    private int ct; // Count
    private double mn; // Minimum temperature
    private double mx; // Maximum temperature

    // Getter and setter methods for all the fields

    public void setCt(int ct) {
        this.ct = ct;
    }

    public void setAv(double av) {
        this.av = av;
    }

    public void setMn(double mn) {
        this.mn = mn;
    }

    public void setMx(double mx) {
        this.mx = mx;
    }

    public double getAv() {
        return av;
    }

    public double getMn() {
        return mn;
    }

    public double getMx() {
        return mx;
    }

    public int getCt() {
        return ct;
    }
}