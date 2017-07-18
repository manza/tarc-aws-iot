package tarc.service;

import org.springframework.stereotype.Component;
import tarc.App;
import tarc.model.Tilt;

import java.util.List;

@Component
public class TiltService {

    public List<Tilt> getTilt() {

        return App.tiltList;
    }
}
