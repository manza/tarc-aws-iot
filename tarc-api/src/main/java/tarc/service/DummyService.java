package tarc.service;

import org.springframework.stereotype.Component;
import tarc.model.Dummy;

import java.util.ArrayList;
import java.util.List;

@Component
public class DummyService {

    public List<Dummy> getDummy() {

        List<Dummy> dummyList = new ArrayList<>();

        Dummy dummy1 = new Dummy();
        dummy1.setName("Scott");
        dummy1.setAge(12);

        Dummy dummy2 = new Dummy();
        dummy2.setName("Amanda");
        dummy2.setAge(33);

        Dummy dummy3 = new Dummy();
        dummy3.setName("Bonn");
        dummy3.setAge(45);

        dummyList.add(dummy1);
        dummyList.add(dummy2);
        dummyList.add(dummy3);

        return dummyList;
    }
}
