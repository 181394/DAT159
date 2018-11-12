package devices;

public class Heater {
    private boolean state;

    public Heater(){
        state=false;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
