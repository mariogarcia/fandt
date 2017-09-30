package fandt.http;

import static fandt.func.CheckedSupplier.checked;

import java.util.function.Supplier;
import com.mashape.unirest.http.Unirest;

/**
 * This class represents a simple Http GET request to get a given
 * resource.
 *
 * @since 0.1.0
 */
public class HttpSupplier implements Supplier<String> {

    @SuppressWarnings("PMD.CommentRequired")
    private final transient String url;

    @SuppressWarnings("PMD.CommentRequired")
    private final transient String acceptHeader;

    /**
     * Initializes the supplier by passing the resource url and the
     * "Accept" header value.
     *
     * @param url the url of the resource
     * @param acceptHeader the type of the "Accept" header
     * @since 0.1.0
     */
    public HttpSupplier(final String url, final String acceptHeader) {
        this.url = url;
        this.acceptHeader = acceptHeader;
    }

    /**
     * Executes the logic to get the remote resouces's content
     *
     * @return an {@link String} containing the resource content
     * @since 0.1.0
     */
    @Override
    @SuppressWarnings("PMD.LawOfDemeter")
    public String get() {
        return checked(() -> {
                return Unirest
                .get(url)
                .header("Accept", acceptHeader)
                .asString()
                .getBody();
        }).get();
    }
}
