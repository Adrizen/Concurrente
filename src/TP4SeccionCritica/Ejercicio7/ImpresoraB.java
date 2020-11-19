
package TP4SeccionCritica.Ejercicio7;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class ImpresoraB extends Impresora{
   
    
    public ImpresoraB(String nombreImpresora, Oficina oficina){
        super(nombreImpresora, oficina);
    }
    
    @Override
    public void run() {
        while (true) {
            oficina.imprimirB(nombre);
        }
    }
}
