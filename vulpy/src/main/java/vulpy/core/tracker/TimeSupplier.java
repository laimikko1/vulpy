package vulpy.core.tracker;

/**
 * Interface joka tarjoaa vain yhden metodin SystemTimeSupplierille.
 * Hyödynnetään ajankäytön testaamisessa.
 */

@FunctionalInterface
public interface TimeSupplier {
    long getNanoseconds();
}
