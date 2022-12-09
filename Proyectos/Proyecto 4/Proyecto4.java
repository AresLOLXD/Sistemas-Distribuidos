import java.io.IOException;

/**
 * Proyecto 4
 * Ares Ulises Juárez Martínez
 * 4CM14
 */

public class Proyecto4 {
    private static final String URL = "http://34.125.203.60/";

    public static void main(String[] args) {

        WebClientSync webClient = new WebClientSync();
        boolean banderaSalida = false;
        while (!banderaSalida) {
            int opcion = 0;
            System.out.println("1.- CURPS generando por segundo");
            System.out.println("2.- Registros en total en la base de datos");
            System.out.println("3.- Registros en cada base de datos");
            System.out.println("4.- Contar bytes");
            System.out.println("5.- Contar género");
            System.out.println("6.- Contar estado");
            System.out.println("7.- Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(System.console().readLine());

            switch (opcion) {
                case 1:
                    try {
                        System.out.println(webClient.doRequest(URL + "countCurpsGenerating", null));
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("Error al realizar la petición");
                    }
                    break;
                case 2:
                    try {
                        System.out.println(webClient.doRequest(URL + "countCurpsDB", null));
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("Error al realizar la petición");
                    }
                    break;
                case 3:
                    try {
                        System.out.println(webClient.doRequest(URL + "countCurpsTotal", null));
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("Error al realizar la petición");
                    }
                    break;
                case 4:
                    try {
                        System.out.println(webClient.doRequest(URL + "countBytes", null));
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("Error al realizar la petición");
                    }
                    break;
                case 5:
                    try {
                        System.out.println(webClient.doRequest(URL + "countGender", null));
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("Error al realizar la petición");
                    }
                    break;
                case 6:
                    try {

                        System.out.println("Los estados son: ");

                        System.out.println("Aguascalientes: AS");
                        System.out.println("Baja California: BC");
                        System.out.println("Baja California Sur: BS");
                        System.out.println("Campeche: CC");
                        System.out.println("Coahuila de Zaragoza: CL");
                        System.out.println("Colima: CM");
                        System.out.println("Chiapas: CS");
                        System.out.println("Chihuahua: CH");
                        System.out.println("Distrito Federal: DF");
                        System.out.println("Durango: DG");
                        System.out.println("Guanajuato: GT");
                        System.out.println("Guerrero: GR");
                        System.out.println("Hidalgo: HG");
                        System.out.println("Jalisco: JC");
                        System.out.println("México: MC");
                        System.out.println("Michoacán de Ocampo: MN");
                        System.out.println("Morelos: MS");
                        System.out.println("Nayarit: NT");
                        System.out.println("Nuevo León: NL");
                        System.out.println("Oaxaca: OC");
                        System.out.println("Puebla: PL");
                        System.out.println("Querétaro: QT");
                        System.out.println("Quintana Roo: QR");
                        System.out.println("San Luis Potosí: SP");
                        System.out.println("Sinaloa: SL");
                        System.out.println("Sonora: SR");
                        System.out.println("Tabasco: TC");
                        System.out.println("Tamaulipas: TS");
                        System.out.println("Tlaxcala: TL");
                        System.out.println("Veracruz de Ignacio de la Llave: VZ");
                        System.out.println("Yucatán: YN");
                        System.out.println("Zacatecas: ZS");
                        System.out.println("Nacido en el Extranjero: NE");

                        System.out.print("Estado: ");
                        String estado = System.console().readLine();

                        System.out.println(webClient.doRequest(URL + "countState", estado.getBytes()));
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("Error al realizar la petición");
                    }
                    break;
                case 7:
                    banderaSalida = true;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;

            }
        }
    }
}
