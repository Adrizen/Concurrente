
package TP4SeccionCritica.Ejercicio6;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class test {

    
    public static void main(String[] args) {
        Imprimir imprimir = new Imprimir();
        Letra[] letras = new Letra[3];
        Thread[] hilos = new Thread[3];
        
        for (int i = 0; i < letras.length; i++) {
            char caracter = (char) (65+i);
            letras[i] = new Letra(imprimir, i+1);
            hilos[i] = new Thread(letras[i],String.valueOf(caracter));
        }
        
        for (int i = 0; i < hilos.length; i++) {
            hilos[i].start();
        }
    }
    /*
    Como abordar este tipo de problemas:
    Identificar los hilos:
	-Identificar las entidades que forman parte del problema.
	-Identificar las clases correspondientes.
        Letras
    Identificar recursos compartidos:
	-Es necesario asociar herramientas de sincronización para garantizar seguridad.
        Imprimir.
    Identificar los eventos de sincronización:
	-Para delimitar el orden en que deben ocurrir los eventos.
        Los hilos (letras) tienen que turnarse para saber cuando debe imprimir cada una.
    Notas: 
    Profe, cuando dice que no haya espera activa se refiere a que el hilo no se bloquee intentando obtener el lock?
    Silvia 25/09/2020:
    Se refiere a que el hilo no este todo el tiempo preguntando si le toca
     */
}
