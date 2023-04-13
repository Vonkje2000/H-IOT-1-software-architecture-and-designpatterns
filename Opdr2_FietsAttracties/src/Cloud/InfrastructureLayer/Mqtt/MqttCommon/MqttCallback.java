package Cloud.InfrastructureLayer.Mqtt.MqttCommon;

public interface MqttCallback {

  public void messageArrived (String topic, String message);
}