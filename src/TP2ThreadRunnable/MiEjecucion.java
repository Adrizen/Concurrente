package TP2ThreadRunnable;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class MiEjecucion extends Thread {
    public void run(){
        ir();
    }
    public void ir(){
        hacerMas();
    }
    public void hacerMas(){
        System.out.println("En la pila");
    }
}

class ThreadTesting {
    public static void main (String[] args){
        Thread miHilo = new MiEjecucion();
        miHilo.start();
        try {
            Thread.currentThread().sleep(100);
        } catch (InterruptedException e){ }
        System.out.println("En el main");
    }
}
