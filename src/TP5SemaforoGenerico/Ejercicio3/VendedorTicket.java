
package TP5SemaforoGenerico.Ejercicio3;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class VendedorTicket extends Persona{
    
    public VendedorTicket(String nom, TrenTuristico t){
        super(nom,t);
    }
    
    @Override
    public void run(){
        while (true){
            tren.venderTicket(nombre);
        }
    }
}
