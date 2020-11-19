
package TP4SeccionCritica.EjemploSemaphore;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Dato {
    private int dato;
    private Semaphore mutex;
    
    public Dato(int nro){
        this.dato = nro;
        this.mutex = new Semaphore(1);
    }
    
    public int getDato(){
        return this.dato;
    }
    
    public void incrementar() {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {  }
        this.dato++;
        mutex.release();
    }
}
