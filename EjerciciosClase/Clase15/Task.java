
class Task implements Runnable {
    final long cantCaracteres = 815272L;
    private String name;
    private int indice;

    public Task(String s, int indice) {
        name = s;
        this.indice = indice;
    }

    // Prints task name and sleeps for 1s
    // This Whole process is repeated 5 times
    public void run() {

        long contador = 0L;
        long inicio = indice * cantCaracteres;
        while (contador < cantCaracteres) {
            contador++;
            Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(
                    Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(
                            Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(
                                    Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(
                                            Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math
                                                    .tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(
                                                            Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(Math
                                                                    .tan(Math.tan(Math.tan(Math.tan(Math.tan(Math.tan(
                                                                            Math.tan(Math.tan(Math.tan(Math.tan(Math
                                                                                    .tan(Math.tan(Math.tan(Math.tan(Math
                                                                                            .tan(Math.tan(Math.tan(Math
                                                                                                    .tan(Math.tan(Math
                                                                                                            .tan(Math
                                                                                                                    .tan(Math
                                                                                                                            .tan(Math
                                                                                                                                    .tan(Math
                                                                                                                                            .tan(Math
                                                                                                                                                    .tan(Math
                                                                                                                                                            .tan(Math
                                                                                                                                                                    .tan(Math
                                                                                                                                                                            .tan(Math
                                                                                                                                                                                    .tan(Math
                                                                                                                                                                                            .tan(Math
                                                                                                                                                                                                    .tan(Math
                                                                                                                                                                                                            .tan(Math
                                                                                                                                                                                                                    .tan(Math
                                                                                                                                                                                                                            .tan(Math
                                                                                                                                                                                                                                    .tan(Math
                                                                                                                                                                                                                                            .tan(Math
                                                                                                                                                                                                                                                    .tan(Math
                                                                                                                                                                                                                                                            .tan(inicio))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))));
        }

        System.out.println("Finalizo la tarea: " + name);

    }
}