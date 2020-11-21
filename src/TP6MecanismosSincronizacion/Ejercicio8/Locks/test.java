
package TP6MecanismosSincronizacion.Ejercicio8.Locks;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        CentroHemoterapia centro = new CentroHemoterapia(1,1); // (numCamillas, numRevistas)
        
        for (int i = 0; i < 10; i++) {
            new Thread(new Donante("Donante"+i,centro)).start();
        }
        
    }
    
}
