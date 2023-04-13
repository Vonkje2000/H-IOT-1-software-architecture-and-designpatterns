package Cloud.Main;

import BackEnd._1_InterfaceLayer.GeefInfoAttractiesServerImpl;
import BackEnd._1_InterfaceLayer.RegistreerAttractiesServerImpl;
import BackEnd._1_InterfaceLayer.ToonFietstochtenServerImpl;
import BackEnd._2_ApplicationLayer.UC_GeefInfoAttracties;
import BackEnd._2_ApplicationLayer.UC_RegistreerAttracties;
import BackEnd._2_ApplicationLayer.UC_ToonFietstochten;
import Cloud.InfrastructureLayer.Mqtt.MqttCommon.MqttCallback;
import Cloud.InfrastructureLayer.Mqtt.MqttMock.MqttBrokerMock;
import Cloud.InfrastructureLayer.Mqtt.MqttPaho.MqttClient;
import Cloud.InfrastructureLayer.Mqtt.MqttMock.MqttClientMock;
import Tablet._1_InterfaceLayer.DisplayAttractiesServerImpl;
import Tablet._1_InterfaceLayer.DisplayServerImpl;
import Tablet._2_ApplicationLayer.UC_DisplayAttracties;
import Tablet._3_DomainImplLayer.DisplayImpl;
import Tablet._4_DomainLayer.Display;
import java.util.logging.Level;
import java.util.logging.Logger;
import Cloud.InfrastructureLayer.Mqtt.MqttCommon.MqttClientServer;
import static java.lang.Thread.MIN_PRIORITY;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

class ChildThread extends Thread 
{   private int Tid;
    public ChildThread (int tid) { Tid = tid; }
    public void run()  
    {   //=====================================================================
        // Mqtt Paho:
        MqttClientServer ClientTablet  = new MqttClient();
        // XOR: !!! -----------------------------------------------------------
        // Mqtt Mock:
        //MqttClientServer ClientTablet  = new MqttClientMock(MqttBrokerMock.Broker); 
        //=====================================================================
        
        DisplayServerImpl DSI = new DisplayServerImpl();
        Display           D   = new DisplayImpl(DSI);        
        DisplayAttractiesServerImpl ADS = new DisplayAttractiesServerImpl(Tid, DSI, ClientTablet);
        UC_DisplayAttracties      UCAD  = new UC_DisplayAttracties(Tid,ADS,D);
        UCAD.stelInRadius(60);
        UCAD.stelInHoreca(true);
        UCAD.ga();
        ADS.cleanup();
    } 
}

public class Main {
    
    public static void log () {
        Logger.getGlobal().setLevel(Level.OFF);
        Logger.getGlobal().addHandler(  new Handler () {
                public void publish(LogRecord record) { System.out.println(record.getMessage());  }
                public void flush() {                 }
                public void close() throws SecurityException {  }
        });
        MqttClient alles = new MqttClient();
        alles.subscribe("#", new MqttCallback() {
            public void messageArrived(String topic, String message) {
                Logger.getGlobal().log(Level.FINEST, "Alles ontvangt [" + topic + "] = " + message);
            }
        });
    }
    
    public static void main(String[] args) throws InterruptedException {
          
        log();

        //=====================================================================
        // Mqtt Paho:
        MqttClientServer ClientBackEnd = new MqttClient();
        // XOR: !!! -----------------------------------------------------------
        // Mqtt Mock:
        //MqttClientServer ClientBackEnd = new MqttClientMock(MqttBrokerMock.Broker); 
        //=====================================================================
        
        GeefInfoAttractiesServerImpl AIUCS = new GeefInfoAttractiesServerImpl(ClientBackEnd);
        UC_GeefInfoAttracties        AUC   = new UC_GeefInfoAttracties(AIUCS);
        AIUCS.setAiuc(AUC);
        
        RegistreerAttractiesServerImpl S = new RegistreerAttractiesServerImpl();
        UC_RegistreerAttracties      UCR = new UC_RegistreerAttracties(S);
        //UCR.registreerAttractie();
        
        ToonFietstochtenServerImpl TFS = new ToonFietstochtenServerImpl();
        UC_ToonFietstochten      UCTFT = new UC_ToonFietstochten(TFS);
        //UCTFT.toon();
        
        for (int fiets = 0; fiets < 5; fiets++) {
            ChildThread T = new ChildThread(fiets); 
            T.setPriority(MIN_PRIORITY);
            T.start(); 
            Thread.sleep(1000);
        }
        
        // AIUCS.cleanup(); Kan hierdoor vroegtijdig stoppen!!!!!!
        // De backend moet "oneindig" lang runnen.....
    }

}
