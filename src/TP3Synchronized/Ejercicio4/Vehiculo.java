
package TP3Synchronized.Ejercicio4;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Vehiculo {
    protected String patente;
    protected int modelo;
    protected String marca;
    protected int capacidadCombustible;
    protected int litrosCombustible;
    
    public Vehiculo(String p, int m, String mar, int capaC){
        this.patente = p;
        this.modelo = m;
        this.marca = mar;
        this.capacidadCombustible = capaC;
        this.litrosCombustible = this.capacidadCombustible;
    }
}
