package tarc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tarc.model.Light;
import tarc.service.LightService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class LightController {

    @Autowired
    LightService lightService;

    @RequestMapping("/light")
    public List<Light> light(HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Origin", "*");
        return lightService.getLight();
    }
}
