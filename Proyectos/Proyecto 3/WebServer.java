
/**
 * Proyecto 3
 * Ares Ulises Juárez Martínez
 * 4CM14
 */

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;

public class WebServer {
    private static final String SEARCH_TOKEN_CONTEXT = "/searchToken";
    private final int port;
    private HttpServer server;

    public static void main(String[] args) {
        int serverPort = 8080;
        if (args.length == 1) {
            serverPort = Integer.parseInt(args[0]);
        }

        WebServer webServer = new WebServer(serverPort);
        webServer.startServer();

        System.out.println("Servidor escuchando en el puerto " + serverPort);
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
        HttpContext searchTokenContext = server.createContext(SEARCH_TOKEN_CONTEXT);
        searchTokenContext.setHandler(this::handleSearchTokenRequest);
        server.setExecutor(Executors.newFixedThreadPool(8));
        server.start();
    }

    private byte[] calculateResponse(byte[] requestBytes) {
        String bodyString = new String(requestBytes);
        String[] stringNumbers = bodyString.split(",");
        int numeroTokens = Integer.parseInt(stringNumbers[0]);
        String cadena = stringNumbers[1];
        StringBuilder cadenota = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < numeroTokens; i++) {
            cadenota
                    .append((char) ('A' + r.nextInt(26)))
                    .append((char) ('A' + r.nextInt(26)))
                    .append((char) ('A' + r.nextInt(26)))
                    .append(" ");
        }
        int indice = 0, contador = 0;
        String cadenotaStr = cadenota.toString();
        while (indice < cadenotaStr.length()) {
            indice = cadenota.indexOf(cadena, indice);
            if (indice == -1) {
                break;
            } else {
                contador++;
                indice++;
            }
        }
        return String.format("Se encontraron: %d apariciones de la cadena %s",
                contador, cadena).getBytes();
    }

    private void handleSearchTokenRequest(HttpExchange exchange) throws IOException {

        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            System.out.println("!exchange.getRequestMethod().equalsIgnoreCase(\"post\"): " + !exchange
                    .getRequestMethod().equalsIgnoreCase("post"));
            exchange.close();
            return;
        }

        Headers headers = exchange.getRequestHeaders();

        boolean isDebugMode = false;
        if (headers.containsKey("X-Debug") && headers.get("X-Debug").get(0).equalsIgnoreCase("true")) {
            isDebugMode = true;
        }

        long startTime = System.nanoTime();

        byte[] requestBytes = exchange.getRequestBody().readAllBytes();
        byte[] responseBytes = calculateResponse(requestBytes);

        long finishTime = System.nanoTime();

        if (isDebugMode) {
            int segundos = (int) ((finishTime - startTime) / 1000000000);
            int milisegundos = (int) ((finishTime - startTime) / 1000000) - segundos * 1000;
            String debugMessage = String.format(
                    "La operación tomó %d nanosegundos = %d segundos con %d milisegundos",
                    finishTime - startTime, segundos, milisegundos);
            exchange.getResponseHeaders().put("X-Debug-Info", Arrays.asList(debugMessage));
        }
        sendResponse(responseBytes, exchange);
    }

    private void sendResponse(byte[] responseBytes, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, responseBytes.length);
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();
        exchange.close();
    }
}
