import java.util.ArrayList;
import java.util.Iterator;

class Main {

    public static void main(String[] args)

    {

        ArrayList<String> CURPS = new ArrayList<>();
        int n = Integer.parseInt(args[0]);
        char sexo = args[1].charAt(0);
        for (int i = 0; i < n; i++) {
            CURPS.add(getCURP());
        }
        System.out.println(CURPS);
        Iterator<String> it = CURPS.iterator();
        while (it.hasNext()) {
            String curp = it.next();
            if (curp.charAt(10) == sexo) {
                it.remove();
            }
        }
        System.out.println(CURPS);
    }

    // Función para generar una CURP aleatoria

}