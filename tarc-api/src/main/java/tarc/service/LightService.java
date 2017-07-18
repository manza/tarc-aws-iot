package tarc.service;

import org.springframework.stereotype.Component;
import tarc.App;
import tarc.model.Light;

import java.util.List;

@Component
public class LightService {

    public List<Light> getLight() {

        return App.lightList;
    }
}
