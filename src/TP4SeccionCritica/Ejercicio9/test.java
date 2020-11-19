
package TP4SeccionCritica.Ejercicio9;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        
        Taxi taxi = new Taxi();
        
        Taxista taxista = new Taxista(taxi);
        Thread hiloTaxista = new Thread(taxista,"Taxista");
        Cliente[] clientes = new Cliente[4];
        Thread[] hilos = new Thread[4];
        
        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Cliente(taxi);
            hilos[i] = new Thread(clientes[i],"Cliente"+i);
        }
        
        for (int i = 0; i < hilos.length; i++) {
            hilos[i].start();
        }
        hiloTaxista.start();
        
    }
    
}
