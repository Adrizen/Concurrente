
package TP2ThreadRunnable;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class testPunto6 {

    public static void main(String[] args) {
        
        //Test para Cajera (no Thread) y Cliente.
        Cliente cliente1 = new Cliente("Cliente 1", new int[] {2,2,1,5,2,3});
        Cliente cliente2 = new Cliente("Cliente 2", new int[] {1,3,5,1,1});
        //Tiempo inicial de referencia.
        long initialTime = System.currentTimeMillis();
        
        /*Cajera cajera1 = new Cajera("Cajera 1");
        cajera1.procesarCompra(cliente1, initialTime);
        cajera1.procesarCompra(cliente2, initialTime); */
        
        //Test para CajeraThread y Cliente.
        /*CajeraThread cajera1 = new CajeraThread("Cajera 1",cliente1,initialTime);
        CajeraThread cajera2 = new CajeraThread("Cajera 2",cliente2,initialTime);
        cajera1.start();
        cajera2.start();*/
        
        //Test para CajeraRunnable y Cliente.
        CajeraRunnable cajera1 = new CajeraRunnable("Cajera 1",cliente1,initialTime);
        CajeraRunnable cajera2 = new CajeraRunnable("Cajera 2",cliente2,initialTime);
        Thread hilo1 = new Thread(cajera1);
        Thread hilo2 = new Thread(cajera2);
        hilo1.start();
        hilo2.start();
        
    }
}
