
package TP6MecanismosSincronizacion.Ejercicio6;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Persona implements Runnable{
    protected String nombre;
    protected Observatorio observatorio;
    
    public Persona(String n, Observatorio o){
        this.nombre = n;
        this.observatorio = o;
    }
    
    public void run(){
        System.out.println("subclass");
    }
    
}
