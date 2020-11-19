package TP4SeccionCritica.Ejercicio6;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Letra implements Runnable{
    private Imprimir imprimir;
    private int cantidad;
    
    public Letra(Imprimir i, int cant){
        this.imprimir = i;
        this.cantidad = cant;
    }
    
    public void run() {
        for (int i = 0; i < imprimir.getCantidadTotal(); i++) {
            imprimir.imprimir(cantidad);
        }
    }
    
}
