
package TP4SeccionCritica.EjercicioBarberoDormilon;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Cliente implements Runnable{
    private Barberia barberia;
    
    public Cliente(Barberia barb){
        this.barberia = barb;
    }
    
    public void run(){
        barberia.entrarBarberia();
    }
    
}
