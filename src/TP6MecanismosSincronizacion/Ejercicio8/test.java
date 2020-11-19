package TP6MecanismosSincronizacion.Ejercicio8;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        CentroHemoterapia centro = new CentroHemoterapia(2, 1); // (numeroCamillas, numeroRevistas)

        for (int i = 0; i < 15; i++) {
            new Thread(new Donante("Donante" + i, centro)).start();
        }
    }
}
