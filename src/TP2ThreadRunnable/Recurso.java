package TP2ThreadRunnable;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Recurso {

    static void uso() {
        Thread t = Thread.currentThread();
        System.out.println("En Recurso: Soy " + t.getName());
    }
    
    public static void main(String[] args){
        ClienteThread juan = new ClienteThread();
        juan.setName("Juan Lopez");
        ClienteThread ines = new ClienteThread();
        ines.setName("Ines García");
        ClienteThread larrata = new ClienteThread();
        larrata.setName("Rodriguez Larrata");
        ClienteThread kicilove = new ClienteThread();
        kicilove.setName("Kici love");
        juan.start();
        ines.start();
        larrata.start();
        kicilove.start();
    }
}


