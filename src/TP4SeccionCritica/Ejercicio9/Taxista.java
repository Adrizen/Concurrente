package TP4SeccionCritica.Ejercicio9;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Taxista implements Runnable{
    private Taxi taxi;
    
    public Taxista(Taxi t){
        this.taxi = t;
    }
    
    public void run(){
        taxi.conducir();
    }
}
