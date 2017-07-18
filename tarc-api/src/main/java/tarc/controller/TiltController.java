package tarc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tarc.model.Tilt;
import tarc.service.TiltService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class TiltController {

    @Autowired
    TiltService tiltService;

    @RequestMapping("/tilt")
    public List<Tilt> tilt(HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Origin", "*");
        return tiltService.getTilt();
    }
}
