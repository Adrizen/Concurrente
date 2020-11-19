
package TP2ThreadRunnable;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Cliente {
    private String nombre;
    private int[] carroCompra;
    
    public Cliente(String nom, int[] carrito){
        this.nombre = nom;
        this.carroCompra = carrito;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public int[] getCarroCompra(){
        return this.carroCompra;
    }
}
