package Ejercicio1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class AleatoriosFichero implements Runnable {

    private String nombreFichero;

    public AleatoriosFichero(String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }

    @Override
    public void run() {
        Random aleatorio = new Random();

        File file = new File(nombreFichero);

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file, false));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 1; i <= 5; i++) {
            int num = aleatorio.nextInt(1, 21);

            try {

                bw.write(num+"");
                bw.newLine();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        try {
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
