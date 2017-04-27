package vulpy.core.tracker;

/**
 * Tracker-luokka tarjoaa ajanlaskuun tarvittavat metodit.
 * Yksi tracker-olio voi korkeintaan laskea 24h aikaa.
 */

public class Tracker {

    private static final long DAY_TO_CENTISECONDS = 8640000;

    private final TimeSupplier timeSupplier;
    private long startTime;
    private long centiseconds;
    private boolean on;
    private int max;

    public Tracker() {
        this(new SystemTimeSupplier());
        this.centiseconds = 0;
        this.max = 0;
    }

    public Tracker(int max) {
        this(new SystemTimeSupplier());
        this.centiseconds = 0;
        this.max = max;
    }

    public Tracker(TimeSupplier timeSupplier) {
        this.timeSupplier = timeSupplier;
    }

    public void startTracking() {
        if(!this.on){
            this.startTime = timeSupplier.getNanoseconds();
            this.on = true;
        }
    }

    public void stopTracking() {
        if(this.on){
            this.centiseconds += nanosecondsToCentiseconds(timeSupplier.getNanoseconds() - this.startTime);
            this.on = false;
        }
    }

    public long getCentiseconds() {
        long currentNano = timeSupplier.getNanoseconds();
        if ((this.on && (nanosecondsToCentiseconds(currentNano - startTime) + this.centiseconds) > DAY_TO_CENTISECONDS)) {
            this.on = false;
            return DAY_TO_CENTISECONDS;
        } else if (this.centiseconds > DAY_TO_CENTISECONDS) {
            this.on = false;
            return DAY_TO_CENTISECONDS;
        }
        if (this.on) {
            return this.centiseconds + nanosecondsToCentiseconds(currentNano - startTime);
        }
        return this.centiseconds;
    }

    public double getMinutes() {
        return (double) getCentiseconds() / 6000;
    }

    public long nanosecondsToCentiseconds(Long nanoseconds) {
        return nanoseconds / 10000000;
    }
}