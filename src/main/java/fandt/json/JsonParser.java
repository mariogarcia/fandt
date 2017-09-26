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
 * @since 0.1.0
 */
public class JsonParser implements Filter<String,JsonNode> {

    @Override
    public CompletableFuture<JsonNode> filter(final Map<String,String> configuration, final String json) {
        Supplier<JsonNode> supplier = Util.checked(() -> {
            return new ObjectMapper().readTree(json);
            });

        return supplyAsync(supplier);
    }
}
