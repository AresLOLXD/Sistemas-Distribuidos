public class PruebaRectangulo {

    public static void main(String[] args) {

        Rectangulo rect1 = new Rectangulo(
                new Coordenada(5, 7), new Coordenada(4, -1));

        System.out.println("El perimetro del rectángulo es = " + rect1.imprimePerimetro());

        Cuadrado cua1 = new Cuadrado(
                new Coordenada(6, 9), new Coordenada(9, 6));

        System.out.println("El perimetro del cuadrado es = " + cua1.imprimePerimetro());

    }

}