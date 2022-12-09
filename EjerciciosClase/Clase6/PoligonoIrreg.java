
import java.lang.StringBuilder;

public class PoligonoIrreg {
    private Coordenada[] vertices;

    private int indice;

    public PoligonoIrreg() {
        vertices = new Coordenada[10000005];
        indice = 0;
    }

    public void anadeVertice(Coordenada coordenada) {
        vertices[indice++] = coordenada;

    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("PoligonoIrreg:[\n");
        for (int i = 0; i < vertices.length; i++) {
            Coordenada c = vertices[i];
            str.append("\tVertice " + i + ":\n");
            str.append("\t\t" + c + "\n");
        }
        str.append("]");

        return str.toString();
    }

}
