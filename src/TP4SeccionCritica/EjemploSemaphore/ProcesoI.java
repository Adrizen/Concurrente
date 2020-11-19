
package TP4SeccionCritica.EjemploSemaphore;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class ProcesoI implements Runnable{
    private Dato unDato;
    
    public ProcesoI(Dato datito){
        this.unDato = datito;
    }
    
    public void run(){
        System.out.println("Valor de unDato antes del for: "+unDato.getDato());
        for (int i = 0; i < 10000; i++) {
            unDato.incrementar();
        }
        System.out.println("Valor de unDato después del for: "+unDato.getDato());
    }
}
