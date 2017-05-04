package vulpy.core.tracker;

/**
 * TimeSupplier interface joka tarjoaa metodit SystemTimeSupplierille.
 * Hyödynnetään ajankäytön testaamisessa.
 */

@FunctionalInterface
public interface TimeSupplier {

    /**
     * Metodi getNanoseconds tarjoaa tietokoneen tämänhetkisen ajan nanosekuntteina.
     * @return tietokoneen tämänhetkinen aika nanosekuntteina.
     */

    long getNanoseconds();
}
