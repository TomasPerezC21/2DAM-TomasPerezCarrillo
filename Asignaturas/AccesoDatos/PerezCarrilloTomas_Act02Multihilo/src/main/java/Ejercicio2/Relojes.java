package Ejercicio2;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Relojes implements Runnable {

    @Override
    public void run() {
        // Formato para la hora
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalTime horaMadrid = LocalTime.now();
        LocalTime horaNY = LocalTime.now(ZoneId.of("America/New_York"));


        System.out.println("Madrid: " + horaMadrid.format(formato) +
                " | Nueva York: " + horaNY.format(formato));
    }
}