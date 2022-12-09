import java.util.ArrayList;
import java.util.Iterator;

class Main2 {

    public static void main(String[] args)

    {

        ArrayList<String> CURPS = new ArrayList<>();
        int n = Integer.parseInt(args[0]);
        for (int i = 0; i < n; i++) {
            String CURP = getCURP();
            System.out.println(CURP);
            int indice = 0;
            Iterator<String> it = CURPS.iterator();
            while (it.hasNext()) {
                String CurpCompare = it.next();
                if (CurpCompare.substring(0, 4).compareTo(CURP.substring(0, 4)) >= 0) {
                    break;
                }
                indice++;
            }
            CURPS.add(indice, CURP);
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