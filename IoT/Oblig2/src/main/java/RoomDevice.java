import java.util.TreeMap;

public class RoomDevice {

    public static void main(String[] args) {

        Room room = new Room(20);
        Display display;
        TemperatureSensor sensor = new TemperatureSensor(room);
        Heating heating;
        Controller controller;

        MQTTPubTemperature sensorpub = new MQTTPubTemperature(sensor);
        //MQTTSubTemperature sensorsub;

        try {
            display = new Display();
            heating = new Heating(room);
            controller = new Controller();
          //  sensorsub = new MQTTSubTemperature(display);

            Thread temppublisher = new Thread(sensorpub);
            //Thread tempsub = new Thread(sensorsub);
            Thread heatsub = new Thread(heating);
            Thread control = new Thread(controller);
            // TODO: add heating subscriber running in its own thread
            control.start();
            heatsub.start();
            //tempsub.start();
            temppublisher.start();

            heatsub.join();
            control.join();
            //tempsub.join();
            temppublisher.join();

        } catch (Exception ex) {

            System.out.println("RoomDevice: " + ex.getMessage());
            ex.printStackTrace();
        }



    }

}