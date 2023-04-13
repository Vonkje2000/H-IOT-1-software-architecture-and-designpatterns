package Tablet._2_ApplicationLayer;

import Tablet._3_DomainImplLayer.GPSTrackerImpl;
import Tablet._4_DomainLayer.Display;
import Tablet._4_DomainLayer.GPSTracker;

public class UC_DisplayAttracties {
    
    private GPSTracker T;
    private Display    D;
    private int        Radius;
    private boolean    Horeca;
    private final int  Tid;   
    DisplayAttractiesServer S;
            
    public UC_DisplayAttracties (int id, DisplayAttractiesServer as, Display d) { 
        this.Tid = id;
        Radius = 100;
        Horeca = true;
        D = d;
        T = new GPSTrackerImpl();
        S = as;
    }
    public void ga () {
        S.start(Tid);
        while (S.next()) {                 
            S.getInfo(Tid, T.getX(), T.getY(), Radius, Horeca);
            String xxx = S.infoString();
            D.display(""+Tid, xxx);
        }
        S.stop();
    }
    public void stelInRadius (int R) {
        Radius = R;
    }
    public void stelInHoreca (boolean H) {
        Horeca = H;
    }
}
