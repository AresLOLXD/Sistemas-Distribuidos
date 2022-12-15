
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class MainWebServer {
    private static final String IS_PRIME = "/is_prime";
    private final int port;
    private HttpServer server;
    private final String url = "http://127.0.0.1:8081/";

    public static void main(String[] args) {
        int puerto;
        if (args.length != 2) {
            puerto = 8080;
        } else {
            puerto = Integer.parseInt(args[0]);
        }
        MainWebServer webServer = new MainWebServer(puerto);
        webServer.startServer();
        System.out.println("Servidor escuchando en el puerto " + puerto);
    }

    public MainWebServer(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        HttpContext isPrime = server.createContext(IS_PRIME);

        isPrime.setHandler(this::handleIsPrime);

        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }

    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, responseBytes.length);
        exchange.getResponseHeaders().add("Content-Type", "text/plain");
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
        exchange.close();
    }

    private byte[] requestIsPrime(int numero) {
        WebClientAsync client = new WebClientAsync();
        String url = this.url + "is_prime?numero=" + numero;
        CompletableFuture<String> respuesta = client.doRequest(url);
        while (!respuesta.isDone()) {
        }
        String response = respuesta.join();
        byte[] responseBytes = response.getBytes();
        return responseBytes;
    }

    private Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }

    private void handleIsPrime(HttpExchange exchange) throws IOException {

        if (!exchange.getRequestMethod().equalsIgnoreCase("get")) {
            exchange.close();
            return;
        }
        System.out.println("Solicitud recibida: " + exchange.getRequestURI());
        String query = exchange.getRequestURI().getQuery();
        Map<String, String> params = queryToMap(query);
        byte[] responseBytes = "No hay parametros".getBytes();
        if (params.containsKey("numero")) {

            String number = params.get("numero");
            responseBytes = requestIsPrime(Integer.parseInt(number));
        }
        sendResponse(responseBytes, exchange);
    }

}
