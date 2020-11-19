
package TP4SeccionCritica.Ejercicio11;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Cliente implements Runnable{
    private String nombre;
    private int[] carroCompra;
    private Supermercado supermercado;

    public Cliente(String nom, int[] array, Supermercado supermerk2){
        this.nombre = nom;
        this.carroCompra = array;
        this.supermercado = supermerk2;
    }

    public void run() {
        while (true) {
            supermercado.ponerseEnLaFila(nombre, carroCompra);
        }
    }
    
}
