package fandt;

import java.util.function.Supplier;

/**
 * @since 0.1.0
 */
public final class Util {

    /**
     * @since 0.1.0
     */
    public static interface CheckedSupplier<T> {

        /**
         * @return
         * @since 0.1.0
         */
        public T get() throws Throwable;
    }

    /**
     * @param fn
     * @return
     * @since 0.1.0
     */
    public static <T> Supplier<T> checked(CheckedSupplier<T> fn) {
        return () -> {
            try {
                return fn.get();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        };
    }
}
