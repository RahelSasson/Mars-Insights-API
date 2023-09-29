public class WindSpeed {
    private double av; // Average wind speed
    private int ct; // Count
    private double mn; // Minimum wind speed
    private double mx; // Maximum wind speed

    // Getter and setter methods for all the fields


    public void setMx(double mx) {
        this.mx = mx;
    }

    public void setMn(double mn) {
        this.mn = mn;
    }

    public void setAv(double av) {
        this.av = av;
    }

    public void setCt(int ct) {
        this.ct = ct;
    }

    public int getCt() {
        return ct;
    }

    public double getMx() {
        return mx;
    }

    public double getMn() {
        return mn;
    }

    public double getAv() {
        return av;
    }
}
