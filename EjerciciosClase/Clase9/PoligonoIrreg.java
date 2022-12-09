
import java.lang.StringBuilder;
import java.util.ArrayList;

public class PoligonoIrreg {
    private ArrayList<Coordenada> vertices;

    public PoligonoIrreg() {
        vertices = new ArrayList<>();
    }

    public void anadeVertice(Coordenada coordenada) {
        vertices.add(coordenada);

    }

    public void ordenaVertices() {
        vertices.sort(new SortByMagnitud());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("PoligonoIrreg:[\n");
        int contador = 0;
        for (Coordenada c : vertices) {
            str.append("\tVertice " + contador++ + ":\n");
            str.append("\t\t" + c + "\n");

            str.append("\t\tMagnitud: " + c.getMagitud() + "\n");
        }
        str.append("]");

        return str.toString();
    }

}
