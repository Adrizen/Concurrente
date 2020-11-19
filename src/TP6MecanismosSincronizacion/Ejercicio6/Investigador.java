
package TP6MecanismosSincronizacion.Ejercicio6;


/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Investigador extends Persona{
    
    public Investigador(String n, Observatorio o){
        super(n,o);
    }
    
    @Override
    public void run(){
        while (true){
            try {
                observatorio.entrarInvestigador(nombre);
                observatorio.investigar(nombre);
                observatorio.salirInvestigador(nombre);
            } catch (InterruptedException e) { }
        }
    }
    
}
