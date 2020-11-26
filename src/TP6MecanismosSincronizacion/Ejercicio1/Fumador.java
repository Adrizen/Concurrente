
package TP6MecanismosSincronizacion.Ejercicio1;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Fumador implements Runnable{
    private int id;
    private SalaFumadores sala;
    
    public Fumador(int id, SalaFumadores sala){
        this.id = id;
        this.sala = sala;
    }
    
    public void run(){
        while (true){
            
            try{
                sala.entrarFumar(id);
                System.out.println("Fumador " + id + " está fumando.");
                Thread.sleep(4000);
                sala.terminarFumar();
             } catch(InterruptedException e) {}
        }
    }
    
}
