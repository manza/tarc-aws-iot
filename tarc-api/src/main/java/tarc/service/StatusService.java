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
    EarthQuakeService earthQuakeService;

    public Status getStatus() {

        double tempLast = 0.0;
        double tempPrevLast = 0.0;
        boolean alarmTemp = false;

        double lightLast = 0.0;
        double lightPrevLast = 0.0;
        boolean alarmLight = false;

        Status status = new Status();

        String curStatus = "Normal";

        List<Temp> tempList = tempService.getTemp();
        List<Light> lightList = lightService.getLight();
        List<Tilt> tiltList = earthQuakeService.getTilt();

        if ((tempList.size() >= 2) && (lightList.size() >= 2) && (tiltList.size() > 0)) {

            status.setNumberTweetsEnchente(App.tweetList.size());
            status.setTemp(tempList.get(tempList.size() - 1).getTemperature());
            status.setLight(lightList.get(lightList.size() - 1).getLight());

            // Manage Status
            lightLast = lightList.get(lightList.size() - 1).getLight();
            lightPrevLast = lightList.get(lightList.size() - 2).getLight();
            alarmLight = Math.abs(lightLast - lightPrevLast) >= 200;

            tempLast = tempList.get(tempList.size() - 1).getTemperature();
            tempPrevLast = tempList.get(tempList.size() - 2).getTemperature();
            ;
            alarmTemp = Math.abs(tempLast - tempPrevLast) >= 0.5;

            boolean earthQuake = false;
            for (int i = 0; i < tiltList.size(); i++) {
                if (tiltList.get(i).isEarthquake()) {
                    earthQuake = true;
                    break;
                }
            }
            status.setEarthquake(earthQuake);

            if (alarmLight && alarmTemp) {
                curStatus = "Temporal";
            }

            if (alarmLight && alarmTemp && App.tweetList.size() >= 2) {
                curStatus = "Temporal - Enchente";
            }

            if (!alarmLight && !alarmTemp && App.tweetList.size() >= 2) {
                curStatus = "Enchente";
            }

            if (status.isEarthquake()) {
                curStatus = "Terremoto";
            }

            // Definition of Status
            status.setStatus(curStatus);
        }

        return status;
    }
}
