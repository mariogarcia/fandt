package fandt;

import static fandt.Fandt.http;
import static fandt.Fandt.path;
import static fandt.Fandt.file;
import static fandt.Fandt.classpath;
import static fandt.Fandt.toStreamOfType;
import static fandt.func.CheckedSupplier.checked;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import org.apache.commons.io.FileUtils;

public class FandtTest {

    @Test
    public void testHttpResource() {
        String SOURCE_URL = "https://data.cityofnewyork.us/resource/qiz3-axqb.json";
        Function<String,List> CRITERIA =
            path("$..*[?(@.borough == 'BRONX')]['number_of_persons_injured']",
                 List.class);

        int peopleInjured = http(SOURCE_URL)
            .map(CRITERIA)
            .flatMap(toStreamOfType(String.class))
            .mapToInt(value -> Integer.parseInt(value))
            .sum();


        assertEquals(14, peopleInjured);
    }

    @Test
    public void testClasspathResource() {
        String SOURCE_URL = "nypd_motor_vehicle_collisions.json";
        Function<String,List> CRITERIA =
            path("$..*[?(@.borough == 'BRONX')]['number_of_persons_injured']",
                 List.class);

        int peopleInjured = classpath(SOURCE_URL)
            .map(CRITERIA)
            .flatMap(toStreamOfType(String.class))
            .mapToInt(value -> Integer.parseInt(value))
            .sum();


        assertEquals(14, peopleInjured);
    }

    @Test
    public void testFileResource() {
        String SOURCE_URL = "nypd_motor_vehicle_collisions.json";
        Function<String,List> CRITERIA =
            path("$..*[?(@.borough == 'BRONX')]['number_of_persons_injured']",
                 List.class);

        String path = classpath(SOURCE_URL)
            .map(copyToFile("/tmp/test_file.json"))
            .findFirst()
            .get();

        int peopleInjured = file(path)
            .map(CRITERIA)
            .flatMap(toStreamOfType(String.class))
            .mapToInt(value -> Integer.parseInt(value))
            .sum();


        assertEquals(14, peopleInjured);
    }

    Function<String,String> copyToFile(final String destination) {
        return (String json) -> {
            checked(() -> {
                    FileUtils.writeStringToFile(new File(destination), json);
                return null;
                }).get();

            return destination;
        };
    }
}
