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

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class Application {
    private static final String URL = "http://localhost:8080/task";

    public static void main(String[] args) {
        Random r = new Random();
        PoligonoIrreg poligonoIrreg = new PoligonoIrreg();
        poligonoIrreg.anadeVertice(new Coordenada(r.nextDouble(), r.nextDouble()));
        poligonoIrreg.anadeVertice(new Coordenada(r.nextDouble(), r.nextDouble()));
        poligonoIrreg.anadeVertice(new Coordenada(r.nextDouble(), r.nextDouble()));
        System.out.println("Poligono inicial: \n" + poligonoIrreg);
        while (true) {
            poligonoIrreg = enviaPeticion(poligonoIrreg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static PoligonoIrreg enviaPeticion(PoligonoIrreg poligonoIrreg) {
        System.out.println("Poligono antes de enviar: \n" + poligonoIrreg);
        WebClient webClient = new WebClient();
        byte[] requestPayload = SerializationUtils.serialize(poligonoIrreg);
        CompletableFuture<byte[]> response = webClient.sendTask(URL, requestPayload);
        while (!response.isDone()) {
        }
        PoligonoIrreg responsePoligonoIrreg = (PoligonoIrreg) SerializationUtils
                .deserialize(response.join());
        System.out.println("Poligono despues de recibir: \n" + responsePoligonoIrreg);
        return responsePoligonoIrreg;
    }
}