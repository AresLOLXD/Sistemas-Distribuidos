import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class EjercicioClase1 {
    public static void main(String[] args) {
        try {

            FileReader fr;
            Map<Character, Integer> map = new HashMap<Character, Integer>();
            fr = new FileReader("./BIBLIA_COMPLETA.txt");
            int ch;
            while ((ch = fr.read()) != -1) {
                map.put((char) ch, map.getOrDefault((char) ch, 0) + 1);
            }
            fr.close();
            ArrayList<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(
                    map.entrySet());
            list.sort(new SortByCant());
            System.out.println("\n\nCaracteres ordenados por cantidad:");
            for (Map.Entry<Character, Integer> entry : list) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}