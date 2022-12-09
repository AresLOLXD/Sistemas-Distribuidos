
/**
 * Proyecto 2 Primera parte
 * Ares Ulises Juárez Martínez
 * 4CM14
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Proyecto {
    public static void main(String[] args) {
        try {
            Set<String> palabras = new HashSet<>();
            File carpeta = new File("LIBROS_TXT");
            File[] archivos = carpeta.listFiles();
            for (File archivo : archivos) {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] palabrasLinea = linea.split("[- .,;:?!¡¿\"(){}\\[\\]<>\\t\\n\\r]+");
                    for (String palabra : palabrasLinea) {
                        palabra = palabra.replaceAll("[^a-zA-Z0-9]", "").trim();
                        if (!palabra.isEmpty()) {
                            palabras.add(palabra);
                        }
                    }
                }
                br.close();
            }
            System.out.print("Ingresa la frase a revisar la ortografía: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String frase = br.readLine();
            br.close();
            String[] palabrasFrase = frase.split("[- .,;:?!¡¿\"(){}\\[\\]<>\\t\\n\\r]+");
            for (String palabra : palabrasFrase) {
                String copia = palabra;
                palabra = palabra.replaceAll("[^a-zA-Z0-9]", "").trim();
                if (!palabra.isEmpty()) {
                    if (!palabras.contains(palabra)) {
                        System.out.println("La palabra \"" + copia + "\" puede no estar escrita correctamente.");
                    }
                }
            }
            System.out.println("Finalizo el analisis de la frase.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}