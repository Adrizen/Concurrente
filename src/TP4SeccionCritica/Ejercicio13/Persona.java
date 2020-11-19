
package TP4SeccionCritica.Ejercicio13;
/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Persona implements Runnable{

    
    //Variables.
    protected String nombre;
    protected Confiteria confiteria;

    
    public Persona(String nom, Confiteria conf){
        this.nombre = nom;
        this.confiteria = conf;
    }
    
    public void run(){
        System.out.println("subclassResponsibility"); // ahre Smalltalk
    }
}
