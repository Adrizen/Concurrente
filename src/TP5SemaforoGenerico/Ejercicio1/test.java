
package TP5SemaforoGenerico.Ejercicio1;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    public static void main(String[] args) {
        Comedor comedor = new Comedor(4);
        // Creo, inicializo y arranco los hilos de Perros.
        for (int i = 0; i < 4; i++) {
            new Thread(new Animal("Perro"+i,"perro",comedor)).start();
        }
        // Creo, inicializo y arranco los hilos de Gatos.
        for (int i = 0; i < 4; i++) {
            new Thread(new Animal("Gato"+i,"gato",comedor)).start();
        }
    }
    
    /*
Como abordar este tipo de problemas:
Identificar los hilos:
	-Identificar las entidades que forman parte del problema.
	-Identificar las clases correspondientes.
    Animal (perros y gatos)
Identificar recursos compartidos:
	-Es necesario asociar herramientas de sincronización para garantizar seguridad.
    Comedor.
Identificar los eventos de sincronización:
	-Para delimitar el orden en que deben ocurrir los eventos.
    El entrar/salir de los animales.
    
    Observaciones:
    Solo puede haber animales de la misma especie en los comederos al mismo tiempo. Se debe diseñar un método que no permita a 
    la misma especie entrar repetidas veces y no deje que la otra pueda ingresar. (una cada uno x ejemplo)
    
*/

}
