package fandt;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * @since 0.1.0
 */
public interface Filter<IN,OUT> {

    /**
     * @param out
     * @param configuration
     * @return
     * @since 0.1.0
     */
    Future<OUT> filter(Map<String, String> configuration, IN out);
}
