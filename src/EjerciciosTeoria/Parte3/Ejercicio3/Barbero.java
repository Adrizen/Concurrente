
package EjerciciosTeoria.Parte3.Ejercicio3;
import static Colores.Colores.*;
/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Barbero implements Runnable{
    private String nombre;
    private Barberia barberia;
    
    public Barbero(String nom, Barberia barb){
        this.nombre = nom;
        this.barberia = barb;
    }
    
    public void run() {
        while (true){
            barberia.cortarPelo(nombre);
        }
    }
    
    
}
