import java.io.Serializable;

public class Coordenada implements Serializable {

    private double x, y;

    public Coordenada(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double magnitud() {
        return Math.sqrt(x * x + y * y);
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