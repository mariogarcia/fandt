package fandt;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @since 0.1.0
 */
public class Pipeline {

    /**
     * @param supplier
     * @return
     * @since 0.1.0
     */
    public static <InputType> InputFragment<InputType> input(Input<InputType> supplier) {
        return new InputFragment<InputType>(supplyAsync(() -> supplier.produce(null)));
    }

    /**
     * @since 0.1.0
     */
    public static class InputFragment<InputType> {

        /**
         * @since 0.1.0
         */
        final CompletableFuture<InputType> input;

        /**
         * @since 0.1.0
         */
        public InputFragment(CompletableFuture<InputType> input) {
            this.input = input;
        }

        /**
         * @param filter
         * @return
         * @since 0.1.0
         */
        public <O> FilterFragment<InputType,O> filter(Filter<InputType,O> filter) {
            CompletableFuture<O> computation = this
                .input
                .thenCompose((in) -> supplyAsync(() -> filter.filter(null, in)));

            return new FilterFragment<InputType,O>(computation);
        }

        /**
         * @since 0.1.0
         */
        public static class FilterFragment<I,O> {

            /**
             * @since 0.1.0
             */
            final CompletableFuture<O> filter;

            /**
             * @since 0.1.0
             */
            public FilterFragment(CompletableFuture<O> filter) {
                this.filter = filter;
            }

            public <OutputType> FilterFragment<O,OutputType> filter(Filter<O,OutputType> filter) {
                CompletableFuture<OutputType> computation = this
                    .filter
                    .thenCompose((in) -> supplyAsync(() -> filter.filter(null, in)));

                return new FilterFragment<O,OutputType>(computation);
            }

            /**
             * @param output
             * @return
             * @since 0.1.0
             */
            public CompletableFuture<Void> output(Output<O> output) {
                return this.filter.thenCompose((in) -> supplyAsync(() -> {
                        output.process(in);
                        return null;
                    }));
            }
        }
    }
}
