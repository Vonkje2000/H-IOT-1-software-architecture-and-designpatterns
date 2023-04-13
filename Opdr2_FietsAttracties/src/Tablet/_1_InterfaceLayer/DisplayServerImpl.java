package Tablet._1_InterfaceLayer;

import Tablet._3_DomainImplLayer.DisplayServer;

public class DisplayServerImpl implements DisplayServer {
    
    public void show (String header,String info) {
        System.out.println("Fiets "+header+ ": ----------------------------\n"+info); 
    }
}
