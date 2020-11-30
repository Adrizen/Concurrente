
package EjerciciosTeoria.Parte5.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Pasajero extends Persona{
    
    
    public Pasajero(String nom, TrenTuristico t){
        super(nom,t);
    }
    
    @Override
    public void run() {
        try {
            tren.reservarAsiento(nombre);
            tren.comprarTicket(nombre);
            tren.subirseTren(nombre);
            tren.bajarseTren();
        } catch (InterruptedException e) {} catch (BrokenBarrierException e) {}
    }
    
}

