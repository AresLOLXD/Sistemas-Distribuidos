
import java.io.Serializable;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

public class PoligonoIrreg implements Serializable {
    private List<Coordenada> vertices;

    public PoligonoIrreg() {
        this.vertices = new ArrayList<>();
    }

    public void anadeVertice(Coordenada coordenada) {
        vertices.add(coordenada);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("PoligonoIrreg:[\n");
        int i = 1;
        for (Coordenada c : vertices) {
            str.append("\tVertice " + (i++) + ":\n");
            str.append("\t\t" + c + "\n");
            str.append("\t\tMagnitud: " + c.magnitud() + "\n");
        }
        str.append("]\n\n");

        return str.toString();
    }

}
