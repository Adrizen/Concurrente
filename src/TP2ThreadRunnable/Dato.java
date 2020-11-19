
package TP2ThreadRunnable;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Dato {
    private int valor;
    
    public synchronized void contar(){
        valor = ++valor;
    }
    
    int obtenerValor(){
        return valor;
    }
}
