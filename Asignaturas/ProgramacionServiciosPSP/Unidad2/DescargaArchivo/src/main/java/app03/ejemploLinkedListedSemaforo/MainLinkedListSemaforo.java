package app03.ejemploLinkedListedSemaforo;

import app02.Semaforo;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class MainLinkedListSemaforo {

    public static void main(String[] args) {

        LinkedList<Integer> lista = new LinkedList<>();

        Semaphore semaforo = new Semaphore(0);

        Productor productor1 = new Productor("ProductorTomas", lista,  semaforo);

        //Productor productor2 = new Productor("ProductoJuan", lista,   semaforo);

        //Productor productor3 = new Productor("ProductoGuy", lista,   semaforo);

        Thread hilop1  = new Thread(productor1);
        //Thread hilop2 = new Thread(productor2);
        //Thread hilop3 = new Thread(productor3);

        hilop1.start();
        //hilop2.start();
        //hilop3.start();

        Consumidor consumidor1 = new Consumidor("ConsumidorTomas", lista, semaforo);
        Consumidor consumidor2 = new Consumidor("ConsumidorJuan", lista, semaforo);
        Consumidor consumidor3 = new Consumidor("ConsumidorGuy", lista, semaforo);
        Consumidor consumidor4 = new Consumidor("ConsumidorMario", lista, semaforo);

        Thread hiloc1 =  new Thread(consumidor1);
        Thread hiloc2 =  new Thread(consumidor2);
        Thread hiloc3 =  new Thread(consumidor3);
        Thread hiloc4 =  new Thread(consumidor4);

        hiloc1.start();
        hiloc2.start();
        hiloc3.start();
        hiloc4.start();
    }

}
