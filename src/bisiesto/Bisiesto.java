/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bisiesto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author yai
 */
public class Bisiesto {

    // CONSTANTE
    public static String RUTA = "bisiesto.txt";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> bisiesto = new ArrayList<>();
        System.out.println("Tienes estos años bisiestos almacenados:");
        leerFichero();
        boolean salir = false;
        do {
            System.out.println("1. Añadir años bisiesto.");
            System.out.println("2. Vaciar contenido fichero.");
            System.out.println("3. Mostrar contenido fichero.");
            System.out.println("");
            System.out.println("4. Salir.");
            System.out.print("Elige una opción: ");
            String opcion = sc.next();
            switch (opcion) {
                case "1":
                    System.out.println("¿Cuántos años bisiestos quieres añadir?");
                    int num = sc.nextInt();
                    boolean limite = false;
                    int cont = 0;
                    for (int i = leerUltimo() + 1; i >= 0 && !limite; i++) {
                        if (NumBisiesto(i)) {
                            cont = cont + 1;
                            bisiesto.add(i);
                        }
                        if (cont == num) {
                            limite = true;
                        }
                    }
                    escribirFichero(bisiesto);
                    break;
                case "2":
                    vaciarFichero();
                    break;
                case "3":
                    leerFichero();
                    break;
                case "4":
                    salir = true;
                    System.out.println("Nos vemos pronto :)");
                    break;
                default:
                    System.out.println("Solo números del 1 al 7.");
            }
        } while (!salir);
//        for (int i = 0; i <= primos.size()-1; i++) {
//            System.out.print(primos.get(i)+" ");
//        }
    }

    public static boolean NumBisiesto(int num) {
        boolean bisiesto = false;
        if ((num % 4 == 0 && num % 100 != 0) || (num % 400 == 0)) {
            bisiesto = true;
        }
        return bisiesto;
    }

    public static void leerFichero() {
        // Creamos un objeto fichero
        // File fichero = new File("mascotas.info");
        File fichero = new File(RUTA);

        FileReader f = null;
        BufferedReader s = null;
        String numero = "";
        try {
            if (!fichero.exists()) {
                fichero.createNewFile();
            }
            f = new FileReader(fichero);
            s = new BufferedReader(f);
            numero = s.readLine();
            if (numero != null) {
                System.out.println(numero);
            } else {
                numero = "1900";
                System.out.println("Ninguno.");
            }

        } catch (FileNotFoundException ex) {
            try {
                fichero.createNewFile();
                System.out.println("Mensaje de la excepción: " + ex.getMessage());
            } catch (IOException ex1) {
                System.out.println("Mensaje de la excepción: " + ex1.getMessage());
            }
        } catch (IOException ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
        } finally {
            // Cerramos el fichero se haya escrito la info o no. 
            try {
                s.close();
                f.close();
            } catch (IOException ex2) {
                System.out.println("Mensaje de la excepción: " + ex2.getMessage());
            }
        }
    }
    
    public static void vaciarFichero() {
        // Creamos un objeto fichero
        //File fichero = new File("mascotas.info");
        File fichero = new File(RUTA);
        FileWriter f = null;
        BufferedWriter s = null;
        try {
            f = new FileWriter(fichero);
            s = new BufferedWriter(f);
            s.write("");
        } catch (FileNotFoundException ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
        } finally {
            // Cerramos el fichero se haya escrito la info o no. 
            try {
                s.close();
                f.close();
            } catch (IOException ex2) {
                System.out.println("Mensaje de la excepción: " + ex2.getMessage());
            }
        }
    }

    public static void escribirFichero(ArrayList<Integer> bisiesto) {
        // Creamos un objeto fichero
        //File fichero = new File("mascotas.info");
        File fichero = new File(RUTA);
        FileWriter f = null;
        BufferedWriter s = null;
        try {
            f = new FileWriter(fichero, true);
            s = new BufferedWriter(f);
            int tamaño = bisiesto.size();
            for (int i = 0; i < tamaño; i++) {
                s.write(bisiesto.get(i) + " ");
            }

        } catch (IOException ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
        } finally {
            // Cerramos el fichero se haya escrito la info o no. 
            try {
                s.close();
                f.close();
            } catch (IOException ex2) {
                System.out.println("Mensaje de la excepción: " + ex2.getMessage());
            }
        }
    }

    public static int leerUltimo() {
        File fichero = new File(RUTA);
        FileReader f = null;
        BufferedReader s = null;
        String numero = "";
        int numLast = 0;
        try {
            if (!fichero.exists()) {
                fichero.createNewFile();
            }
            f = new FileReader(fichero);
            s = new BufferedReader(f);
            numero = s.readLine();
            if (numero != null) {
//                System.out.println(numero);
            } else {
                // La historia del día extra del año bisiesto comienza en el año 49 a.C.
                numero = "1900";
                System.out.println("Ninguno.");
            }
            String[] cadena = numero.split(" ");
            String ultimoNumero = cadena[cadena.length - 1];
            numLast = Integer.parseInt(ultimoNumero);
//            System.out.print(numLast);

        } catch (FileNotFoundException ex) {
            try {
                fichero.createNewFile();
                System.out.println("Mensaje de la excepción: " + ex.getMessage());
            } catch (IOException ex1) {
                System.out.println("Mensaje de la excepción: " + ex1.getMessage());
            }
        } catch (IOException ex) {
            System.out.println("Mensaje de la excepción: " + ex.getMessage());
        } finally {
            // Cerramos el fichero se haya escrito la info o no. 
            try {
                s.close();
                f.close();
            } catch (IOException ex2) {
                System.out.println("Mensaje de la excepción: " + ex2.getMessage());
            }
        }
        return numLast;
    }

}
