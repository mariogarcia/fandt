package fandt;

import java.util.List;

/**
 * @since 0.1.0
 */
class Pipeline {

    /**
     * @since 0.1.0
     */
    private static class InputFragment<InputType> {

        /**
         * @since 0.1.0
         */
        final Input input

        /**
         * @since 0.1.0
         */
        public InputFragment(Input<InputType> input) {
            this.input = input;
        }

        /**
         * @since 0.1.0
         */
        private static class FilterFragment<I,O> {

            /**
             * @since 0.1.0
             */
            final Filter<I,O> filter;

            /**
             * @since 0.1.0
             */
            public FilterFragment(Filter<I,O> filter) {
                this.filter = filter;
            }

            /**
             * @since 0.1.0
             */
            private static class OutputFragment<I,O> {

            }

            /**
             * @param output
             * @return
             * @since 0.1.0
             */
            public <I,O> OutputFragment<I,O> output(Output<I,O> output) {
                return new OutputFragment<I,O>(output);
            }
        }

        /**
         * @param filter
         * @return
         * @since 0.1.0
         */
        public <I,O> FilterFragment<I,O> filter(Filter<I,O> filter) {
            return new FilterFragment<I,O>(filter);
        }

    }

    /**
     * @param supplier
     * @return
     * @since 0.1.0
     */
    public static <InputType> InputFragment<InputType> input(Input<InputType> supplier) {
        return new InputFragment<InputType>(supplier);
    }
}
