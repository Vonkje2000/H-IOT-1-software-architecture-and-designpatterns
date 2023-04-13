package Cloud.InfrastructureLayer.Mqtt.MqttMock;

// =======================================
//
// Auteur: 2019 Pascal van den Bosch
//
// =======================================

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MqttBrokerMock {
    private static Map<String, List<MqttClientMock>> subscribed = new HashMap<>();
    
    public static final MqttBrokerMock Broker = new MqttBrokerMock();
    private MqttBrokerMock () {}

    public void subscribe (MqttClientMock sender, String topic) { 
        if (!subscribed.containsKey(topic))
             subscribed.put(topic, new LinkedList<>());
        subscribed.get(topic).add(sender); 
    }
    public void unSubscribe (String topic) {
        subscribed.remove(topic);
    }
    public void publish (String topic, String message) {  
        for (String topicwildcard : subscribed.keySet()) {
            Matcher match = Pattern.compile(topicwildcard.replace("+", "[^\\\\]*")).matcher(topic);
            if (match.find())
                for (MqttClientMock m : subscribed.get(topicwildcard))
                    m.callback(topicwildcard, topic, message);
        }   
    }
}
