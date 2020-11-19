
package TP3Synchronized.Ejercicio1;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class CuentaBanco {
    private int balance = 50;
    
    public synchronized int getBalance(){
        return balance;
    }
    
    public synchronized void retiroBancario(int retiro){
        balance = balance - retiro;
    }

    public synchronized void hacerRetiro(int cantidad) throws InterruptedException {
        if (balance >= cantidad) {
            System.out.println(Thread.currentThread().getName() + " está realizando un retiro de: " + cantidad + ".");
            Thread.sleep(1000); //Quizas sería mejor quitar esto. Es un sleep dentro de un método synchroniado.
            this.retiroBancario(cantidad);
            System.out.println(Thread.currentThread().getName() + ": Retiro realizado.");
            System.out.println(Thread.currentThread().getName() + ": Los fondos son de: " + balance);
        } else {
            System.out.println("No hay suficiente dinero en la cuenta para realizar el retiro Sr." + Thread.currentThread().getName());
            System.out.println("Su saldo actual es de " + balance);
            Thread.sleep(1000); //Quizas sería mejor quitar esto. Es un sleep dentro de un método synchroniado.
        }
    }
}
