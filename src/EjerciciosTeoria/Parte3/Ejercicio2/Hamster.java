
package EjerciciosTeoria.Parte3.Ejercicio2;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Hamster implements Runnable{
    private Jaula jaulita;
    private String nombre;
    
    public Hamster(String n, Jaula j){
        this.jaulita = j;
        this.nombre = n;
    }
    
    public void run(){
        jaulita.empezarAComer(nombre);
        jaulita.rodar(nombre);
    }
}
