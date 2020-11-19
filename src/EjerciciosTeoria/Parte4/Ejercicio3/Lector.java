
package EjerciciosTeoria.Parte4.Ejercicio3;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Lector implements Runnable{
    private Libro libro;
    private String nombre;
    
    public Lector(String n, Libro l){
        this.nombre = n;
        this.libro = l;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                libro.hayEscrito(nombre);
                libro.empezarLeer(nombre);
                libro.terminarLeer(nombre);
            } catch (InterruptedException ex) {}
        }
    }
}
