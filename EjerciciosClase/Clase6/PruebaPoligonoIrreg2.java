import java.util.Random;

public class PruebaPoligonoIrreg2 {
    public static void main(String[] args) {
        PoligonoIrreg irreg = new PoligonoIrreg();
        Random r = new Random();
        Coordenada c = new Coordenada(0.0, 0.0);
        long inicio = System.nanoTime();
        for (int i = 0; i < 10000000; i++) {
            c.setX(r.nextDouble());
            c.setY(r.nextDouble());
            irreg.anadeVertice(c);
        }
        System.out.println((System.nanoTime() - inicio) / 1e9);
    }
}
