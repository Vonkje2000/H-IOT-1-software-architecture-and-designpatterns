package Cloud.InfrastructureLayer.Mqtt.MqttCommon;

public interface MqttClientServer {
   public void publish  (String topic, String message);
   public void subscribe(String topic, MqttCallback C);
   public void cleanup  ();
}