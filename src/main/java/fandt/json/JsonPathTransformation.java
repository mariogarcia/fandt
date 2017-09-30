package fandt.json;

import java.util.function.Function;
import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.JsonPath;

/**
 * Transform a given {@link JsonNode} instance into another {@link
 * JsonNode} using a JsonPath expression found in the configuration in
 * the "expression" key
 *
 * @since 0.1.0
 */
public class JsonPathTransformation<O> implements Function<String, O> {

    @SuppressWarnings("PMD.CommentRequired")
    private final transient String expression;

    @SuppressWarnings("PMD.CommentRequired")
    private final transient Class<O> clazz;

    /**
     * Initializes the transformation with the criteria expression and
     * the desired output type.
     *
     * @param expression the expression to filter out the document with
     * @param outputClazz the type of the output.
     * @since 0.1.0
     */
    public JsonPathTransformation(final String expression, final Class<O> outputClazz) {
        this.expression = expression;
        this.clazz = outputClazz;
    }

    /**
     * Provides a JSON fragment out of a JSON string
     *
     * @param json the JSON source
     * @return a filtered JSON fragment/value
     * @since 0.1.0
     */
    @Override
    @SuppressWarnings("PMD.LawOfDemeter")
    public O apply(final String json) {
        return JsonPath
            .parse(json)
            .read(expression, clazz);
    }
}
