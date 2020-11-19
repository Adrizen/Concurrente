
package TP4SeccionCritica.EjercicioBarberoDormilon;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Barbero implements Runnable{
    private Barberia barberia;
    
    public Barbero(Barberia barb){
        this.barberia = barb;
    }
    
    public void run(){
            barberia.cortarPelo();

    }
}
