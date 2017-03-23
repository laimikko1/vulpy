package vulpy.core.tracker;

class SystemTimeSupplier implements TimeSupplier {
    public long getNanoseconds() {
        return System.nanoTime();
    }
}