package tarc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tarc.model.Tilt;
import tarc.service.EarthQuakeService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class EarthQuakeController {

    @Autowired
    EarthQuakeService earthQuakeService;

    @RequestMapping("/earthquake")
    public List<Tilt> tilt(HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Origin", "*");
        return earthQuakeService.getTilt();
    }
}
