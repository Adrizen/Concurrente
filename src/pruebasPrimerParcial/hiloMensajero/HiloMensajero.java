
package pruebasPrimerParcial.hiloMensajero;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class HiloMensajero extends Thread {

    private String msg;
    private Sender sender;

    // Recieves a message object and a string 
    // message to be sent 
    public HiloMensajero(String m, Sender obj) {
        msg = m;
        sender = obj;
    }

    public void run() {
        // Only one thread can send a message 
        // at a time. 
        //synchronized (sender) {
            // synchronizing the snd object (recurso compartido por los hilos)
            sender.send(msg);
        //}
    }

}
