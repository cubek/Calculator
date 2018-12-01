package sk.cubi.calculator.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.util.Map;

public class HttpClient {

    private String url;
    private Client client;
    private String userAgent;

    /**
     * Creates a new instance of HTTP client, defaults User-Agent (request header)
     * to that of Firefox (to avoid DDoS protection)
     *
     * @param url URL of resource which should be accessed
     */
    public HttpClient(String url) {
        this(url, "Mozilla/5.0 (X11; U; Linux i686) Gecko/20071127 Firefox/2.0.0.11");
    }

    /**
     * Creates a new instance of HTTP client with defined values
     *
     * @param url       URL of resource which should be accessed
     * @param userAgent User-Agent header field which should be used in request
     */
    public HttpClient(String url, String userAgent) {
        this.url = url;
        this.client = Client.create();
        this.userAgent = userAgent;
    }

    public String makeGetRequest(Map<String, String> params) {
        WebResource webResource = client.resource(this.url);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            webResource = webResource.queryParam(entry.getKey(), entry.getValue());
        }

        // set custom User-Agent to avoid DDoS protection (default agent is banned)
        ClientResponse response = webResource.header("User-Agent", this.userAgent).get(ClientResponse.class);

        return response.getEntity(String.class);
    }
}
