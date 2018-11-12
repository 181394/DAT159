package devices;

import controllers.HeatController;
import controllers.Room;
import org.eclipse.paho.client.mqttv3.MqttException;
import publishers.MQTTPubTemperature;

public class RoomDevice {

    public static void main(String[] args) throws MqttException {

        Room room = new Room(20);
        TemperatureSensor sensor = new TemperatureSensor(room);
        Heating heating = new Heating(room);

        HeatController controller = new HeatController();
        MQTTPubTemperature sensorpub = new MQTTPubTemperature(sensor);

        try {
            Thread temppublisher = new Thread(sensorpub);
            Thread control = new Thread(controller);
            // TODO: add heating subscriber running in its own thread
            control.start();
            temppublisher.start();

            control.join();
            temppublisher.join();

        } catch (Exception ex) {

            System.out.println("devices.RoomDevice: " + ex.getMessage());
            ex.printStackTrace();
        }



    }

}