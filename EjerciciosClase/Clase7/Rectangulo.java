public class Rectangulo extends Figura implements Peritmetro {

    private Coordenada superiorIzq, inferiorDer;

    public Rectangulo() {

        superiorIzq = new Coordenada(0, 0);

        inferiorDer = new Coordenada(0, 0);

    }

    public Rectangulo(double xSupIzq, double ySupIzq, double xInfDer, double yInfDer) {

        superiorIzq = new Coordenada(xSupIzq, ySupIzq);

        inferiorDer = new Coordenada(xInfDer, yInfDer);

    }

    public Rectangulo(Coordenada p1, Coordenada p2) {
        superiorIzq = p1;

        inferiorDer = p2;
    }

    // Metodo getter de la coordenada superior izquierda

    public Coordenada superiorIzquierda() {
        return superiorIzq;
    }

    // Metodo getter de la coordenada inferior derecha

    public Coordenada inferiorDerecha() {
        return inferiorDer;
    }

    // Sobreescritura del método de la superclase objeto para imprimir con
    // System.out.println( )

    @Override

    public String toString() {

        return "Esquina superior izquierda: " + superiorIzq + "\tEsquina superior derecha:" + inferiorDer + "\n";

    }

    @Override
    double area() {
        return Math.abs((this.superiorIzq.ordenada() - this.inferiorDer.ordenada())
                * (this.inferiorDer.abcisa() - this.superiorIzq.abcisa()));
    }

    @Override
    public float imprimePerimetro() {
        return (float) Math.abs((this.superiorIzq.ordenada() - this.inferiorDer.ordenada()) * 2
                + (this.inferiorDer.abcisa() - this.superiorIzq.abcisa()) * 2);
    }

}