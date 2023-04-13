package Tablet._3_DomainImplLayer;

import Tablet._4_DomainLayer.GPSTracker;

public class GPSTrackerImpl implements GPSTracker {
    public int getX() { return (int) (Math.random()*100+1); }
    public int getY() { return (int) (Math.random()*100+1); }
}
