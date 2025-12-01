package CuentaBancaria;

import java.util.concurrent.Callable;

public class CuentaBancaria  {

    private double saldo;

    public CuentaBancaria() {
        this.saldo = 0;
    }


    public synchronized void ingresar (double cantidad) {
        System.out.println("Ingresando "+ cantidad);
        this.saldo += cantidad;
        System.out.println("Saldo total despues del ingreso: " + this.saldo);
        notifyAll();
    }

    public synchronized void retirar(double cantidad) throws InterruptedException {
        System.out.println("Retirando "+ cantidad);

        while (saldo < cantidad) {
            wait();
        }

        this.saldo -= cantidad;
        System.out.println("Saldo total despues de la retirada: " + this.saldo);
    }
}
