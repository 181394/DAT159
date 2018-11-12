package devices;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Display implements MqttCallback {
    public static void main(String[] args) throws MqttException {

        new Display();

    }

    public Display ()throws MqttException  {
        String topic = "Heat";
        int qos = 1; // 1 - This client will acknowledge to the Device Gateway that messages are
        // received
        String broker = "tcp://m20.cloudmqtt.com:14470";
        String clientId = "MQTT_Display";
        String username = "qknkzqhj";
        String password = "Mx22MlpCx2K-";


        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName(username);
        connOpts.setPassword(password.toCharArray());

        System.out.println("Connecting to broker: " + broker);

        MqttClient client = new MqttClient(broker, clientId, new MemoryPersistence());
        client.setCallback(this);
        client.connect(connOpts);
        System.out.println("Connected");

        client.subscribe(topic, qos);
        System.out.println("Subscribed to message");




    }

    /**
     * @see MqttCallback#connectionLost(Throwable)
     */
    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost because: " + cause);
        System.exit(1);

    }

    /**
     * @see MqttCallback#messageArrived(String, MqttMessage)
     */
    public void messageArrived(String topic, MqttMessage message) throws Exception {

        String msg = (String.format("[%s] %s", topic, new String(message.getPayload())).substring(7));
        this.write(msg);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }



    public void write(String message) {
        System.out.println("DISPLAY: " + message);
    }

}