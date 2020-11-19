
package TP4SeccionCritica.Ejercicio8;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Atleta implements Runnable{
    private Testigo testigo;
    
    public Atleta(Testigo test){
        this.testigo = test;
    }
    
    public void run(){
        testigo.correr();
    }
}
