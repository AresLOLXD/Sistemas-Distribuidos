public class Hilos implements Runnable {
    static int n = 0;
    static int vart_compartida = 0;

    public void run() {
        for (int i = 0; i < n; i++) {
            modificar();
        }
    }

    public synchronized void modificar() {
        if (Thread.currentThread().getName() == "h1") {
            vart_compartida++;
        } else {
            vart_compartida--;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        n = Integer.parseInt(args[0]);
        Thread a = new Thread(new Hilos());
        Thread h1 = new Thread(a, "h1");
        Thread h2 = new Thread(a, "h2");

        h1.start();

        h2.start();
        h1.join();
        h2.join(); // ejecuta el segundo hilo hasta que el primero acabo

        System.out.println(vart_compartida);
    }
}