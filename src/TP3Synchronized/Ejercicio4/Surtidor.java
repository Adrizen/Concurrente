
package TP3Synchronized.Ejercicio4;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Surtidor {
    
    public synchronized void llenarTanque(){
        System.out.println(Thread.currentThread().getName() + " va a empezar a cargar combustible");
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {  }
        System.out.println(Thread.currentThread().getName() + " : listo");
        
    }
}
