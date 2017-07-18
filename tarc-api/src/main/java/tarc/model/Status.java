package tarc.model;

public class Status {

    private double temp;
    private double light;
    private boolean earthquake;
    private int numberTweetsEnchente;
    private String status;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getLight() {
        return light;
    }

    public void setLight(double light) {
        this.light = light;
    }

    public boolean isEarthquake() {
        return earthquake;
    }

    public void setEarthquake(boolean earthquake) {
        this.earthquake = earthquake;
    }

    public int getNumberTweetsEnchente() {
        return numberTweetsEnchente;
    }

    public void setNumberTweetsEnchente(int numberTweetsEnchente) {
        this.numberTweetsEnchente = numberTweetsEnchente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
