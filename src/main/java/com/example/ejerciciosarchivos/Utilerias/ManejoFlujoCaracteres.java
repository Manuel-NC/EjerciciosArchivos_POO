package com.example.ejerciciosarchivos.Utilerias;

import java.io.*;
import java.util.ArrayList;

public class ManejoFlujoCaracteres {
    // Guardar archivo de texto
    public void guardarArchivo(File archivo, String contenido) throws IOException {
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write(contenido);
        }
    }

    // Cargar archivo de texto
    public String cargarArchivo(File archivo) throws IOException {
        StringBuilder contenido = new StringBuilder();
        try (FileReader reader = new FileReader(archivo)) {
            int caracter;
            while ((caracter = reader.read()) != -1) {
                contenido.append((char) caracter);
            }
        }
        return contenido.toString();
    }

    // Calcular Estadisticas
    public int[] calcularEstadisticas(File archivo) throws IOException {
        int lineas = 0;
        int palabras = 0;
        int caracteres = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas++;
                caracteres += linea.length(); // Cuenta los caracteres de la linea

                // Si la linea no esta vacia, contamos palabras separando por espacios
                if (!linea.trim().isEmpty()) {
                    String[] arregloPalabras = linea.trim().split("\\s+");
                    palabras += arregloPalabras.length;
                }
            }
        }
        // Retornamos los 3 conteos en un arreglo simple de enteros
        return new int[]{lineas, palabras, caracteres};
    }

    // Leer archivo CSV
    public ArrayList<String[]> leerCSV(File archivo) throws IOException {
        ArrayList<String[]> filas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // String.split(",") dentro de un bloque try-catch
                try {
                    String[] columnas = linea.split(",");
                    filas.add(columnas);
                } catch (Exception e) {
                    System.err.println("Error procesando línea CSV: " + e.getMessage());
                }
            }
        }
        return filas;
    }
}
