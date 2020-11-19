
package TP6MecanismosSincronizacion.Ejercicio5;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class testSemaforo {


    public static void main(String[] args) {
        GestionaTransitoSemaforos gestor = new GestionaTransitoSemaforos();
        
              for (int i = 0; i < 10; i++) {
            new Thread(new CocheSemaforos("CocheSur"+i,"sur",gestor)).start();
        }
        
        for (int i = 0; i < 10; i++) {
            new Thread(new CocheSemaforos("CocheNorte"+i,"norte",gestor)).start();
        }
        
  
        
    }
    
}
