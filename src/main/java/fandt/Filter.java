package fandt;

import java.util.Map;

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
    OUT filter(Map<String, String> configuration, IN out);
}
