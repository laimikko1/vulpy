package core.tracker;

public class Tracker {

    private long startTime;
    private int seconds;
    private boolean on;

    public Tracker() {
        int seconds = 0;
    }

    public void startTracking(){
        this.startTime = System.currentTimeMillis();
        this.on = true;
    }

    public void stopTracking(){
        this.seconds += (int)(System.currentTimeMillis() - this.startTime) / 1000;
        this.on = false;
    }

    public int getSeconds() {
        if(this.on){
            return seconds + (int)(System.currentTimeMillis() - this.startTime) / 1000;
        } else {
            return seconds;
        }
    }
}
