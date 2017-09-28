package fandt.json;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.Map;
import java.util.function.Function;
import java.util.concurrent.CompletableFuture;
import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;

/**
 * Transform a given {@link JsonNode} instance into another {@link
 * JsonNode} using a JsonPath expression found in the configuration in
 * the "expression" key
 *
 * @since 0.1.0
 */
public class JsonPathTransformation implements Function<JsonNode, JsonNode> {

    private static final Configuration JACKSON_CONFIGURATION = Configuration
        .builder()
        .mappingProvider(new JacksonMappingProvider())
        .jsonProvider(new JacksonJsonProvider())
        .build();

    /**
     * @since 0.1.0
     */
    protected final String expression;

    /**
     * @param configuration
     * @since 0.1.0
     */
    public JsonPathTransformation(String expression) {
        this.expression = expression;
    }

    @Override
    public JsonNode apply(JsonNode json) {
        return JsonPath
            .using(JACKSON_CONFIGURATION)
            .parse(json)
            .read(expression);
    }
}
