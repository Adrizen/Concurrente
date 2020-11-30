
package EjerciciosTeoria.Parte5.CyclicBarrier;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class VendedorTicket extends Persona{
    
    public VendedorTicket(String nom, TrenTuristico t){
        super(nom,t);
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                tren.venderTicket(nombre);
            }
        } catch (InterruptedException e) { }

    }
}
