package fandt.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static fandt.Mappers.toJson;
import static fandt.Mappers.toJsonPath;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class StringTransformationTest {

    @Test
    void toUpperCaseTest() {
        List values = Stream.of("{\"value\":\"one\"}","{\"value\":\"two\"}")
            .map(toJson())
            .map(toJsonPath("*.value"))
            //            .map(String::toUpperCase)
            .collect(Collectors.toList());

        assertEquals(Arrays.asList("ONE", "TWO"), values);
    }
}
