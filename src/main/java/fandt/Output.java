package fandt;

/**
 * @since 0.1.0
 */
@FunctionalInterface
public interface Output<INPUT> {

    /**
     * @param input
     * @return
     * @since 0.1.0
     */
    Runnable process(INPUT input);
}