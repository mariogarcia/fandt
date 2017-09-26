package fandt.json;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import fandt.Filter;

/**
 * @since 0.1.0
 */
public class JsonTransformer implements Filter<JsonNode, JsonNode> {

    public static final Configuration JACKSON_CONFIGURATION = Configuration
        .builder()
        .mappingProvider(new JacksonMappingProvider())
        .jsonProvider(new JacksonJsonProvider())
        .build();

    @Override
    public CompletableFuture<JsonNode> filter(Map<String,String> configuration, JsonNode json) {
        return CompletableFuture.supplyAsync(() -> {
                return JsonPath
                    .using(JACKSON_CONFIGURATION)
                    .parse(json)
                    .read(configuration.get("path").toString());
            });
    }
}
