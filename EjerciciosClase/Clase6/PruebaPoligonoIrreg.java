import java.util.Random;

public class PruebaPoligonoIrreg {
    public static void main(String[] args) {
        PoligonoIrreg irreg = new PoligonoIrreg();
        Random r = new Random();
        long inicio = System.nanoTime();
        for (int i = 0; i < 10000000; i++) {
            irreg.anadeVertice(new Coordenada(r.nextDouble(), r.nextDouble()));
        }
        System.out.println((System.nanoTime() - inicio) / 1e9);
    }
}
