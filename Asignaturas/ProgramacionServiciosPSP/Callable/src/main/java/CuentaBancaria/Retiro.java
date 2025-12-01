package CuentaBancaria;

public class Retiro implements Runnable {

    private double cantidad;
    private CuentaBancaria cuentaBancaria;

    public Retiro(double cantidad, CuentaBancaria cuentaBancaria) {
        this.cantidad = cantidad;
        this.cuentaBancaria = cuentaBancaria;
    }


    @Override
    public void run() {
        try {
            cuentaBancaria.retirar(cantidad);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
