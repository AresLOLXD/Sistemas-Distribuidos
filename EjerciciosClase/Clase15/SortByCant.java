import java.util.Comparator;
import java.util.Map;

public class SortByCant implements Comparator<Map.Entry<Character, Integer>> {
    @Override
    public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
        return o2.getValue() - o1.getValue();
    }
}
