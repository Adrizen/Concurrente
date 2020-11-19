
package EjerciciosTeoria.Parte3.Ejercicio3;
/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        Barberia barberia = new Barberia(1);
        
        // Creo, inicializo y arranco a los Clientes.
        for (int i = 0; i < 3; i++) {
            new Thread(new Cliente("Cliente" + i,barberia)).start();
        }

        // Creo, inicializo y arranco al Barbero.
        new Thread(new Barbero("Barbero0", barberia)).start();
    }
    
}
