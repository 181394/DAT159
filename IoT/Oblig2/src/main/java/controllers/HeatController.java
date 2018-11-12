package controllers;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class HeatController implements MqttCallback, Runnable{

    private String subtopic = "Temp";
    private String pubtopic = "Heat";
    private int qos = 1;
    private String broker = "tcp://m20.cloudmqtt.com:14470";
    private String subclientId = "MQTT_Temp_sub";
    private String pubclientId = "MQTT_heat_pub";
    private String username = "qknkzqhj";
    private String password = "Mx22MlpCx2K-";

    private MqttClient publisherClient;
    private MqttClient subscriberClient;

    private boolean heatstate;



    public HeatController()throws MqttException{
        heatstate = false;
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName(username);
        connOpts.setPassword(password.toCharArray());

        System.out.println("Connecting to broker: " + broker);

        subscriberClient = new MqttClient(broker, subclientId, new MemoryPersistence());
        subscriberClient.setCallback(this);
        subscriberClient.connect(connOpts);
        System.out.println("Connected");

        subscriberClient.subscribe(subtopic, qos);
        System.out.println("Subscribed to message");

    }

    private void publish() throws MqttPersistenceException, MqttException, InterruptedException {

        for (int i = 0; i < 20; i++) {

            String state = String.valueOf(heatstate);

            System.out.println("Publishing message: " + state);

            MqttMessage message = new MqttMessage(state.getBytes());
            message.setQos(qos);

            publisherClient.publish(pubtopic, message);

            Thread.sleep(5000);
        }

    }

    private void connect() {

        MemoryPersistence persistence = new MemoryPersistence();

        try {
            publisherClient = new MqttClient(broker, pubclientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());
            System.out.println("Connecting to broker: " + broker);
            publisherClient.connect(connOpts);
            System.out.println("Connected");

        } catch (MqttException e) {
            System.out.println("reason " + e.getReasonCode());
            System.out.println("msg " + e.getMessage());
            System.out.println("loc " + e.getLocalizedMessage());
            System.out.println("cause " + e.getCause());
            System.out.println("excep " + e);
            e.printStackTrace();
        }
    }


    private void setHeatstate(boolean state){heatstate = state;}

    @Override
    public void run() {

        try {

            System.out.println("State publisher running");

            connect();

            publish();

            disconnect();

            System.out.println("Sensor publisher stopping");

        } catch (Exception ex) {
            System.out.println("Sensor publisher: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    /**
     * @see MqttCallback#connectionLost(Throwable)
     */
    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost because: " + cause);
        System.exit(1);

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception  {
        double heat = Double.parseDouble(String.format("[%s] %s", subtopic, new String(message.getPayload())).substring(7));
        if (heat<15.0)setHeatstate(true);
        else if (heat>25.0)setHeatstate(false);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

    private void disconnect() throws MqttException {

        publisherClient.disconnect();

    }

    public static void main(String[] args) throws MqttException, InterruptedException {
        Thread th = new Thread(new HeatController());
        th.start();
        th.join();
    }

}
