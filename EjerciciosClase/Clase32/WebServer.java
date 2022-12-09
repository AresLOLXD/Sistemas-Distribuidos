/*
 *  MIT License
 *
 *  Copyright (c) 2019 Michael Pogrebinsky - Distributed Systems & Cloud Computing with Java
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;

public class WebServer {
    private static final String SEARCH_TOKEN_CONTEXT = "/task";

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

    private byte[] calculateResponse2(byte[] requestBytes) {

        Demo demo = (Demo) SerializationUtils.deserialize(requestBytes);
        System.out.println("demo = " + demo);

        String cadena = demo.b;
        int numeroTokens = demo.a;
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
        byte[] responseBytes = calculateResponse2(requestBytes);

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
