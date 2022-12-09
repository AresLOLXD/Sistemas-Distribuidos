
/**
 * Proyecto 3
 * Ares Ulises Juárez Martínez
 * 4CM14
 */

import java.util.Arrays;
import java.util.Random;

public class Proyecto3 {
    private static final String WORKER_ADDRESS_1 = "http://localhost:8080/searchToken";
    private static final String WORKER_ADDRESS_2 = "http://localhost:8081/searchToken";
    private static final String WORKER_ADDRESS_3 = "http://localhost:8082/searchToken";

    public static void main(String[] args) {
        final int minimo = 1757600;
        final int maximo = 17576000;
        Aggregator aggregator = new Aggregator();
        String tasks[] = new String[26];
        Random r = new Random();
        for (int i = 0; i < 26; i++) {
            char caracter = (char) (i + 'A');
            tasks[i] = Integer.toString((minimo + r.nextInt(maximo - minimo + 1))) +
                    "," + caracter + "" + caracter + "" + caracter;
        }

        aggregator.sendTasksToWorkers(
                Arrays.asList(WORKER_ADDRESS_1, WORKER_ADDRESS_2, WORKER_ADDRESS_3),
                Arrays.asList(tasks));

    }
}
