
package TP2ThreadRunnable;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Cajera {
    private String nombre;
    
    public Cajera(String nom){
        this.nombre = nom;
    }
    
    public void procesarCompra(Cliente cliente, long timeStamp){
        System.out.println("La cajera "+this.nombre+" COMIENZA A PROCESAR LA COMPRA DEL CLIENTE "+cliente.getNombre()+" EN EL TIEMPO: "+(System.currentTimeMillis() - timeStamp) / 1000 + " seg");
        
        for (int i = 0; i < cliente.getCarroCompra().length; i++) {
            this.esperarXSegundos(cliente.getCarroCompra()[i]);
            System.out.println("Procesado el producto "+ (i + 1) + "->Tiempo: "+(System.currentTimeMillis() - timeStamp) / 1000 + " seg");
        }
        System.out.println("La cajera "+this.nombre+" HA TERMINADO DE PROCESAR "+ cliente.getNombre() + " EN EL TIEMPO: "+(System.currentTimeMillis() - timeStamp) / 1000 + " seg");
    }
    
    public void esperarXSegundos(int segundosProducto){
        try{
            Thread.sleep(segundosProducto*1000);
        } catch(InterruptedException e) {  }
    }
    
}
