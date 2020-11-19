
package PereyraFAI1943;
import static PereyraFAI1943.Colores.*;
/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Pasajero extends Persona{

    public Pasajero(String nom, TrenTuristico t) {
        super(nom, t);
    }
    
    @Override
    public void run(){
        while (true){
            if (tren.reservarAsiento(nombre)){
                tren.comprarTicket(nombre);
                tren.bajarse(nombre);
            }
            pasear();
        }
    }
    
    public void pasear(){
        try{
            System.out.println(RED_BOLD + "El " + nombre + " pasea por el lugar un rato." + RESET);
            Thread.sleep(10000);
        } catch(InterruptedException e) {}
    }
    
}
