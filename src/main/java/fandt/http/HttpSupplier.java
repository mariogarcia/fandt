package fandt.http;

import fandt.Util;
import java.util.function.Supplier;
import com.mashape.unirest.http.Unirest;

/**
 * @since 0.1.0
 */
public class HttpSupplier implements Supplier<String> {

    private final String url;
    private final String acceptHeader;

    /**
     * @param url
     * @since 0.1.0
     */
    public HttpSupplier(String url, String acceptHeader) {
        this.url = url;
        this.acceptHeader = acceptHeader;
    }

    /**
     * @return
     * @since 0.1.0
     */
    public String get() {
        return Util.checked(() -> {
                return Unirest
                .get(url)
                .header("Accept", acceptHeader)
                .asString()
                .getBody();
        }).get();
    }
}
