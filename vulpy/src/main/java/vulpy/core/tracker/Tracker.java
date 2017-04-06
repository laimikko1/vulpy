package vulpy.core.tracker;

/**
 * Tracker-luokka tarjoaa ajanlaskuun tarvittavat metodit.
 */


public class Tracker {

    private static final long DAY_TO_CENTISECONDS = 8640000;

    private final TimeSupplier timeSupplier;
    private long startTime;
    private long centiseconds;
    private boolean on;

    public Tracker() {
        this(new SystemTimeSupplier());
        this.centiseconds = 0;
    }

    public Tracker(TimeSupplier timeSupplier) {
        this.timeSupplier = timeSupplier;
    }

    public void startTracking() {
        this.startTime = timeSupplier.getNanoseconds();
        this.on = true;
    }

    public void stopTracking() {
        this.centiseconds += nanosecondsToCentiseconds(timeSupplier.getNanoseconds() - this.startTime);
        this.on = false;
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

    public long nanosecondsToCentiseconds(Long nanoseconds) {
        return nanoseconds / 10000000;
    }
}