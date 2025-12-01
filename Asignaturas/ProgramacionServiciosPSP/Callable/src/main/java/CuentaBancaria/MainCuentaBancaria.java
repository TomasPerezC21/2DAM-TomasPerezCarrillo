package CuentaBancaria;

public class MainCuentaBancaria {

    public static void main(String[] args) throws InterruptedException {

        CuentaBancaria cuenta = new CuentaBancaria();

        Retiro reti = new Retiro(1500, cuenta);

        Ingreso ing = new Ingreso(700, cuenta);

        Retiro reti2 = new Retiro(300, cuenta);

        Thread hiloRetiro1 = new Thread(reti);
        Thread hiloIngreso1 = new Thread(ing);
        Thread hiloRetiro2 = new Thread(reti2);

        hiloRetiro1.start();
        hiloRetiro2.start();
        Thread.sleep(2000);
        hiloIngreso1.start();


    }


}
