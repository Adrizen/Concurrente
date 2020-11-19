
package TP2ThreadRunnable;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class ClienteRunnable implements Runnable{
    
    public void run(){
        System.out.println("Soy "+Thread.currentThread().getName());
        RecursoRunnable.uso();
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {  }
    }
}
