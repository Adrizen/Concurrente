
package pruebasSegundoParcial.ProductorConsumidor;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        Buffer buffer = new Buffer(50);
        
        for (int i = 0; i < 3; i++) {
            new Thread(new Productor("Productor"+i,buffer)).start();
        }
        
        for (int i = 0; i < 10; i++) {
            new Thread(new Consumidor("Consumidor"+i,buffer)).start();
        }
        
    }
    
}
