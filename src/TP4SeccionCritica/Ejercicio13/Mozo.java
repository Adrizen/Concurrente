
package TP4SeccionCritica.Ejercicio13;
import static Colores.Colores.*;
/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Mozo extends Persona{
    
    public Mozo(String nom, Confiteria conf) {
        super(nom, conf);
    }
    
    @Override
    public void run(){
        while (true){
            confiteria.prepararBebida(nombre);
            confiteria.servir(nombre);
            System.out.println(PURPLE_BOLD + nombre + " aprovecha a dedicarse a su hobbie mientras no hay cosas que hacer." + RESET);
        }
    }
    
}
