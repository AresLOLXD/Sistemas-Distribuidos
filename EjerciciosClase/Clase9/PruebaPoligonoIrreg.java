import java.util.Random;

public class PruebaPoligonoIrreg {
    public static void main(String[] args) {
        PoligonoIrreg irreg = new PoligonoIrreg();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            double abcisa = r.nextInt(100);
            if (r.nextBoolean())
                abcisa *= -1;
            abcisa += r.nextDouble();
            double ordenada = r.nextInt(100);
            if (r.nextBoolean())
                ordenada *= -1;
            ordenada += r.nextDouble();

            irreg.anadeVertice(new Coordenada(abcisa, ordenada));
        }
        System.out.println(irreg);
        irreg.ordenaVertices();
        System.out.println(irreg);

    }
}
