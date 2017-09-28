package fandt.json;

import java.util.Map;
import java.util.function.Function;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fandt.Util;

/**
 * This filter parses a given {@link String} to a {@link JsonNode}
 * instance
 *
 * @since 0.1.0
 */
public class JsonParser implements Function<String,JsonNode> {

    @Override
    public JsonNode apply(String json) {
        return Util
            .checked(() -> new ObjectMapper().readTree(json))
            .get();
    }
}
