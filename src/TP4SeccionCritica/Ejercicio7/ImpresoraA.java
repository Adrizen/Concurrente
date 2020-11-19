
package TP4SeccionCritica.Ejercicio7;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class ImpresoraA extends Impresora{
   
    
    public ImpresoraA(String nombreImpresora, Oficina oficina){
        super(nombreImpresora, oficina);
    }
    
    @Override
    public void run() {
        while (true) {
            oficina.imprimirA(nombre);
        }
    }
}
