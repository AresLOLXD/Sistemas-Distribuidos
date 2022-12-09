import java.util.Random;

class SerieEjercicio1 {

    public static void main(String[] args) {
        long inicio = System.nanoTime();
        int n = Integer.parseInt(args[0]);
        char cadenota[] = new char[n * 4];
        Random r = new Random();
        int indice = 0, contador = 0;
        for (int i = 0; i < n; i++) {
            cadenota[indice++] = (char) ('A' + r.nextInt(26));
            cadenota[indice++] = (char) ('A' + r.nextInt(26));
            cadenota[indice++] = (char) ('A' + r.nextInt(26));
            cadenota[indice++] = ' ';
        }
        indice = 0;
        for (int i = 0; i < cadenota.length; i++) {
            if (cadenota[i] == 'I' && indice == -1) {
                indice = 0;
            } else if (cadenota[i] == 'P' && indice == 0) {
                indice++;
            } else if (cadenota[i] == 'N' && indice == 1) {
                contador++;
                indice = -1;
            } else {
                indice = -1;
            }
        }
        System.out.println(contador);
        System.out.println((System.nanoTime() - inicio) / 1e9);
    }
}