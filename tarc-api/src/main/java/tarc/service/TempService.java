package tarc.service;

import org.springframework.stereotype.Component;
import tarc.App;
import tarc.model.Temp;

import java.util.List;

@Component
public class TempService {

    // Authentication
    public List<Temp> getTemp() {

        return App.tempList;
    }
}
