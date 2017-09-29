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
public class JsonPathTransformation<OUTPUT> implements Function<String, OUTPUT> {

    /**
     * @since 0.1.0
     */
    protected final String expression;
    protected final Class<OUTPUT> clazz;

    /**
     * @param configuration
     * @since 0.1.0
     */
    public JsonPathTransformation(String expression, Class<OUTPUT> outputClazz) {
        this.expression = expression;
        this.clazz = outputClazz;
    }

    @Override
    public OUTPUT apply(String json) {
        return JsonPath
            .parse(json)
            .read(expression, clazz);
    }
}
