package vulpy.core.tracker;

/**
 * TimeSupplier interface joka tarjoaa vain yhden metodin SystemTimeSupplierille.
 * Hyödynnetään ajankäytön testaamisessa.
 */

@FunctionalInterface
public interface TimeSupplier {
    long getNanoseconds();
}
