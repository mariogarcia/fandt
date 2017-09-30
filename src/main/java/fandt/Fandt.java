package fandt;

import static fandt.func.CheckedSupplier.checked;

import fandt.json.JsonPathTransformation;
import fandt.http.HttpSupplier;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.net.URL;
import java.util.Map;
import java.util.List;
import java.util.stream.Stream;
import java.util.function.Supplier;
import java.util.function.Function;
import com.google.common.io.Resources;
import com.google.common.base.Charsets;

/**
 * Set of functions to transform and filter JSON data
 *
 * @since 0.1.0
 */
@SuppressWarnings("PMD.CommentSize")
public final class Fandt {

    private Fandt() {
        // UTILITY CLASS
    }

    /**
     * Returns a {@link Stream} with the content of the file located
     * at the file path passed as an argument
     *
     * @param filePath the path where json file is
     * @return a {@link Stream} containing the file content
     * @since 0.1.0
     */
    public static Stream<String> file(final String filePath) {
        return checked(() -> Stream.of(new String(Files.readAllBytes(Paths.get(filePath))))).get();
    }

    /**
     * This function gets a given JSON by doing an HTTP GET request to
     * the url passed as an argument.
     *
     * @param url the URL where the json resource is located
     * @return a {@link Stream} containing the json content
     * @since 0.1.0
     */
    public static Stream<String> http(final String url) {
        return Stream.of(new HttpSupplier(url, "application/json").get());
    }

    /**
     * Returns a {@link Stream} with the content of the file located
     * in the classpath. The path is supposed to be absolute.
     *
     * @param path the path where json file is
     * @return a {@link Stream} containing the file content
     * @since 0.1.0
     */
    @SuppressWarnings("PMD.LawOfDemeter")
    public static Stream<String> classpath(final String path) {
        final URL classpathURL = Resources.getResource(path);
        final Supplier<String> supplier = checked(() -> Resources.toString(classpathURL, Charsets.UTF_8));
        final String fileContent = supplier.get();

        return Stream.of(fileContent);
    }

    /**
     * This method returns a function that parses and filters the
     * a json document
     *
     * @param expression expression to filter out the Json document
     * @param clazz the class used to cast the result
     * @return a function receiving an string and returning the result
     * @since 0.1.0
     */
    public static <OUTPUT> Function<String,OUTPUT> path(final String expression, final Class<OUTPUT> clazz) {
        return new JsonPathTransformation(expression, clazz);
    }

    /**
     * This method returns a function that parses and filters the
     * document passed as argument. The result of the filtering will
     * be casted to a {@link Stream} type
     *
     * @param expression the JsonPath expression to filter the document with
     * @return a function that receives a string and returns an {@link
     * Stream} of the defined class
     * @since 0.1.0
     */
    public static Function<String, Stream<?>> pathAsStream(final String expression) {
        return (String json) -> new JsonPathTransformation<List>(expression, List.class).apply(json).stream();
    }

    /**
     * This method returns a function that parses and filters the
     * document passed as argument. The result of the filtering will
     * be casted to a {@link Map} type
     *
     * @param expression the JsonPath expression to filter the
     * document with
     * @return a function that receives a string and returns an {@link
     * Map}
     * @since 0.1.0
     */
    public static Function<String, Map<String,?>> toJsonPathAsMap(final String expression) {
        return (String json) -> new JsonPathTransformation<Map>(expression, Map.class).apply(json);
    }

    /**
     * This method returns a {@link Function} receiving a List and
     * returning an Stream of the type class passed as an argument.
     *
     * This method is a shortcut to create and cast an unbound list to
     * an {@link Stream} of certain type.
     *
     * param clazz type of the resulting {@link Stream}
     * @return an {@link Stream} of a certain type.
     * @since 0.1.0
     */
    public static <O> Function<List,Stream<O>> toStreamOfType(final Class<O> clazz) {
        return (List list) -> (Stream<O>) list.stream();
    }
}
