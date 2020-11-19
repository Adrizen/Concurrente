
package TP3Synchronized.Ejercicio3;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {


    public static void main(String[] args) {
        Prioridad pr = new Prioridad('A');
        Letra a = new Letra('A', 1, pr);
        Letra b = new Letra('B', 2, pr);
        Letra c = new Letra('C', 3, pr);
        
        a.start();
        b.start();
        c.start();
    }
    
}
