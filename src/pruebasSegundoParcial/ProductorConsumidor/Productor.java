
package pruebasSegundoParcial.ProductorConsumidor;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Productor implements Runnable{
    private Buffer buffer;
    private String nombre;
    
    public Productor(String n, Buffer b){
        nombre = n;
        buffer = b;
    }
    
    public void run() {
        while (true) {
            try {
                buffer.producir(nombre);
            } catch (InterruptedException e) { }
        }
    }
    
}
