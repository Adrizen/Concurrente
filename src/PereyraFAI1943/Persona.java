
package PereyraFAI1943;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Persona implements Runnable{
    protected String nombre;
    protected TrenTuristico tren;
    
    public Persona(String nom, TrenTuristico t){
        this.nombre = nom;
        this.tren = t;
    }
    
    public void run(){
        System.out.println("subclassResponsibility");
    }
    
    
}
