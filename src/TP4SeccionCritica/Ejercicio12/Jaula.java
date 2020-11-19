
package TP4SeccionCritica.Ejercicio12;

import java.util.concurrent.locks.ReentrantLock;
import static Colores.Colores.*;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Jaula {
    private final ReentrantLock plato;
    private final ReentrantLock rueda;
    private final ReentrantLock hamaca;
    
    public Jaula(){
        plato = new ReentrantLock();
        rueda = new ReentrantLock();
        hamaca = new ReentrantLock();
    }
    
    // Métodos que usaran los hamster.
    public void usarPlato(String nombreHamster) {   // Intenta ocupar el plato, si no puede se va a jugar.
        plato.lock();
        System.out.println(GREEN_BOLD + nombreHamster + " usa el plato." + RESET);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) { }
        System.out.println(GREEN_BOLD + nombreHamster + " terminó de comer y deja el plato." + RESET);
        plato.unlock();
    }
    
    public void usarRueda(String nombreHamster) {   // Intenta ocupar la rueda, si no puede se va a jugar.
        rueda.lock();
        System.out.println(PURPLE_BOLD + nombreHamster + " sube a la rueda y empieza a dar vueltas" + RESET);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) { }
        System.out.println(PURPLE_BOLD + nombreHamster + " terminó de usar la rueda y se baja." + RESET);
        rueda.unlock();
    }

    public void usarHamaca(String nombreHamster) {  // Intenta ocupar la hamaca, si no puede se va a jugar.
        hamaca.lock();
        System.out.println(CYAN_BOLD + nombreHamster + " se sube a la hamaca y descansa por un rato." + RESET);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) { }
        System.out.println(CYAN_BOLD + nombreHamster + " terminó de descansar en la hamaca y se va." + RESET);
        hamaca.unlock();
    }
    
    // Creo que podría usar directamente un lock(); en vez de los if (tryLock()) y evitar usar recursión. Al fin y al cabo no espe-
    // -cifica lo que hacen los hamster mientras no usar algún objeto. HECHO, VER EL HISTORIAL PARA LA VERSIÓN ANTERIOR QUE TIENE RECURSIÓN.
}
