package com.example.proceso.grep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Filtrador {

    public static void main(String[] args) {
        final String[] COMANDOS = {"grep", "PSP"};

        try {
            Process p = Runtime.getRuntime().exec(COMANDOS);

            try (BufferedReader lector = new BufferedReader(new InputStreamReader(System.in)); PrintWriter escritor = new PrintWriter(new OutputStreamWriter(p.getOutputStream()), true)) {

                String linea;
                while ((linea = lector.readLine()) != null) {
                    escritor.println(linea);
                }
            }

            p.getOutputStream().close();

            try (BufferedReader salida = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String linea;
                while ((linea = salida.readLine()) != null) {
                    System.out.println(linea);
                }
            }

            p.waitFor();

        } catch (IOException | InterruptedException e) {
            e.getMessage();
        }
    }
}
