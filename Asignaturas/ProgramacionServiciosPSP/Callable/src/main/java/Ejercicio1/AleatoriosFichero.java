package Ejercicio1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class AleatoriosFichero implements Runnable {

    private String nombreFichero = "src/main/java/Ejercicio1/numeros.txt";

    @Override
    public void run() {
        Random aleatorio = new Random();

        File file = new File(nombreFichero);

        for (int i = 1; i < 5; i++) {
            int num = aleatorio.nextInt(1, 21);

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
                bw.write(num);
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



    }

}
