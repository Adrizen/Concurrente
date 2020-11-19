
package TP6MecanismosSincronizacion.Ejercicio4;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Soldado implements Runnable{
    private String nombre;
    private boolean quierePostre;
    private boolean quiereGaseosa;
    private Cuartel cuartel;
    
    public Soldado(String n, boolean quiereGas, boolean quierePos, Cuartel c){
        nombre = n;
        quierePostre = quierePos;
        quiereGaseosa = quiereGas;
        cuartel = c;
    }
    
    
    public void run(){
        try{
            cuartel.entrar(nombre);
            cuartel.tomarComida(nombre);
            if (quiereGaseosa){
                cuartel.tomarAbridor(nombre);
            }
            if (quierePostre){
                cuartel.tomarPostre(nombre);
            }
            cuartel.comer(nombre);
            cuartel.salir(nombre,quierePostre);
        } catch(InterruptedException e) { }
    }
    
}
