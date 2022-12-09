import java.util.Random;
import java.lang.StringBuilder;

class SerieEjercicio2 {

    public static void main(String[] args) {

        long inicio = System.nanoTime();
        int n = Integer.parseInt(args[0]);
        StringBuilder cadenota = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            cadenota
                    .append((char) ('A' + r.nextInt(27)))
                    .append((char) ('A' + r.nextInt(27)))
                    .append((char) ('A' + r.nextInt(27)))
                    .append(" ");
        }

        int indice = 0, contador = 0;
        String cadenotaStr = cadenota.toString();
        while (indice < cadenotaStr.length()) {
            indice = cadenota.indexOf("IPN", indice);
            if (indice == -1) {
                break;
            } else {
                contador++;
                indice++;
            }
        }
        System.out.println(contador);
        System.out.println((System.nanoTime() - inicio) / 1e9);
    }
}