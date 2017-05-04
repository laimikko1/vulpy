package vulpy.core.tracker;

/**
 * SystemTimeSupplier-luokka tarjoaa getNanoseconds metodin Tracker-luokalle.
 */

class SystemTimeSupplier implements TimeSupplier {

    /**
     * Metodi getNanoseconds antaa tietokoneen tämänhetkisen ajan nanosekuntteina.
     * @return Tietokoneen tämänhetkinen aika nanosekuntteina.
     */

    public long getNanoseconds() {
        return System.nanoTime();
    }
}