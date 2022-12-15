
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class WebServer {
    private static final String IS_PRIME = "/is_prime";
    private final int port;
    private HttpServer server;

    public static void main(String[] args) {
        int puerto;
        if (args.length != 2) {
            puerto = 8081;
        } else {
            puerto = Integer.parseInt(args[0]);
        }
        WebServer webServer = new WebServer(puerto);
        webServer.startServer();
        System.out.println("Servidor escuchando en el puerto " + puerto);
    }

    public WebServer(int port) {
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
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
        exchange.close();
    }

    private byte[] testPrime(long numero) {
        String response = "El numero " + numero + " es primo";
        if (numero < 2L) {
            response = "El numero " + numero + " no es primo";
        } else {
            for (long i = 2L; i < numero; i++) {
                if (numero % i == 0L) {
                    response = "El numero " + numero + " no es primo";
                    break;
                }
            }
        }
        return response.getBytes();
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
            responseBytes = testPrime(Long.parseLong(number));
        }
        sendResponse(responseBytes, exchange);
    }

}
