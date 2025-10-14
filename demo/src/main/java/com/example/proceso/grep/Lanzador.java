package com.example.proceso.grep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Lanzador {

    // Importante: incluir el classpath (.) para que java encuentre la clase Escritor
    final static String[] COMANDOS = {"java", "com.example.proceso.grep.Escritor"};

    public static void main(String[] args) {
        try {
            Process p = Runtime.getRuntime().exec(COMANDOS);

            try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(p.getOutputStream()), true)) {

                pw.println("Me gusta PSP y java");
                pw.println("PSP se programa en java");
                pw.println("es un módulo de DAM");
                pw.println("y se programa de forma concurrente en PSP");
                pw.println("PSP es programación.");
                pw.close();

            }
            p.waitFor();
            try (BufferedReader salida = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String linea;
                while ((linea = salida.readLine()) != null) {
                    System.out.println(linea);
                }
            }

        } catch (IOException | InterruptedException e) {
            e.getMessage();
        }
    }
}
