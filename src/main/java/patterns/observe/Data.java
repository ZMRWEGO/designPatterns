package patterns.observe;

import java.util.ArrayList;
import java.util.List;

public class Data {

    List<Observer> obs = new ArrayList<>();
    int val;

    public void setVal(int val) {
        this.val = val;
        for (Observer o:obs) {
            o.update(val);
        }
    }

    public void register(Observer observer) {
        obs.add(observer);
    }


}
