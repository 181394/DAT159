package devices;

public class TemperatureSensor {
    private int tempstate;
    private long lastsense;
    private static double RATE = .0001; // change in temperature per time unit
    private double temperature;
    public TemperatureSensor(int startTemp) {
        tempstate = -1;
        lastsense = System.currentTimeMillis();
        temperature = startTemp;
    }

    synchronized public double sense() {

        long timenow = System.currentTimeMillis();
        long timeinterval = timenow - lastsense;

        temperature += (RATE * tempstate * timeinterval);

        return temperature;

    }


    public void setTempstate(int tempstate) {
        this.tempstate = tempstate;
    }

    public double getTemperature() {
        return sense();
    }

}
