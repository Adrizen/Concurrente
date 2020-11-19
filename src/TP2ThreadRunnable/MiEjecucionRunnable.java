
package TP2ThreadRunnable;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class MiEjecucionRunnable implements Runnable{
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

class ThreadRunnableTesting{
    public static void main (String[] args){
        MiEjecucionRunnable miEjecucion = new MiEjecucionRunnable();
        Thread miHilo = new Thread(miEjecucion); // "Linkeo" mi hilo con mi objeto Runnable.
        miHilo.start();
        System.out.println("En el main");
        
    }
}