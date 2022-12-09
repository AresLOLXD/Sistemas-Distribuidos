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

    static String getCURP()

    {

        String Letra = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String Numero = "0123456789";

        String Sexo = "HM";

        String Entidad[] = { "AS", "BC", "BS", "CC", "CS", "CH", "CL", "CM", "DF", "DG", "GT", "GR", "HG", "JC", "MC",
                "MN", "MS", "NT", "NL", "OC", "PL", "QT", "QR", "SP", "SL", "SR", "TC", "TL", "TS", "VZ", "YN", "ZS" };

        int indice;

        StringBuilder sb = new StringBuilder(18);

        for (int i = 1; i < 5; i++) {

            indice = (int) (Letra.length() * Math.random());

            sb.append(Letra.charAt(indice));

        }

        for (int i = 5; i < 11; i++) {

            indice = (int) (Numero.length() * Math.random());

            sb.append(Numero.charAt(indice));

        }

        indice = (int) (Sexo.length() * Math.random());

        sb.append(Sexo.charAt(indice));

        sb.append(Entidad[(int) (Math.random() * 32)]);

        for (int i = 14; i < 17; i++) {

            indice = (int) (Letra.length() * Math.random());

            sb.append(Letra.charAt(indice));

        }

        for (int i = 17; i < 19; i++) {

            indice = (int) (Numero.length() * Math.random());

            sb.append(Numero.charAt(indice));

        }

        return sb.toString();

    }

}