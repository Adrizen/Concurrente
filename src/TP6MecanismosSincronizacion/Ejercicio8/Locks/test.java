
package TP6MecanismosSincronizacion.Ejercicio8.Locks;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        CentroHemoterapia centro = new CentroHemoterapia(2,0); // (numCamillas, numRevistas)
        
        for (int i = 0; i < 5; i++) {
            new Thread(new Donante("Donante"+i,centro)).start();
        }
        
    }
    
}
