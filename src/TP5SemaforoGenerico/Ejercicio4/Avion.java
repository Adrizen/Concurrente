
package TP5SemaforoGenerico.Ejercicio4;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Avion implements Runnable{
    private String numeracion;
    private String tipo;
    private Aeropuerto aeropuerto;
    
    public Avion(String n, String t, Aeropuerto a){
        this.numeracion = n;
        this.aeropuerto = a;
        this.tipo = t;
    }

    public void run() {
        aeropuerto.informarTorre(numeracion, tipo);
        aeropuerto.realizarAccion(numeracion, tipo);
    }
}

