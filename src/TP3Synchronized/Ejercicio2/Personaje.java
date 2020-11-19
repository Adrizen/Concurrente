package TP3Synchronized.Ejercicio2;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Personaje implements Runnable {

    private PuntosDeVida puntosDeVida = new PuntosDeVida();

    public void run() {
        try {
            if (Thread.currentThread().getName().equals("Orco")) {
                puntosDeVida.accion(-3);
            } else {
                puntosDeVida.accion(3);
            }
        } catch (InterruptedException e) {  }
    }
    
}
