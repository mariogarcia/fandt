package fandt.func;

import static fandt.func.CheckedSupplier.checked;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

public class CheckedSupplierTest {

    @Test
    public void testCheckedThrowingRuntimeException() {
        assertThrows(RuntimeException.class, () -> {
                createFailingSupplier("/tmp/kk").get();
        });
    }

    Supplier<String> createFailingSupplier(final String parameter) {
        return checked(() -> { throw new IOException("error"); });
    }
}
