package com.example.proceso.grep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Lanzador {

    final static String[] COMANDOS = {"grep", "PSP"};
    public static final String MSG_ERROR = "Se ha producido un error al ejecutar el comando";

    public static final String MSG = "Me gusta PSP y java\r\n"
            + "PSP se programa en java\r\n"
            + "es un módulo de DAM\r\n"
            + "y se programa de forma concurrente en PSP\r\n"
            + "PSP es programación.";

    public static void main(String[] args) {
        try {
            Process p = Runtime.getRuntime().exec(COMANDOS);
            StringBuilder sb = new StringBuilder();
            OutputStream out = p.getOutputStream();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
            pw.println(MSG);
            pw.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            int exitVal = p.waitFor();
            if (exitVal == 0) {
                System.out.println(sb);
                System.exit(0);
            } else {
                System.out.println(MSG_ERROR);
                System.exit(1);
            }

        } catch (IOException | InterruptedException e) {
            System.exit(34);
        }
    }
}
