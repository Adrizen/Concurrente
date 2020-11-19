package TP4SeccionCritica.Ejercicio9;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Cliente implements Runnable{
    private Taxi taxi;
    
    public Cliente(Taxi t){
        this.taxi = t;
    }
    
    public void run(){
        taxi.buscarYSubirseTaxi();
    }
}
