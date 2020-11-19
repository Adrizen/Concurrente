package TP1Excepciones;

import java.util.Random;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Guillermo Andrés Pereyra.
 */
public class PruebaExcep {

    public static void menorEdad(int edad) {
        try {
            if (edad < 18)
                throw new IOException();
        } catch (IOException e) {
            System.err.println("La persona es menor a 18 años, usted ingresó: " + edad);
        }
    }

    public static void numeroRuleta(int numero) {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(37); //Genera un número aleatorio de 0 a 37-1 (osea 36).
        try {
            if (numero != numeroAleatorio)
                throw new Exception();
        } catch (Exception e) {
            System.err.println("El número elegido no coincide con el de la ruleta. Numero ruleta: " + numeroAleatorio + " numero elegido: " + numero);
        }
    }
    
    public static void pedirNumeros() {
        Scanner sc = new Scanner(System.in);
        int[] arreglo = new int[5];
        for (int i = 0; i < arreglo.length; i++) {
            System.out.println("Ingrese un numero para la casilla " + i);
            arreglo[i] = sc.nextInt();
        }
        try {
            for (int i = 0; i < 8; i++)
                System.out.println(arreglo[i]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println(e);
        }
    }
    

    public static void main(String[] args) {
        menorEdad(14);
        numeroRuleta(25);
        pedirNumeros();
    }

}
