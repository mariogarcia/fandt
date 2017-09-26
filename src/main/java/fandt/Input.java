package fandt;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * @since 0.1.0
 */
@FunctionalInterface
public interface Input<TYPE> {

    /**
     * @param configuration
     * @return
     * @since 0.1.0
     */
    Future<TYPE> produce(Map<String,String> configuration);
}
