package app03.ejemploConcurrentLinkedDeque;

import app03.ejemploConcurrentLinkedDeque.Consumidor;
import app03.ejemploConcurrentLinkedDeque.Productor;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedDeque;

public class MainConcurrentLinkedDeque {

    public static void main(String[] args) {
        ConcurrentLinkedDeque<Integer> lista = new ConcurrentLinkedDeque<>();

        Productor productor1 = new Productor("ProductoTomas", lista);

        Productor productor2 = new Productor("ProductoJuan", lista);

        Productor productor3 = new Productor("ProductoGuy", lista);

        Thread hilop1  = new Thread(productor1);
        Thread hilop2 = new Thread(productor2);
        Thread hilop3 = new Thread(productor3);

        hilop1.start();
        hilop2.start();
        hilop3.start();

        Consumidor consumidor1 = new Consumidor("ConsumidorTomas", lista);
        Consumidor consumidor2 = new Consumidor("ConsumidorJuan", lista);
        Consumidor consumidor3 = new Consumidor("ConsumidorGuy", lista);

        Thread hiloc1 =  new Thread(consumidor1);
        Thread hiloc2 =  new Thread(consumidor2);
        Thread hiloc3 =  new Thread(consumidor3);

        hiloc1.start();
        hiloc2.start();
        hiloc3.start();
    }

}
