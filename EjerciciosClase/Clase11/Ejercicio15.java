
public class Ejercicio15 implements Runnable {
    static int var_compartida = 0;
    static int n = 0;

    public static void main(String[] args) throws InterruptedException {

        n = Integer.parseInt(args[0]);

        Thread a = new Thread(new Ejercicio15());
        Thread t1 = new Thread(a, "suma");
        Thread t2 = new Thread(a, "resta");
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("El valor es: " + Ejercicio15.var_compartida);

    }

    public synchronized void modifica() {

        if (Thread.currentThread().getName() == "resta") {
            var_compartida--;
        } else {
            var_compartida++;
        }

    }

    @Override
    public void run() {
        for (int i = 0; i < Ejercicio15.n; i++) {

            modifica();
        }

    }
}