
package TP2ThreadRunnable;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
class RunnableCdor implements Runnable{
    
    Dato unContador;
    
    public RunnableCdor(Dato elCdor){
        unContador = elCdor;
    }
    
    public void run(){
        for (int i = 0; i < 10000; ++i) {
            unContador.contar();
        }
    }
}

public class Contador {
    public static void main(String[] args) throws InterruptedException{
        final Dato unContador = new Dato();
        
        RunnableCdor unRunCdor = new RunnableCdor(unContador);
        
        Thread h1 = new Thread(unRunCdor);
        Thread h2 = new Thread(unRunCdor);
        h1.start();
        h2.start();
        h1.join();
        h2.join();
        System.out.println("en main- "+unContador.obtenerValor());
    }
}
