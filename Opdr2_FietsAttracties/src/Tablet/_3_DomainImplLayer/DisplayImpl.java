package Tablet._3_DomainImplLayer;

import Tablet._4_DomainLayer.Display;

public class DisplayImpl implements Display {
    private DisplayServer S;
    
    public DisplayImpl (DisplayServer s) { S=s; }
    public void display(String header, String info) { S.show(header,info); }
}
