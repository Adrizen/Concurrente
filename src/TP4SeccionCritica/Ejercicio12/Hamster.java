
package TP4SeccionCritica.Ejercicio12;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Hamster implements Runnable{
    private String nombre;
    private Jaula jaula;
    
    public Hamster(String nom, Jaula jaulita){
        this.nombre = nom;
        this.jaula = jaulita;
    }
    
    @Override
    public void run() {
        jaula.usarPlato(nombre);
        jaula.usarRueda(nombre);
        jaula.usarHamaca(nombre);
    }
    
    
}
