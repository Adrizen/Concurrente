
package TP6MecanismosSincronizacion.Ejercicio6;


/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Visitante extends Persona{
    private String tipo;
    
    public Visitante(String n,Observatorio o, String t){
        super(n,o);
        this.tipo = t;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                observatorio.entrarVisitante(nombre,tipo);
                observatorio.estudiarEstrellas(nombre);
                observatorio.salirVisitante(nombre);
            } catch (InterruptedException e) { }
            
        }
    }

}
