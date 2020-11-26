
package TP6MecanismosSincronizacion.Ejercicio7;


/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {


    public static void main(String[] args) {
        Pasteleria pasteleria = new Pasteleria(10); // (pesoMaximo)
        
        // Hornos A.
        for (int i = 0; i < 1; i++) {
            new Thread(new Horno("Horno"+i,"A",1,pasteleria)).start();
        } 
        
        // Hornos B.
        for (int i = 0; i < 1; i++) {
            new Thread(new Horno("Horno"+i,"B",2,pasteleria)).start();
        }
        
        // Hornos C.
        for (int i = 0; i < 1; i++) {
            new Thread(new Horno("Horno"+i,"C",3,pasteleria)).start();
        }
        
        // Empaquetador.
        for (int i = 0; i < 3; i++) {
            new Thread(new Empaquetador("Empaquetador"+i,pasteleria)).start();
        }
        
        // Reponedor.
        for (int i = 0; i < 1; i++) {
            new Thread(new Reponedor("Reponedor"+i,pasteleria)).start();
        }
        
    }
    
}
