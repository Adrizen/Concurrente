
package TP4SeccionCritica.EjemploSemaphore;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        
        Dato dato1 = new Dato(0);
        ProcesoI proceso = new ProcesoI(dato1);
        Thread hilo1 = new Thread(proceso);
        
        hilo1.start();
    }
    
}
