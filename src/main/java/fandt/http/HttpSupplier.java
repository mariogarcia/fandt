package fandt.http;

import java.util.function.Supplier;
import com.mashape.unirest.http.Unirest;
/**
 * @since 0.1.0
 */
public class HttpSupplier implements Supplier<String> {

    /**
     * @since 0.1.0
     */
    protected final String url;

    /**
     * @since 0.1.0
     */
    protected final String acceptHeader;

    /**
     * @param url
     * @since 0.1.0
     */
    public HttpSupplier(String url, String acceptHeader) {
        this.url = url;
    }

    /**
     * @return
     * @since 0.1.0
     */
    public String get() {
        return Unirest
            .get(url)
            .header("Accept", acceptHeader);
    }
}
