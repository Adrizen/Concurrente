
package TP4SeccionCritica.Ejercicio14;
import static Colores.Colores.*;
/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Cocinero extends Persona{
    
    public Cocinero(String nom, Confiteria conf) {
        super(nom, conf);
    }
    
    @Override
    public void run(){
        while (true){
            confiteria.prepararComida(nombre);
            System.out.println(CYAN_BOLD + nombre + " aprovecha a dedicarse a su hobbie mientras no hay cosas que hacer." + RESET);
        }
    }
    
}
