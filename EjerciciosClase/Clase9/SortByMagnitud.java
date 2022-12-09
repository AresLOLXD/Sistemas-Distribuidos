import java.util.Comparator;

public class SortByMagnitud implements Comparator<Coordenada> {

    @Override
    public int compare(Coordenada c1, Coordenada c2) {

        if (c1.getMagitud() < c2.getMagitud())
            return -1;
        else if (c1.getMagitud() > c2.getMagitud())
            return 1;
        else
            return 0;
    }

}