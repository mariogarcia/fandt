package fandt;

import java.util.function.Supplier;

public final class Util {

    public static interface CheckedSupplier<T> {
        public T get() throws Throwable;
    }

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
