package devices;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Display {

    public void write(String message) {
        System.out.println("DISPLAY: " + message);
    }

}