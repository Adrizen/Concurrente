
package TP4SeccionCritica.Ejercicio7;

import java.util.concurrent.ThreadLocalRandom;
import static Colores.Colores.*;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class Usuario implements Runnable{
    private String nombre;
    private Oficina oficina;
    
    public Usuario(String nombreEmpelado, Oficina oficina){
        this.nombre = nombreEmpelado;
        this.oficina = oficina;
    }

    public void run() {
        while (true) {
            int randomInt = ThreadLocalRandom.current().nextInt(0, 3);  // Elige un número aleatorio entre 0 y 2.
            char tipoImpresion = (char) (65 + (randomInt));        // Dado el número anterior, se elige entre las letras: 'A', 'B' o 'C'. De esta forma se crea un tipo de impresion aleatorio.
            System.out.println(GREEN_BOLD + nombre + " quiere imprimir un archivo tipo " + tipoImpresion + RESET);
            switch (tipoImpresion) {
                case 'A':
                    oficina.agregarImpresionA();
                    oficina.retirarCopiaA(nombre);
                    break;
                case 'B':
                    oficina.agregarImpresionB();
                    oficina.retirarCopiaB(nombre);
                    break;
                case 'C':
                    int intImpresoraAleatoria = ThreadLocalRandom.current().nextInt(0, 2);  // Elige un número aleatorio entre 0 y 1.
                    char charImpresoraAleatoria = (char) (65 + (intImpresoraAleatoria));    // Dado el número anterior, elige entre las letras 'A' o 'B'.
                    if (charImpresoraAleatoria == 'A') {
                        oficina.agregarImpresionA();
                        oficina.retirarCopiaA(nombre);
                    } else {
                        oficina.agregarImpresionB();
                        oficina.retirarCopiaB(nombre);
                    }
                    break;
            }
            laburar();
        }
    }
    
    public void laburar() {
        try {
            System.out.println(RED_BOLD + nombre + " está trabajando" + RESET);
            Thread.sleep(10000);
        } catch (InterruptedException e) { }
    }
    
    
    }
    

