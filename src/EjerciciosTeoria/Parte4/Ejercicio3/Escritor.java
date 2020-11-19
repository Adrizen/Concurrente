
package EjerciciosTeoria.Parte4.Ejercicio3;



/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Escritor implements Runnable{
    private String nombre;
    private Libro libro;
    
    public Escritor(String n, Libro l){
        this.nombre = n;
        this.libro = l;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                libro.empezarEscribir(nombre);
                libro.terminarEscribir(nombre);
            } catch (InterruptedException ex)  { }
        }
    }
}
