package vulpy.core.tracker;

/**
 * Luokka tarjoaa ainoastaan getNanosecons metodin Tracker-luokalle
 */

class SystemTimeSupplier implements TimeSupplier {
    public long getNanoseconds() {
        return System.nanoTime();
    }
}