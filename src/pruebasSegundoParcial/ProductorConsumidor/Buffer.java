
package pruebasSegundoParcial.ProductorConsumidor;
import static Colores.Colores.*;
/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Buffer {
    private int productos;
    private final int PRODUCTOS_MAXIMOS;
    
    public Buffer(int max){
        this.productos = 0;
        this.PRODUCTOS_MAXIMOS = max;
    }
    
    public synchronized void producir(String productor) throws InterruptedException{
        while (productos >= PRODUCTOS_MAXIMOS){
            this.wait();
        }
        productos++;
        System.out.println(GREEN_BOLD + productor + " produce: " + productos + RESET);
        this.notifyAll();
    }
    
    public synchronized void consumir(String consumidor) throws InterruptedException{
        while (productos <= 0){
            this.wait();
        }
        productos--;
        System.out.println(RED_BOLD + consumidor + " consume: " + productos + RESET);
        this.notifyAll();
    }
    
}
