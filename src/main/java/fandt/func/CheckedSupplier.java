package fandt.func;

import java.util.function.Supplier;

/**
 * Sometimes a {@link Supplier} needs to catch checked
 * exceptions. This class acts as a {@link Supplier} but without the
 * need of catching checked exceptions.
 *
 * @since 0.1.0
 */
public interface CheckedSupplier<T> {

    /**
     * Executes the supplier to get the produced value
     *
     * @return the produced value
     * @throws could throw any type of runtime exceptions
     * @since 0.1.0
     */
    T get() throws Throwable;

    /**
     * Creates a suplier out of a given {@link CheckedSupplier}
     *
     * @param function the {@link CheckedSupplier} we want to create a
     * {@link Supplier} from
     * @return an instance of {@link Supplier}
     * @since 0.1.0
     */
    @SuppressWarnings({"PMD.AvoidCatchingThrowable", "PMD.AvoidThrowingRawExceptionTypes"})
    static <T> Supplier<T> checked(CheckedSupplier<T> function) {
        return () -> {
            try {
                return function.get();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        };
    }
}
