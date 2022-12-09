import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class EjercicioClase2 {
    public static void main(String[] args) {
        try {

            FileReader fr;
            Map<Character, Integer> map = new HashMap<Character, Integer>();
            fr = new FileReader("./El_viejo_y_el_mar.txt");
            int ch;
            long contador = 0L;
            while ((ch = fr.read()) != -1) {
                map.put((char) ch, map.getOrDefault((char) ch, 0) + 1);
                contador++;
            }
            System.out.println("Caracteres encontrados: " + contador);
            System.out.println("Caracteres distintos encontrados: " + map.size());
            Iterator it = map.entrySet().iterator();
            ArrayList<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(
                    map.entrySet());
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                System.out.println(pair.getKey() + " = " + pair.getValue());
                it.remove();
            }
            fr.close();
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