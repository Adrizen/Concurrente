
package TP3Synchronized.Ejercicio2;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class testEjercicio2 {
    public static void main(String[] args) {
        Personaje personaje = new Personaje();
        
        Thread orco = new Thread(personaje,"Orco");
        Thread curandero = new Thread(personaje,"Curandero");
        
        orco.start();
        curandero.start();
        
    }
}
