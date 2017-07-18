package tarc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tarc.App;
import tarc.model.Light;
import tarc.model.Status;
import tarc.model.Temp;
import tarc.model.Tilt;

import java.util.List;

@Component
public class StatusService {

    @Autowired
    TempService tempService;

    @Autowired
    LightService lightService;

    @Autowired
    TiltService tiltService;

    public Status getStatus() {

        String curStatus = "Normal";

        Status status = new Status();

        List<Temp> tempList = tempService.getTemp();
        if (tempList.size() > 0) {
            status.setTemp(tempList.get(tempList.size() - 1).getTemperature());
        }

        List<Light> lightList = lightService.getLight();
        if (lightList.size() > 0) {
            status.setLight(lightList.get(lightList.size() - 1).getLight());
        }

        List<Tilt> tiltList = tiltService.getTilt();
        boolean earthQuake = false;
        for (int i = 0; i < tiltList.size(); i++) {
            if (tiltList.get(i).isEarthquake()) {
                earthQuake = true;
                break;
            }
        }
        status.setEarthquake(earthQuake);

        status.setNumberTweetsEnchente(App.tweetList.size());

        // Definition of Status
        status.setStatus(curStatus);

        if ((status.getNumberTweetsEnchente() > 5) &&
                (status.getLight() >= 0 && status.getLight() <= 10) &&
                (status.getTemp() >= 20 && status.getTemp() <= 50) &&
                (!status.isEarthquake())) {
            status.setStatus("Temporal");
        }

        if ((!status.isEarthquake()) && (status.getNumberTweetsEnchente() > 5)) {
            status.setStatus("Enchente");
        }

        if (status.isEarthquake()) {
            status.setStatus("Terremoto");
        }

        return status;
    }
}
