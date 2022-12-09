
/**
 * Proyecto 4
 * Ares Ulises Juárez Martínez
 * 4CM14
 */

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;

public class WebServer {
    private static final String ADD_CURP = "/addCurp";
    private static final String COUNT_CURPS = "/countCurpsDB";
    private static final String COUNT_BYTES = "/countBytes";
    private static final String COUNT_GENDER = "/countGender";
    private static final String COUNT_STATE = "/countState";
    private final int port;
    private final List<String> curps;
    private HttpServer server;

    public static void main(String[] args) {
        int puerto;
        if (args.length != 1) {
            puerto = 80;
        } else {
            puerto = Integer.parseInt(args[0]);
        }
        WebServer webServer = new WebServer(puerto);
        webServer.startServer();
        System.out.println("Servidor escuchando en el puerto " + puerto);
    }

    public WebServer(int port) {
        this.port = port;
        this.curps = new ArrayList<>();
    }

    public void startServer() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        HttpContext addCurp = server.createContext(ADD_CURP);
        HttpContext countCurpsDB = server.createContext(COUNT_CURPS);
        HttpContext countBytes = server.createContext(COUNT_BYTES);
        HttpContext countGender = server.createContext(COUNT_GENDER);
        HttpContext countState = server.createContext(COUNT_STATE);

        addCurp.setHandler(this::handleAddCurp);
        countCurpsDB.setHandler(this::handleCountDBCurps);
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

    private void handleCountDBCurps(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        byte[] requestBytes = exchange.getRequestBody().readAllBytes();
        byte[] responseBytes = calculateResponseCurpsDB(requestBytes);

        sendResponse(responseBytes, exchange);
    }

    private byte[] calculateResponseCurpsDB(byte[] requestBytes) {
        return String.valueOf(curps.size()).getBytes();
    }

    private void handleCountBytes(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        byte[] requestBytes = exchange.getRequestBody().readAllBytes();
        byte[] responseBytes = calculateResponseBytes(requestBytes);

        sendResponse(responseBytes, exchange);
    }

    private byte[] calculateResponseBytes(byte[] requestBytes) {
        return String.valueOf(curps.size() * 18).getBytes();
    }

    private void handleCountGender(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        byte[] requestBytes = exchange.getRequestBody().readAllBytes();
        byte[] responseBytes = calculateResponseGender(requestBytes);

        sendResponse(responseBytes, exchange);
    }

    private byte[] calculateResponseGender(byte[] requestBytes) {
        String gender = new String(requestBytes);
        int count = 0;
        for (String curp : curps) {
            if (curp.substring(10, 11).equals(gender)) {
                count++;
            }
        }
        return String.valueOf(count).getBytes();
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
        int count = 0;
        for (String curp : curps) {
            if (curp.substring(11, 13).equals(state)) {
                count++;
            }
        }
        return String.valueOf(count).getBytes();
    }

    private void handleAddCurp(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equalsIgnoreCase("post")) {
            exchange.close();
            return;
        }

        byte[] requestBytes = exchange.getRequestBody().readAllBytes();
        byte[] responseBytes = calculateResponseAddCurp(requestBytes);

        sendResponse(responseBytes, exchange);
    }

    private byte[] calculateResponseAddCurp(byte[] requestBytes) {
        String curp = new String(requestBytes);
        curps.add(curp);
        return curp.getBytes();
    }
}
