package tarc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tarc.model.Temp;
import tarc.service.TempService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class TempController {

    @Autowired
    TempService tempService;

    @RequestMapping("/temp")
    public List<Temp> temp(HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Origin", "*");
        return tempService.getTemp();
    }
}
