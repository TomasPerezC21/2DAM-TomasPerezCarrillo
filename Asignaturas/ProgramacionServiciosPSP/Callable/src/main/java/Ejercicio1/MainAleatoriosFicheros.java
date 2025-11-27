package Ejercicio1;

import java.io.*;

public class MainAleatoriosFicheros {

    public static void main(String[] args) {

        //Thread calculadora1 = new Thread();

        String nombreFichero = "src/main/java/Ejercicio1/numeros.txt";
        AleatoriosFichero calcular1 = new AleatoriosFichero(nombreFichero);

        //calculadora1.start();

        Thread hilo1 = new Thread(calcular1);

        hilo1.start();

        int suma = 0;

        for (int i = 100; i <= 10000; i++) {
            suma += i;
        }

        System.out.println("Suma: " + suma);


        try {
            hilo1.join();
            BufferedReader br = new BufferedReader(new FileReader(nombreFichero));
            String linea = br.readLine();
            while(linea != null) {
                System.out.println(linea);
                linea = br.readLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
