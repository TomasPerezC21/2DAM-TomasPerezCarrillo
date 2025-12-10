package Ejercicio1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscritorNumeros implements Runnable {

    private String nombreFichero;
    private int inicio;
    private int fin;

    public EscritorNumeros(String nombreFichero, int inicio, int fin) {
        this.nombreFichero = nombreFichero;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    public void run() {

        try (FileWriter fw = new FileWriter(nombreFichero, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            for (int i = inicio; i <= fin; i++) {
                bw.write(i+" ");

                Thread.sleep(100);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
