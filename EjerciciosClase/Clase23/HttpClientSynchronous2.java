
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpClientSynchronous2 implements Runnable {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    static final int MAX_T = 4;

    public static void main(String[] args) throws IOException, InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
        int m = Integer.parseInt(args[0]);

        for (int i = 0; i < m; i++) {
            System.out.println("Ejecutando la peticion numero: " + (i + 1));
            pool.execute(new HttpClientSynchronous2());
        }

        pool.shutdown();
    }

    @Override
    public void run() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(BodyPublishers.ofString("1757600,IPN"))
                    .uri(URI.create("http://192.168.43.216:8080/searchToken"))
                    .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                    .setHeader("X-Debug", "true") // add request header
                    .build();
            HttpResponse<String> response;
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // print response headers
            HttpHeaders headers = response.headers();
            headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

            // print status code
            System.out.println(response.statusCode());

            // print response body
            System.out.println(response.body());
            System.out.println();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}