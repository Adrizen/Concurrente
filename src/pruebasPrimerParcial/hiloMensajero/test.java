package pruebasPrimerParcial.hiloMensajero;

import pruebasPrimerParcial.hiloMensajero.HiloMensajero;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        Sender sender = new Sender();
        HiloMensajero S1 = new HiloMensajero(" Hola ",sender);
        HiloMensajero S2 = new HiloMensajero(" Chau ",sender);
        
        // Start two threads of ThreadedSend type 
        S1.start();
        S2.start();

        // wait for threads to end 
        try {
            S1.join();
            S2.join();
        } catch (Exception e) { }
        


    }
    
    /*
    Si clavo un "synchronized" en la cabecera del método 'send' de 'Sender'. Parece que cumple el mismo rol que hacer un bloque de synchronized
    en el run() de 'HiloMensajero'.
    Es más, si se quisiera synchronizar solo una parte de código, se podría usar un bloque synchronized en método 'send' de Sender y como objeto
    del bloque se coloca 'this'. Ya que este 'this' hace referencia al objeto de tipo Sender (además, estoy dentro de su clase).
    */

}
