
package TP6MecanismosSincronizacion.Ejercicio6;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Mantenimiento extends Persona{
    
    public Mantenimiento(String n, Observatorio o){
        super(n,o);
    }
    
    @Override
    public void run() {
        while (true) {
            try{
                observatorio.entrarMantenimiento(nombre);
                observatorio.asearMuseo(nombre);
                observatorio.salirMantenimiento(nombre);
            } catch(InterruptedException e) { }
        }
    }

}
