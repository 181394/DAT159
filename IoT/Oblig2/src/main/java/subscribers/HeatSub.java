package subscribers;

import controllers.Room;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class HeatSub implements MqttCallback {

    private Room room;


    public HeatSub(Room room)throws MqttException  {
        this.room = room;

            String topic = "Heat";
            int qos = 1; // 1 - This client will acknowledge to the Device Gateway that messages are
            // received
            String broker = "tcp://m20.cloudmqtt.com:14470";
            String clientId = "MQTT_heat_SUB";
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

    public void write () {
        room.toggleHeater();
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
        if(room.getHeaterState() !=Boolean.parseBoolean(String.format("[%s] %s", topic, new String(message.getPayload())).substring(7)))
            this.write();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}