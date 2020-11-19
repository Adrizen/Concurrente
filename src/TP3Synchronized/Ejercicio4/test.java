
package TP3Synchronized.Ejercicio4;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    
    public static void main(String[] args) { 
        
        Surtidor surtidor = new Surtidor();
        Auto[] autos = new Auto[5];
        Thread[] hilos = new Thread[5];
        
        for (int i = 0; i < autos.length; i++) { // Se crean los autos y los hilos. Un arreglo para cada uno.
            autos[i] = new Auto("AA0"+i,2020,"Marca"+i,200,surtidor);
            hilos[i] = new Thread(autos[i],"Auto0"+i);
        }
        
        for (int i = 0; i < hilos.length; i++) { // Se inician los hilos.
            hilos[i].start();
        }
    }
    
}
