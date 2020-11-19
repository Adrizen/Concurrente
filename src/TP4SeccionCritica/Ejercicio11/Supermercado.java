
package TP4SeccionCritica.Ejercicio11;

import java.util.concurrent.locks.ReentrantLock;
import static Colores.Colores.*;
/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Supermercado {
    private ReentrantLock lockCajera;
    
    public Supermercado(){
        lockCajera = new ReentrantLock();
    }
    
    // Métodos del Cliente.
    public void ponerseEnLaFila(String nombreCliente, int[] carrito){
        System.out.println(GREEN_BOLD + nombreCliente + " se pone en la fila y espera a ser atendido por la Cajera" + RESET);
        lockCajera.lock();
        procesarCompra(carrito);
        lockCajera.unlock();
    }
    
    
    // Métodos de la Cajera.
    public void procesarCompra(int[] carrito) {
        long initialTime = System.currentTimeMillis();
        System.out.println(CYAN_BOLD + "La Cajera comienza a procesar la compra del Cliente" + RESET);
        for (int i = 0; i < carrito.length; i++) {
            try {
                Thread.sleep(carrito[i] * 1000);
            } catch (InterruptedException e) { }
            System.out.println("Procesado el producto " + (i + 1) + "->Tiempo: " + (System.currentTimeMillis() - initialTime) / 1000 + "seg");
        }
        System.out.println(CYAN_BOLD + "LA CAJERA HA TERMINADO DE PROCESAR EN EL TIEMPO: " + (System.currentTimeMillis() - initialTime) / 1000 + "seg" + RESET);
    }
    
    
    
}
