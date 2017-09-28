package fandt;

import java.util.Map;

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
    public TYPE produce(Map<String,String> configuration);
}
