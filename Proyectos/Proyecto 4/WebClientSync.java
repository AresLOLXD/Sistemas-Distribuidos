
/**
 * Proyecto 4
 * Ares Ulises Juárez Martínez
 * 4CM14
 */

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.Duration;

public class WebClientSync {

    private HttpClient client;

    public WebClientSync() {
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    public String doRequest(String url, byte[] requestPayload) throws IOException, InterruptedException {
        if (requestPayload == null) {
            requestPayload = new byte[0];
        }
        HttpRequest request = HttpRequest.newBuilder()
                .POST(BodyPublishers.ofByteArray(requestPayload))
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
