package BackEnd._1_InterfaceLayer;

import BackEnd._2_ApplicationLayer.UC_GeefInfoAttracties;
import Cloud.InfrastructureLayer.Mqtt.MqttCommon.MqttCallback;
import BackEnd._2_ApplicationLayer.GeefInfoAttractiesServer;
import Cloud.InfrastructureLayer.Mqtt.MqttCommon.MqttClientServer;

public class GeefInfoAttractiesServerImpl implements GeefInfoAttractiesServer {

    private int                   Tid = 0;
    private UC_GeefInfoAttracties AIUC;
    private MqttClientServer      C;
    
    private int getid(String topic)  {
        String[] sp = topic.split("/");
        return Integer.parseInt(sp[sp.length - 1]);
    }
    
    public GeefInfoAttractiesServerImpl (MqttClientServer C) {
        this.C = C;
        C.subscribe("Tablet/Start/+", new MqttCallback() {
            public void messageArrived(String topic, String messageTablet){
                AIUC.start(getid(topic));
        }});
        C.subscribe("Tablet/GetInfo/+", new MqttCallback() {
            public void messageArrived(String topic, String messageTablet){
                AIUC.getInfo(getid(topic), messageTablet);
        }});
        C.subscribe("Tablet/Stop/+", new MqttCallback() {
            public void messageArrived(String topic, String messageTablet){
                AIUC.stop(getid(topic));
        }});
    }
    
    public void setAiuc (UC_GeefInfoAttracties aiuc) {
        AIUC = aiuc;
    }

    public void setId (int tid) {
        Tid = tid;
    }
    public void getInfo(String infoString) {
        C.publish("Backend/ReturnInfoString/" + Tid, infoString);
    }
    
    public void cleanup() {
        C.cleanup();
    }
}

/* Alternatief met lambda expressies:

    public GeefInfoAttractiesServerImpl (MqttClientServer C) {
        this.C = C;
        C.subscribe("Tablet/Start", (String messageTablet) -> {
            AIUC.start(Tid);
        });
        C.subscribe("Tablet/GetInfo", (String messageTablet) -> {
            AIUC.getInfo(messageTablet);
        });
        C.subscribe("Tablet/Stop", (String messageTablet) -> {
            AIUC.stop();
        });


    Dan kan ook de volgende Interface weg uit de package MqttCommon:

    public interface MqttCallback {
        public void messageArrived (String message);
    }

    Dan weer wel Functional interfaces gebruiken

*/