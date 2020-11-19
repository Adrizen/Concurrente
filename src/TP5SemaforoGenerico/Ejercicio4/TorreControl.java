
package TP5SemaforoGenerico.Ejercicio4;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class TorreControl implements Runnable{
    private Aeropuerto aeropuerto;
    
    public TorreControl(Aeropuerto a){
        this.aeropuerto = a;
    }

    public void run() {
        while (true) {
            aeropuerto.autorizar();
        }
    }
}
