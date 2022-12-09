public class EjerciciosSerie4 {
    public static void main(String[] args) {
        int a = 1, b = 1, c = 2, aux;
        System.out.print(a + " " + b + " ");
        for (int i = 0; i < 18; i++) {
            System.out.print(c + " ");
            aux = a + b + c;
            a = b;
            b = c;
            c = aux;
        }
    }
}
