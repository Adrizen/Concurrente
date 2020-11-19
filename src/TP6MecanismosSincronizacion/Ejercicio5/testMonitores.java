
package TP6MecanismosSincronizacion.Ejercicio5;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class testMonitores {

    public static void main(String[] args) {
        GestionaTransitoMonitor gestor = new GestionaTransitoMonitor(5);

        for (int i = 0; i < 0; i++) {
            new Thread(new CocheMonitor("CocheNorte" + i,"norte",gestor)).start();
        }

        for (int i = 0; i < 6; i++) {
            new Thread(new CocheMonitor("CocheSur" + i,"sur",gestor)).start();
        }

    }
    
}
