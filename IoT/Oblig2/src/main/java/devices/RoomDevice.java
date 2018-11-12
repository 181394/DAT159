package devices;

import controllers.Room;
import org.eclipse.paho.client.mqttv3.MqttException;
import publishers.TempPub;
import subscribers.HeatSub;

public class RoomDevice {

    public static void main(String[] args) throws MqttException {

        Room room = new Room(20);
        new HeatSub(room);

        TempPub roomTempPub = new TempPub(room);

        try {
            Thread tempPublisher = new Thread(roomTempPub);
            tempPublisher.start();

            tempPublisher.join();

        } catch (Exception ex) {

            System.out.println("devices.RoomDevice: " + ex.getMessage());
            ex.printStackTrace();
        }



    }

}