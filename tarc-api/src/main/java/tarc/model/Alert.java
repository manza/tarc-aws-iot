package tarc.model;

public class Alert {

    private String temperature;
    private String humidity;
    private boolean earthquake;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public boolean isEarthquake() {
        return earthquake;
    }

    public void setEarthquake(boolean earthquake) {
        this.earthquake = earthquake;
    }
}
