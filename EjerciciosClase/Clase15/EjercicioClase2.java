
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class EjercicioClase2 {
    static final int MAX_T = 5;

    public static void main(String[] args) {
        Runnable r1 = new Task("Tarea 1", 0);
        Runnable r2 = new Task("Tarea 2", 1);
        Runnable r3 = new Task("Tarea 3", 2);
        Runnable r4 = new Task("Tarea 4", 3);
        Runnable r5 = new Task("Tarea 5", 4);

        // creates a thread pool with MAX_T no. of
        // threads as the fixed pool size(Step 2)
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        // passes the Task objects to the pool to execute (Step 3)
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);

        // pool shutdown ( Step 4)
        pool.shutdown();

    }
}