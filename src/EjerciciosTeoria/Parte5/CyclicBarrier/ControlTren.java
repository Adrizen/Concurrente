
package EjerciciosTeoria.Parte5.CyclicBarrier;

import static Colores.Colores.RESET;
import static Colores.Colores.YELLOW_BOLD;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class ControlTren implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println(YELLOW_BOLD + "El tren está lleno y puede partir." + RESET);
            Thread.sleep(5000);     // Tren viajan2.
            System.out.println(YELLOW_BOLD + "FIN DE RECORRIDO." + RESET);
        } catch (InterruptedException e) {}

    }

}
