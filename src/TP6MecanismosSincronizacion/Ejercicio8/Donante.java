package TP6MecanismosSincronizacion.Ejercicio8;


/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Donante implements Runnable{
    private String nombre;
    private CentroHemoterapia centro;
    
    public Donante(String n, CentroHemoterapia c){
        nombre = n;
        centro = c;
    }
    
    public void run(){
        //while (true){
            try {
                centro.entrarDonante(nombre);
                centro.donarSangre(nombre);
                centro.salirDonante(nombre);
            } catch (InterruptedException e) { }
        //}
    }
    
}
