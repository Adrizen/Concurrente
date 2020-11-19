
package TP5SemaforoGenerico.Ejercicio3;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Pasajero extends Persona{
    
    
    public Pasajero(String nom, TrenTuristico t){
        super(nom,t);
    }
    
    @Override
    public void run(){  // Solo viajan una vez pero se podría cambiar poniendo un while(true) simplemente.
        tren.reservarAsiento(nombre);   // Si se acaban los asientos, se queda esperando en un semáforo interno llamado "asientos".
        tren.comprarTicket(nombre);
        tren.subirse(nombre);
    }
}
