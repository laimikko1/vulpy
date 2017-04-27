package vulpy.core.tracker;

/**
 * Luokka tarjoaa ainoastaan getNanoseconds metodin Tracker-luokalle.
 */

class SystemTimeSupplier implements TimeSupplier {

    /**
     * getNanoseconds metodi antaa tietokoneen tämänhetkisen ajan nanosekuntteina.
     * @return Tietokoneen tämänhetkinen aika nanosekuntteina.
     */

    public long getNanoseconds() {
        return System.nanoTime();
    }
}