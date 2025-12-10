package Ejercicio3;

import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {

        Buffer bufer = new Buffer(6);

        Productor productor = new Productor(bufer);

        Consumidor consumidor = new Consumidor(bufer);

        Thread productorThread = new Thread(productor);
        Thread consumidorThread = new Thread(consumidor);
        productorThread.start();
        consumidorThread.start();



    }
}
