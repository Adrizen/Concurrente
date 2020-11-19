
package EjerciciosTeoria.Parte3.Ejercicio3;
import static Colores.Colores.*;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Cliente implements Runnable{
    private Barberia barberia;
    private String nombre;
    
    public Cliente(String nom, Barberia barb){
        this.nombre = nom;
        this.barberia = barb;
    }

    @Override
    public void run() {
        while (true){
            barberia.cortarsePelo(nombre);
            barberia.irse(nombre);
            try {
                Thread.sleep(6000);
            } catch (InterruptedException ex) { }
        }
    }

}
