
package TP3Synchronized.Ejercicio3;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Letra extends Thread {

    private char letra;
    private int cantidadIteracion;
    private int cantidadTotal;
    private Prioridad prioridad;

    public Letra(char l, int cantIteracion, Prioridad prioridad) {
        this.letra = l;
        this.cantidadIteracion = cantIteracion;
        this.prioridad = prioridad;
        this.cantidadTotal = 2; //Polemico
    }
        
    public void run() {
        while (cantidadTotal > 0) {
            if (prioridad.getLetra() == letra) {
                prioridad.imprimir(cantidadIteracion);
                cantidadTotal--;
            } else {
                try {
                    Thread.sleep(1000); // No es mi turno, vuelvo más tarde.
                    run();
                } catch (InterruptedException e) {  }
            }
        }
    }

}