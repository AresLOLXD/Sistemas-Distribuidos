
public class Coordenada {

    private double x, y, magitud;

    public Coordenada(double x, double y) {

        this.x = x;

        this.y = y;
        this.magitud = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public double getMagitud() {
        return magitud;
    }

    // Metodo getter de x

    public double abcisa() {
        return x;
    }

    // Metodo getter de y

    public double ordenada() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    // Sobreescritura del método de la superclase objeto para imprimir con
    // System.out.println( )

    @Override

    public String toString() {

        return "[" + x + "," + y + "]";

    }

}
