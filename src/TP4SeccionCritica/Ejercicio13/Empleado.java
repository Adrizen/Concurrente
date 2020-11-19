
package TP4SeccionCritica.Ejercicio13;
import static Colores.Colores.*;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Empleado extends Persona{
    
    public Empleado(String nom, Confiteria conf){
        super(nom,conf);
    }
     
    
    @Override
    public void run() {
        while (true) {
            if (confiteria.sentarse()) {
                System.out.println(GREEN_BOLD + nombre + " logra sentarse en el único lugar disponible y empieza a ojear el menú" + RESET);
                confiteria.pedir(nombre);
                confiteria.comer(nombre);
                System.out.println(GREEN_BOLD + nombre + " deja el asiento." + RESET);
                confiteria.dejarAsiento();
                laburar();
            } else {
                laburar();
            }
        }
    }
    
    public void laburar(){
        try {
            System.out.println(RED + nombre + " está trabajando" + RESET);
            Thread.sleep(10000);
        } catch (InterruptedException e) { }
    }
}
