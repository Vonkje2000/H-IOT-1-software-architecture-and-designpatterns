package Cloud.InfrastructureLayer.Mqtt.MqttMock;

// =======================================
//
// Auteur: 2019 Pascal van den Bosch
//
// =======================================

import Cloud.InfrastructureLayer.Mqtt.MqttCommon.MqttCallback;
import Cloud.InfrastructureLayer.Mqtt.MqttCommon.MqttClientServer;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MqttClientMock implements MqttClientServer {
    private int cid;
    private MqttBrokerMock   B;
    private Map<String, MqttCallback> CB = new HashMap<>();
    public MqttClientMock (MqttBrokerMock b) { 
        B=b; 
        cid=new Random().nextInt(); 
    }
    //public void connect     () {  }
    //public void disconnect  () {  }    
    public void publish   (String topic, String message ) { B.publish(topic, message);                    }
    public void subscribe (String topic, MqttCallback C)  { B.subscribe(this, topic); CB.put(topic, C);   }
    public void callback  (String topicwildcard, String topic, String message)  { 
        CB.get(topicwildcard).messageArrived(topic, message); 
    }
    public void cleanup() { }
}
