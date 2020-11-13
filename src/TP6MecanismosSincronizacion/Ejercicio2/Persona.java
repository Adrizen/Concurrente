
package TP6MecanismosSincronizacion.Ejercicio2;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Persona implements Runnable{
    private GestorSala gestor;
    private boolean jubilado;
    
    public Persona(boolean j, GestorSala g){
        jubilado = j;
        gestor = g;
    }

    public void run() {
        try {
            if (jubilado) {
                gestor.entrarSalaJubilado();
            } else {
                gestor.entrarSala();
            }
            gestor.visitar(); // Agregado (No estaba en el PDF.)
            gestor.salirSala();
        } catch (InterruptedException e) { }

    }

}
