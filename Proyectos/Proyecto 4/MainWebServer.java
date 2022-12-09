
/**
 * Proyecto 4
 * Ares Ulises Juárez Martínez
 * 4CM14
 */

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class MainWebServer {
    private static final String COUNT_CURPS_GENERATING = "/countCurpsGenerating";
    private static final String COUNT_CURPS_DB = "/countCurpsDB";
    private static final String COUNT_CURPS_TOTAL = "/countCurpsTotal";
    private static final String COUNT_BYTES = "/countBytes";
    private static final String COUNT_GENDER = "/countGender";
    private static final String COUNT_STATE = "/countState";
    private final int port;
    private HttpServer server;
    private final int numeroDeCurps;
    private final String urls[] = {
            "http://34.125.237.149/",
            "http://34.125.34.22/",
            "http://34.125.58.103/",
    };

    public static void main(String[] args) {
        int numeroDeCurps = Integer.parseInt(args[0]);
        int puerto;
        if (args.length != 2) {
            puerto = 80;
        } else {
            puerto = Integer.parseInt(args[1]);
        }
        MainWebServer webServer = new MainWebServer(puerto, numeroDeCurps);
        webServer.startServer();
        System.out.println("Servidor escuchando en el puerto " + puerto);
        int indiceServidor = 0;
        while (true) {
            try {
                int contador = 0;
                while (contador < numeroDeCurps) {
                    String curp = GeneradorCurp.getCURP();
                    byte[] requestPayload = curp.getBytes();
                    WebClientAsync webClient = new WebClientAsync();
                    webClient.doRequest(webServer.urls[indiceServidor] + "addCurp", requestPayload);
                    indiceServidor = (indiceServidor + 1) % webServer.urls.length;
                    contador++;
                }
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public MainWebServer(int port, int numeroDeCurps) {
        this.port = port;
        this.numeroDeCurps = numeroDeCurps;
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        HttpContext countCurpsGenerating = server.createContext(COUNT_CURPS_GENERATING);
        HttpContext countCurpsDB = server.createContext(COUNT_CURPS_DB);
        HttpContext countCurpsTotal = server.createContext(COUNT_CURPS_TOTAL);
        HttpContext countBytes = server.createContext(COUNT_BYTES);
        HttpContext countGender = server.createContext(COUNT_GENDER);
        HttpContext countState = server.createContext(COUNT_STATE);

        countCurpsGenerating.setHandler(this::handleCountGeneratingCurps);
        countCurpsDB.setHandler(this::handleCountDBCurps);
        countCurpsTotal.setHandler(this::handleCountTotalCurps);
        countBytes.setHandler(this::handleCountBytes);
        countGender.setHandler(this::handleCountGender);
        countState.setHandler(this::handleCountState);

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

    private byte[] calculateResponseCurpsGenerating() {
        return String.format("%d se estan generando por segundo", this.numeroDeCurps).getBytes();
    }

    private void handleCountGeneratingCurps(HttpExchange exchange) throws IOException {

        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        byte[] responseBytes = calculateResponseCurpsGenerating();

        sendResponse(responseBytes, exchange);
    }

    private void handleCountDBCurps(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        byte[] responseBytes = calculateResponseCurpsDB();

        sendResponse(responseBytes, exchange);
    }

    private byte[] calculateResponseCurpsDB() {
        int contador = 0;
        try {
            for (int i = 0; i < urls.length; i++) {
                WebClientSync webClient = new WebClientSync();
                contador += Integer.parseInt(new String(webClient.doRequest(urls[i] + "countCurpsDB", null)));
            }
        } catch (NumberFormatException | IOException | InterruptedException e) {
            e.printStackTrace();
            contador = 0;
        }
        return String.format("%d se han guardado en la base de datos", contador).getBytes();

    }

    private void handleCountTotalCurps(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        byte[] responseBytes = calculateResponseCurpsTotal();

        sendResponse(responseBytes, exchange);
    }

    private byte[] calculateResponseCurpsTotal() {
        String respuesta = "";
        try {
            for (int i = 0; i < urls.length; i++) {
                WebClientSync webClient = new WebClientSync();
                respuesta += String.format("Servidor numero %d: %s curps\n", i + 1,
                        webClient.doRequest(urls[i] + "countCurpsDB", null));
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            respuesta = "";
        }
        return respuesta.getBytes();
    }

    private void handleCountBytes(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        byte[] responseBytes = calculateResponseBytes();

        sendResponse(responseBytes, exchange);
    }

    private byte[] calculateResponseBytes() {
        String respuesta = "";
        int suma = 0;
        try {
            for (int i = 0; i < urls.length; i++) {
                WebClientSync webClient = new WebClientSync();
                int cantidad = Integer.parseInt(webClient.doRequest(urls[i] + "countBytes", null));
                respuesta += String.format("Servidor numero %d: %d bytes\n", i + 1, cantidad);
                suma += cantidad;
            }
            respuesta += String.format("Total: %d bytes", suma);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            respuesta = "";
        }
        return respuesta.getBytes();
    }

    private void handleCountGender(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        byte[] responseBytes = calculateResponseGender();

        sendResponse(responseBytes, exchange);
    }

    private byte[] calculateResponseGender() {
        String respuesta = "";
        int sumaH = 0;
        int sumaM = 0;
        try {
            for (int i = 0; i < urls.length; i++) {
                WebClientSync webClient = new WebClientSync();
                int cantidad = Integer.parseInt(webClient.doRequest(urls[i] + "countGender", "H".getBytes()));
                respuesta += String.format("Servidor numero %d: %d curps de hombre\n", i + 1, cantidad);
                sumaH += cantidad;
            }
            respuesta += String.format("Total: %d curps de hombre\n\n", sumaH);

            for (int i = 0; i < urls.length; i++) {
                WebClientSync webClient = new WebClientSync();
                int cantidad = Integer.parseInt(webClient.doRequest(urls[i] + "countGender", "M".getBytes()));
                respuesta += String.format("Servidor numero %d: %d curps de mujer\n", i + 1, cantidad);
                sumaM += cantidad;
            }
            respuesta += String.format("Total: %d curps de mujer", sumaM);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            respuesta = "";
        }
        return respuesta.getBytes();
    }

    private void handleCountState(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        byte[] requestBytes = exchange.getRequestBody().readAllBytes();
        byte[] responseBytes = calculateResponseState(requestBytes);

        sendResponse(responseBytes, exchange);
    }

    private byte[] calculateResponseState(byte[] requestBytes) {
        String state = new String(requestBytes);
        String respuesta = "";
        int suma = 0;
        try {
            for (int i = 0; i < urls.length; i++) {
                WebClientSync webClient = new WebClientSync();
                int cantidad = Integer.parseInt(webClient.doRequest(urls[i] + "countState", state.getBytes()));
                respuesta += String.format("Servidor numero %d: %d curps de %s\n", i + 1, cantidad, state);
                suma += cantidad;
            }
            respuesta += String.format("Total: %d curps de %s", suma, state);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            respuesta = "";
        }
        return respuesta.getBytes();
    }
}
