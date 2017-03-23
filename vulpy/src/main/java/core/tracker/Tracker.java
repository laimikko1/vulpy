package core.tracker;

public class Tracker {

    private long startTime;
    private long seconds;
    private boolean on;

    public Tracker() {
        this.seconds = 0;
    }

    public void startTracking(){
        this.startTime = System.currentTimeMillis();
        this.on = true;
    }

    public void stopTracking(){
        this.seconds += (System.currentTimeMillis() - this.startTime) / 1000;
        this.on = false;
    }

    public long getSeconds() {
        long currentTimeSeconds = (System.currentTimeMillis()) / 1000;
        if(this.seconds + (currentTimeSeconds - this.startTime) > 86400){
            this.on = false;
            return 86400;
        } else if(this.on){
            return seconds + (currentTimeSeconds - this.startTime) / 1000;
        } else {
            return seconds;
        }
    }
}
