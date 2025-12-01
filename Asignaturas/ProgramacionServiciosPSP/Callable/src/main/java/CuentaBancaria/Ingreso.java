package CuentaBancaria;

public class Ingreso implements Runnable {

    private double cantidad;
    private CuentaBancaria cuentaBancaria;

    public Ingreso(double cantidad, CuentaBancaria cuentaBancaria){
        this.cantidad = cantidad;
        this.cuentaBancaria = cuentaBancaria;
    }

    @Override
    public void run() {
        cuentaBancaria.ingresar(cantidad);
    }

}
