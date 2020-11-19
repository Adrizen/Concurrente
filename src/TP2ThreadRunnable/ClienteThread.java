package TP2ThreadRunnable;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class ClienteThread extends Thread {

    public void run() {
        System.out.println("Soy " + Thread.currentThread().getName());
        Recurso.uso();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }
}
