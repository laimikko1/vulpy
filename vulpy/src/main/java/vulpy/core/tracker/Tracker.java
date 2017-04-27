package vulpy.core.tracker;

/**
 * Tracker-luokka tarjoaa ajanlaskuun tarvittavat metodit yhtä päivää kohden.
 * Yksi tracker-olio voi korkeintaan laskea 24h aikaa.
 */

public class Tracker {

    private static final long DAY_TO_CENTISECONDS = 8640000;

    private final TimeSupplier timeSupplier;
    private long startTime;
    private long centiseconds;
    private boolean on;

    /**
     * Konstruktorissa alustetaan tietyn trackerin mitattu aika, sekä annetaan systemTimeSupplier luokalle.
     * @param timeSupplier trackerin käyttämä timeSupplier olio.
     */

    public Tracker(TimeSupplier timeSupplier) {
        this.timeSupplier = timeSupplier;
    }

    /**
     * Konstruktorissa alustetaan tietyn trackerin mitattu aika, sekä annetaan systemTimeSupplier luokalle.
     */

    public Tracker() {
        this(new SystemTimeSupplier());
        this.centiseconds = 0;
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
            this.centiseconds += nanosecondsToCentiseconds(timeSupplier.getNanoseconds() - this.startTime);
            this.on = false;
        }
    }

    /**
     * Metodi getCentiseconds tarjoaa trackerin mittaaman ajan senttisekuntteina.
     * @return senttisekuntteina trackerin mittaaman ajan.
     */

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

    /**
     * Metodi getMinutes tarjoaa tietyn trackerin mittaamat minuutit.
     * @return mitatut minuutit.
     */

    public double getMinutes() {
        return (double) getCentiseconds() / 6000;
    }

    private long nanosecondsToCentiseconds(Long nanoseconds) {
        return nanoseconds / 10000000;
    }
}