
package PereyraFAI1943;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class ControlTren implements Runnable{
    private TrenTuristico tren;
    
    public ControlTren(TrenTuristico t){
        this.tren = t;
    }
    
    
    public void run(){   // Lo hice de está forma para que el while(true) no esté TODO el rato preguntando si el tren se llenó. Si no que solo pregunta cuando un Pasajero sube.
        while (true) {
            tren.hacerRecorrido();
        }
    }
}
