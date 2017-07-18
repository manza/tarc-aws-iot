package tarc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tarc.model.Dummy;
import tarc.service.DummyService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class DummyController {

    @Autowired
    DummyService dummyService;

    @RequestMapping("/dummy")
    public List<Dummy> dummy(HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Origin", "*");
        return dummyService.getDummy();
    }
}
