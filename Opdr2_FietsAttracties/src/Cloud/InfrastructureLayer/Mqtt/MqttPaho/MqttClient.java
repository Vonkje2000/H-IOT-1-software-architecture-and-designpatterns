package Cloud.InfrastructureLayer.Mqtt.MqttPaho;

// =======================================
//
// Auteur: 2019 Pascal van den Bosch
//
// =======================================

import Cloud.InfrastructureLayer.Mqtt.MqttCommon.MqttCallback;
import Cloud.InfrastructureLayer.Mqtt.MqttCommon.MqttClientServer;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;

public class MqttClient implements MqttClientServer {
    public static String geheimetopic = "geheimetopic" + (new Random()).nextInt();
    private final org.eclipse.paho.client.mqttv3.MqttClient sampleClient;
    private int cid;
    public MqttClient () {
        cid=new Random().nextInt(); 
        try {
            sampleClient = new org.eclipse.paho.client.mqttv3.MqttClient("tcp://test.mosquitto.org:1883", "clientId-HHSgeheim-" + cid);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            sampleClient.connect(connOpts);         
            Logger.getGlobal().log(Level.FINE, "Succesfully connected");
        } catch (MqttException ex) { 
            Logger.getGlobal().log(Level.FINE, "Error while connecting!");
            throw new RuntimeException(ex); }
    }
    //public void disconnect  () {  }    
    public void publish (String topic, String message ) {
        Logger.getGlobal().log(Level.FINE, "Publishing [" + topic + "] = " + message);
        try { sampleClient.publish(geheimetopic + "/" + topic, new MqttMessage(message.getBytes(StandardCharsets.UTF_8)));
        } catch (MqttException ex) { throw new RuntimeException(ex); }
    }
    public void subscribe (String topic,  MqttCallback C) {
        Logger.getGlobal().log(Level.FINE, "Subscribing [" + topic + "]");
        try { sampleClient.subscribe(geheimetopic + "/" + topic, new IMqttMessageListener(){
                public void messageArrived(String topic, MqttMessage mm)  {
                    Logger.getGlobal().log(Level.FINE, "Message arrived [" + topic + "] = " + new String(mm.getPayload(), StandardCharsets.UTF_8));
                    C.messageArrived(topic, new String(mm.getPayload(), StandardCharsets.UTF_8));
                }
            });
        } catch (MqttException ex) { throw new RuntimeException(ex); }
    }
    // er is geen unsubscribe?
    public void callback (String message) { throw new RuntimeException(); }
    public void cleanup () {
        try {
            sampleClient.disconnect();
        } catch (MqttException ex) {
            Logger.getLogger(MqttClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

