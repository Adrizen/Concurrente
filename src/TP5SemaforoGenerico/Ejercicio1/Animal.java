
package TP5SemaforoGenerico.Ejercicio1;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Animal implements Runnable{
    private Comedor comedero;
    private String tipo;
    private String nombre;

    public Animal(String n, String t, Comedor c) {
        this.nombre = n;
        this.tipo = t;
        this.comedero = c;
    }

    @Override
    public void run() {
        while (true) {
            comedero.entrar(tipo);
            comedero.comer(nombre);
            comedero.salir(nombre, tipo);
            }
        }
    }

