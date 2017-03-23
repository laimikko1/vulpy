package vulpy.core.tracker;

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
        long currentMillis = System.currentTimeMillis();
        if((this.on && ((currentMillis - startTime) / 1000 + this.seconds) > 86400) || seconds > 86400){
            this.on = false;
            return 86400;
        }
        if(this.on){
            return this.seconds + (currentMillis - startTime) / 1000;
        }
        return this.seconds;
    }
}
