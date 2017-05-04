package vulpy.core.tracker;

/**
 * Tracker-luokka tarjoaa ajanlaskuun tarvittavat metodit yhtä päivää kohden.
 * Yksi tracker-olio voi korkeintaan laskea maxTime verran aikaa
 */

public class Tracker {

    private final TimeSupplier timeSupplier;
    private long startTime;
    private long milliseconds;
    private long maxTime;
    private boolean on;

    /**
     * Konstruktorissa alustetaan tietyn trackerin mitattu aika, sekä annetaan systemTimeSupplier luokalle.
     * @param timeSupplier trackerin käyttämä timeSupplier olio.
     * @param maxTime millisekuntteina oleva maksimiaika kuinka kauan tracker voi maksimissaan olla päällä.
     */

    public Tracker(TimeSupplier timeSupplier, long maxTime) {
        this.timeSupplier = timeSupplier;
        this.milliseconds = 0;
        this.maxTime = maxTime;
    }

    /**
     * Konstruktorissa alustetaan tietyn trackerin mitaama aika, sekä annetaan systemTimeSupplier luokalle.
     * @param maxTime millisekuntteina oleva maksimiaika kuinka kauan tracker voi maksimissaan olla päällä.
     */

    public Tracker(long maxTime) {
        this(new SystemTimeSupplier(), maxTime);
    }

    /**
     * Metodi startTracking aloittaa ajanlaskemisen, vain jos tracker on ennestään ollut pois päältä.
     */

    public void startTracking() {
        if (!this.on) {
            this.startTime = timeSupplier.getNanoseconds();
            this.on = true;
        }
    }

    /**
     * Metodi stopTracking lopettaa ajanlaskemisen, vain jos tracker on ennestään ollut päällä.
     */

    public void stopTracking() {
        if (this.on) {
            this.milliseconds += nanosecondsToMilliseconds(timeSupplier.getNanoseconds() - this.startTime);
            this.on = false;
        }
    }

    /**
     * Metodi getMilliseconds tarjoaa trackerin mittaaman ajan millisekunteina.
     * Jos aika on mennyt yli maksimaalisesta mitatusta ajasta annetaan ulos maxTime.
     * @return senttisekuntteina trackerin mittaaman ajan.
     */

    public long getMilliseconds() {
        long currentNano = timeSupplier.getNanoseconds();
        if ((this.on && (nanosecondsToMilliseconds(currentNano - this.startTime) + this.milliseconds) > this.maxTime)) {
            this.on = false;
            this.milliseconds = this.maxTime;
            return this.maxTime;
        }
        if (this.on) {
            return this.milliseconds + nanosecondsToMilliseconds(currentNano - startTime);
        }
        return this.milliseconds;
    }

    /**
     * Metodi getSeconds tarjoaa trackerin mittaaman ajan sekunteina.
     * @return sekunteina trackerin mittaaman ajan.
     */

    public long getSeconds() {
        return getMilliseconds() / 1000l;
    }

    /**
     * Metodi getMinutes tarjoaa tietyn trackerin mittaamat minuutit liukulukuna.
     * @return mitatut minuutit.
     */

    public double getMinutes() {
        return (double) getMilliseconds() / 60000;
    }

    private long nanosecondsToMilliseconds(Long nanoseconds) {
        return nanoseconds / 1000000;
    }
}