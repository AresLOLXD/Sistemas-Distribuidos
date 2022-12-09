
/**
 * Proyecto 2 Parte 2
 * Ares Ulises Juárez Martínez
 * 4CM14
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Proyecto2 {
    static boolean esPalindromo(String cadena) {
        int i = 0;
        int j = cadena.length() - 1;
        while (i < j) {
            if (cadena.charAt(i) != cadena.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        File archivo = new File(args[0]);
        try (FileReader fr = new FileReader(archivo)) {
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabrasLinea = linea.split("[- .,;:?!¡¿\"(){}\\[\\]<>\\t\\n\\r]+");
                for (String palabra : palabrasLinea) {
                    palabra = palabra.trim();
                    if (!palabra.isEmpty() && palabra.length() > 1 && esPalindromo(palabra)) {
                        System.out.println(palabra);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}