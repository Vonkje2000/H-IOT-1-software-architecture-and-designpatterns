package Tablet.InfrastructureLayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public interface Subject {

    static Map<Observer,Subject> map = new HashMap<>();
    static Subject get (Observer x) { return map.get(x); }

    default void insertObserver (Observer x) { map.put(x,this);    }
    default void removeObserver (Observer x) { map.remove(x); }
    
    default void notifyObservers () {
        Set<Entry<Observer,Subject>> hashSet=map.entrySet();
        for (Entry e : hashSet)
            if (e.getValue()==this)
                ((Observer)e.getKey()).update();
    }
    static void notifyAllObservers () {
        Set<Entry<Observer,Subject>> hashSet=map.entrySet();
        for (Entry e : hashSet)
            ((Observer)e.getKey()).update();
    }
}
