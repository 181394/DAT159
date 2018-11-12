package controllers;

import devices.Heater;
import devices.TemperatureSensor;

public class Room {
    private TemperatureSensor tempSensor;
    private Heater heater;


    public Room(int starttemp) {

        heater = new Heater();
        tempSensor = new TemperatureSensor(starttemp);
    }



    private void actuate() {
        if (heater.getState()) {
            tempSensor.setTempstate(1);
        } else {
            tempSensor.setTempstate(-1);
        }
    }

    synchronized public void toggleHeater(){
        if (heater.getState()) {
            heater.setState(false);
        } else {
            heater.setState(true);
        }
        actuate();
    }

    public boolean getHeaterState(){
        return heater.getState();
    }

    public double getRoomTemp(){
        return tempSensor.getTemperature();
    }
}