
/**
 * Proyecto 3
 * Ares Ulises Juárez Martínez
 * 4CM14
 */

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Aggregator {
    private WebClient webClient;

    public Aggregator() {
        this.webClient = new WebClient();
    }

    public void sendTasksToWorkers(List<String> workersAddresses, List<String> tasks) {
        CompletableFuture<String>[] futures = new CompletableFuture[workersAddresses.size()];

        int indiceTareasAsignadas = 0;
        int indiceTareasTerminadas = 0;

        System.out.println("Tareas a ejecutar: ");
        for (String task : tasks) {
            System.out.println(task.replaceAll(",", " - "));
        }

        for (int i = 0; i < workersAddresses.size(); i++) {
            String workerAddress = workersAddresses.get(i);
            String task = tasks.get(i);
            indiceTareasAsignadas = i + 1;

            byte[] requestPayload = task.getBytes();
            futures[i] = webClient.sendTask(workerAddress, requestPayload);
        }

        // Evalúa continuamente si uno de los servidores ha terminado.
        while (indiceTareasTerminadas < tasks.size()) {

            for (int i = 0; i < workersAddresses.size(); i++) {
                if (!(futures[i] == null) && true == futures[i].isDone()) {
                    indiceTareasTerminadas++;
                    System.out.println(
                            "Tarea terminada: " + futures[i].join());
                    if (indiceTareasAsignadas < tasks.size()) {
                        String task = tasks.get(indiceTareasAsignadas++);
                        String workerAddress = workersAddresses.get(i);
                        byte[] requestPayload = task.getBytes();
                        futures[i] = webClient.sendTask(workerAddress, requestPayload);
                    } else {
                        futures[i] = null;
                    }
                }

            }
        }
    }
}
