package EjerciciosTeoria.Parte3.Ejercicio2;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class main {

    public static void main(String[] args) {
        Jaula jaulita = new Jaula(); // Creo la jaulita.

        // Creo, inicializo y arranco los Hamsters.
        for (int i = 0; i < 4; i++) {
            new Thread(new Hamster("Hamster" + i, jaulita)).start();
        }
    }

}
