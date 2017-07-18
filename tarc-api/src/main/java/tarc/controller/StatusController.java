package tarc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tarc.model.Status;
import tarc.service.StatusService;

import javax.servlet.http.HttpServletResponse;

@RestController
public class StatusController {

    @Autowired
    StatusService statusService;

    @RequestMapping("/status")
    public Status status(HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Origin", "*");
        return statusService.getStatus();
    }
}
