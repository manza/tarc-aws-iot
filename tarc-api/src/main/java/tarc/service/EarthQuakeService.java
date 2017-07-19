package tarc.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tarc.App;
import tarc.model.Tilt;

import java.util.List;

@Component
public class EarthQuakeService {

    public List<Tilt> getTilt() {

        return App.tiltList;
    }

    @Scheduled(fixedRate = 30000)
    public void cacheTweets() {

        boolean earthQuake = false;
        for (int i = 0; i < App.tiltList.size(); i++) {
            if (App.tiltList.get(i).isEarthquake()) {
                App.tiltList.remove(App.tiltList.get(i));
                break;
            }
        }
    }
}
