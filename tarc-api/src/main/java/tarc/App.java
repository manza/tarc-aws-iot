package tarc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tarc.model.Light;
import tarc.model.Temp;
import tarc.model.Tilt;
import tarc.model.Tweet;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages = {"tarc"})
@EnableAutoConfiguration
@EnableScheduling
public class App {

    public static List<Temp> tempList = new ArrayList<>();
    public static List<Light> lightList = new ArrayList<>();
    public static List<Tilt> tiltList = new ArrayList<>();
    public static List<Tweet> tweetList = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
