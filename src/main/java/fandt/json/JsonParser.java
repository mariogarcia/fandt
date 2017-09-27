package fandt.json;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.Map;
import java.util.function.Supplier;
import java.util.concurrent.CompletableFuture;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fandt.Filter;
import fandt.Util;

/**
 * This filter parses a given {@link String} to a {@link JsonNode}
 * instance
 *
 * @since 0.1.0
 */
public class JsonParser implements Filter<String,JsonNode> {

    @Override
    public CompletableFuture<JsonNode> filter(final Map<String,String> configuration, final String json) {
        return supplyAsync(Util.checked(() -> new ObjectMapper().readTree(json)));
    }
}
