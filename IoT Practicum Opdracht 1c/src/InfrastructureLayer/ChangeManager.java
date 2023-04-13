package InfrastructureLayer;

// #########################################################
// De klasse ChangeManager:
// #########################################################
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
public class ChangeManager {
    private static Map<Observer,Subject> map = new HashMap<>();
    public static Subject get (Observer x) { return map.get(x); }
    public void register (Subject S, Observer x) { map.put(x,S); }
    public void unRegister (Observer x) { map.remove(x); }
    public List<Observer> getObservers(Subject S) {
        List<Observer> L = new ArrayList<>();
        Set<Entry<Observer,Subject>> hashSet=map.entrySet();
        for (Entry e : hashSet)
            if (e.getValue()==S)
                L.add((Observer)e.getKey());
        return L;
    }
    public void notifyObservers (Subject S) {
        Set<Entry<Observer,Subject>> hashSet=map.entrySet();
        for (Entry e : hashSet)
            if (e.getValue()==S)
                ((Observer)e.getKey()).update();
    }
}
