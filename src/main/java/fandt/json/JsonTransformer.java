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
 * Transform a given {@link JsonNode} instance into another {@link
 * JsonNode} using a JsonPath expression found in the configuration in
 * the "expression" key
 *
 * @since 0.1.0
 */
public class JsonTransformer implements Filter<JsonNode, JsonNode> {

    private static final String EXPRESSION_KEY = "expression";
    private static final Configuration JACKSON_CONFIGURATION = Configuration
        .builder()
        .mappingProvider(new JacksonMappingProvider())
        .jsonProvider(new JacksonJsonProvider())
        .build();

    @Override
    public CompletableFuture<JsonNode> filter(Map<String,String> configuration, JsonNode json) {
        return supplyAsync(() -> {
                return JsonPath
                    .using(JACKSON_CONFIGURATION)
                    .parse(json)
                    .read(configuration.get(EXPRESSION_KEY).toString());
            });
    }

    //    public static void main(String[] args) {
    //        Pipelines
    //            .builder()
    //            .input(new HttpInput())
    //            .filters(() -> addEntry("aa", ""),
    //                     () -> addEntry("oo", 1))
    //            .output(mongodb(() -> ))
    //            .cron("")
    //            .execute()
    //
    //    }
}
