package fandt;

import java.util.function.Supplier;

public final class Util {

    public static <T> Supplier<T> checked(Supplier<T> fn) {
        return () -> {
            try {
                return fn.get();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        };
    }
}
