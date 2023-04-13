package Tablet._1_InterfaceLayer;

import Cloud.InfrastructureLayer.Mqtt.MqttCommon.MqttCallback;
import Tablet._3_DomainImplLayer.DisplayImpl;
import Tablet._3_DomainImplLayer.DisplayServer;
import Tablet._4_DomainLayer.Display;
import Tablet._2_ApplicationLayer.DisplayAttractiesServer;
import java.util.logging.Level;
import java.util.logging.Logger;
import Cloud.InfrastructureLayer.Mqtt.MqttCommon.MqttClientServer;
import java.util.Random;

public class DisplayAttractiesServerImpl implements DisplayAttractiesServer {
    
    int Tid = 0;
    int aantal = 0;
    String Message = "";

    Display D;
    
    private MqttClientServer C;
    
    public DisplayAttractiesServerImpl (int id, DisplayServer s, MqttClientServer C) {
        this.C = C;   
        Tid = id;
        D = new DisplayImpl(s);
        C.subscribe("Backend/ReturnInfoString/" + Tid, new MqttCallback() {
            public void messageArrived(String topic, String message) {
                Logger.getGlobal().log(Level.FINE, "Set message = " + message);
                Message = message;
            }
        });
    }
    public void start (int Tid) {
        C.publish("Tablet/Start/" + Tid,"");
    }
    public boolean next () {
        try { Thread.sleep((new Random()).nextInt(1000) + 1000); } catch (InterruptedException e) { }
        aantal++;
        return aantal<11;
    }
    public void stop() {
        C.publish("Tablet/Stop/" + Tid,"");
    }
    public void getInfo(int tid, int x, int y, int radius, boolean horeca) {
        Message = ""; // Belangrijk!
        String HorecaStr;
        if (horeca) HorecaStr = "1";
        else  HorecaStr = "0";
        C.publish("Tablet/GetInfo/" + tid, x + "," + y + "," + radius + "," + HorecaStr);
    }

    public String infoString () {
        while (Message.equals(""))
            try {Thread.sleep(10);}
            catch (InterruptedException ex) {}
        return Message;        
    }
    public void cleanup() {
        C.cleanup();   
    }
}

/* Alternatief met lambda expressies:

    public DisplayAttractiesServerImpl (DisplayServer s, MqttClientServer C) {
        this.C = C;
        D = new DisplayImpl(s);
        C.subscribe("Backend/ReturnInfoString" + Tid, (String message) -> {
            Logger.getGlobal().log(Level.FINE, "Set message = " + message);
            Message = message;
        });
    }


    Dan kan ook de volgende Interface weg uit de package MqttCommon:

    public interface MqttCallback {
        public void messageArrived (String message);
    }

    Dan weer wel Functional interfaces gebruiken

*/