
package TP4SeccionCritica.Ejercicio7;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Impresora implements Runnable{
    protected String nombre;
    protected Oficina oficina;
    
    public Impresora(String nombreImpresora, Oficina oficina){
        this.nombre = nombreImpresora;
        this.oficina = oficina;
    }
    
    public void run(){
        System.out.println("subclassResponsibility"); // ahre Smalltalk
    }
}
