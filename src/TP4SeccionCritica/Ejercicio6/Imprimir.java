package TP4SeccionCritica.Ejercicio6;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Imprimir {
    private Semaphore semA;
    private Semaphore semB;
    private Semaphore semC;
    private int cantidadTotal;
    
    
    public Imprimir(){
        this.semA = new Semaphore(1);           // Indica si le toca imprimir a la letra A. 1 = le toca imprimir.
        this.semB = new Semaphore(0);           // Indica si le toca imprimir a la letra B.
        this.semC = new Semaphore(0);           // Indica si le toca imprimir a la letra C.
        this.cantidadTotal = 2;
    }

    public void imprimir(int cantidad) {
        try {
            switch (Thread.currentThread().getName()) {
                case "A":
                    semA.acquire();
                    mostrar(cantidad, 'A');
                    semB.release();
                    break;
                case "B":
                    semB.acquire();
                    mostrar(cantidad, 'B');
                    semC.release();
                    break;
                case "C":
                    semC.acquire();
                    mostrar(cantidad, 'C');
                    semA.release();
                    break;
            }
        } catch (InterruptedException e) {  }
    }

    public int getCantidadTotal(){
        return this.cantidadTotal;
    }

    public void mostrar(int cantidad, char letra){
        for (int i = 0; i < cantidad; i++) {
            System.out.println(letra);
        }
    }
}

