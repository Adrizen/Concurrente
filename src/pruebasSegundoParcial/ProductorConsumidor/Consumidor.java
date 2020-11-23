
package pruebasSegundoParcial.ProductorConsumidor;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Consumidor implements Runnable{
    private Buffer buffer;
    private String nombre;
    
    public Consumidor(String n, Buffer b){
        nombre = n;
        buffer = b;
    }

    public void run() {
        while (true) {
            try {
                buffer.consumir(nombre);
            } catch (InterruptedException e) { }
        }

    }

}
