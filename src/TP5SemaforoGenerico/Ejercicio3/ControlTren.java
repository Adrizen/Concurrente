
package TP5SemaforoGenerico.Ejercicio3;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class ControlTren implements Runnable{
    private TrenTuristico tren;
    
    public ControlTren(TrenTuristico t){
        this.tren = t;
    }
    
    public void run(){
        while(true){
            tren.viajar();
        }
    }
}
