import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class EjercicioClase1 {
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
            System.out.println("Caracteres distintos encontrados: " + map.values().size());
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                System.out.println(pair.getKey() + " = " + pair.getValue());
                it.remove();
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}