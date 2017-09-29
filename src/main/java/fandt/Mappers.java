package fandt;

import static java.util.stream.Collectors.toList;

import fandt.json.JsonParser;
import fandt.json.JsonPathTransformation;
import java.util.List;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.function.Function;
import com.fasterxml.jackson.databind.JsonNode;

public final class Mappers {

    public static Function<String,JsonNode> toJson() {
        return new JsonParser();
    }

    public static <OUTPUT> Function<JsonNode,OUTPUT> toJsonPath(String expression, Class<OUTPUT> clazz) {
        return new JsonPathTransformation(expression, clazz);
    }

    public static <Output> Function<JsonNode, List<String>> toJsonPathAsList(String expression) {
        return (JsonNode jsonNode) -> {
            return (List<String>)new JsonPathTransformation<List>(expression, List.class)
                .apply(jsonNode)
                .stream()
                .collect(toList());
        };
    }

    public static void main(String args[]) throws Throwable {

        List values = java.util.stream.Stream
            .of("{\"data\": {\"value\":\"one\"}}")
            .map(toJson())
            .map(toJsonPathAsList("$..value"))
            .collect(toList());

        System.out.println(values);
    }
}
