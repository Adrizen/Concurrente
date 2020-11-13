
package TP6MecanismosSincronizacion.Ejercicio2;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Medidor implements Runnable{
    private GestorSala gestor;
    
    public Medidor(GestorSala g){
        gestor = g;
    }
    
    public void run(){
        while (true){
            try {
                gestor.notificarTemperatura(0);
            } catch (InterruptedException e) { }
        }
    }
    
    
}
