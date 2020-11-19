package TP4SeccionCritica.Ejercicio14;
import static Colores.Colores.*;
/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Empleado extends Persona {

    public Empleado(String nom, Confiteria conf) {
        super(nom, conf);
    }
    
    @Override
    public void run() {
        while (true) {
            if (confiteria.intentarSentarse(nombre)) {
                confiteria.consumir(nombre);
                confiteria.irse(nombre);
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
