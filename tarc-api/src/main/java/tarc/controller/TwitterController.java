package tarc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tarc.model.Tweet;
import tarc.service.TwitterService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class TwitterController {

    @Autowired
    TwitterService twitterService;

    @RequestMapping("/twitter")
    public List<Tweet> twitter(HttpServletResponse response) {

        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Origin", "*");
        return twitterService.getTweets();
    }
}
