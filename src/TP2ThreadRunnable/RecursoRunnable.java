package TP2ThreadRunnable;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class RecursoRunnable {

    static void uso() {
        Thread t = Thread.currentThread();
        System.out.println("En Recurso: Soy " + t.getName());
    }

    public static void main(String[] args) {
        ClienteRunnable juan = new ClienteRunnable();
        ClienteRunnable ines = new ClienteRunnable();

        Thread t1 = new Thread(juan); //"Linkeo" mi hilo con mi objeto Runnable.
        t1.setName("Juan Lopez");
        Thread t2 = new Thread(ines); // "Linkeo" mi hilo con mi objeto Runnable.
        t2.setName("Ines García");

        t1.start();
        t2.start();
    }

}
