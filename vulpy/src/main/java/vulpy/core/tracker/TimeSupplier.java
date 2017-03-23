package vulpy.core.tracker;

@FunctionalInterface
public interface TimeSupplier {
    long getNanoseconds();
}
