package com.example.ejerciciosarchivos.Utilerias;

import java.io.*;
import java.util.ArrayList;

public class ManejoFlujoBytes {

    File nombreArchivo;

    public ManejoFlujoBytes(String nombreArchivo) {
        this.nombreArchivo = new File(nombreArchivo);
    }

    // 4: Clonador de imagenes
    public void clonadorImagen(File origen, File destino) {
        try (FileInputStream in = new FileInputStream(origen);
             FileOutputStream out = new FileOutputStream(destino)) {

            // 1 KB
            byte[] buffer = new byte[1024];
            int bytesLeidos;

            // Lee byte por byte (valor de 0 a 255)
            while ((bytesLeidos = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesLeidos);
            }
            System.out.println("Archivo de bytes copiado con exito.");

        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
    }

    public int leerByte() {
        int unByte =-1;
        FileInputStream in = null;
        try {
            in = new FileInputStream(nombreArchivo);
            if((unByte = in.read())!=-1) {
                System.out.println(unByte);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return unByte;
    }


    public ArrayList<Integer> leer8Bytes() {
        int unByte = -1;
        int contador = 0;
        ArrayList<Integer> ochoBytes = new ArrayList<>();
        FileInputStream in = null;
        try {
            in = new FileInputStream(nombreArchivo);
            while((unByte = in.read())!=-1 && (contador < 8)) {
                ochoBytes.add(unByte);
                System.out.println(unByte);
                contador++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ochoBytes;
    }

    // 5: Encriptador XOR
    public void encriptarXOR(File origen, File destino, int clave) {

        try (FileInputStream in = new FileInputStream(origen);
             FileOutputStream out = new FileOutputStream(destino)) {

            int unByte;
            // Lee byte por byte (valor de 0 a 255)
            while ((unByte = in.read()) != -1) {
                // El operador ^ aplica la mascara XOR entre el byte y el numero clave
                int byteCifrado = unByte ^ clave;
                out.write(byteCifrado);
            }
            System.out.println("Cifrado/Descifrado XOR aplicado con exito.");

        } catch (IOException e) {
            // Captura las Exceptions
            System.err.println("Error de sistema (E/S): " + e.getMessage());
        }
    }

    // 6: Identificador de Tipos
    public String verificarFormato(ArrayList<Integer> ochoBytes) {
        String formato="";

        // Hacemos ArrayList de cada formato
        ArrayList<Integer> formatoPDF = new ArrayList<>();
        formatoPDF.add(Integer.parseInt("25", 16));
        formatoPDF.add(Integer.parseInt("50", 16));
        formatoPDF.add(Integer.parseInt("44", 16));
        formatoPDF.add(Integer.parseInt("46", 16));

        ArrayList<Integer> formatoJPEG = new ArrayList<>();
        formatoJPEG.add(Integer.parseInt("FF", 16));
        formatoJPEG.add(Integer.parseInt("D8", 16));
        formatoJPEG.add(Integer.parseInt("FF", 16));
        formatoJPEG.add(Integer.parseInt("E0", 16));

        ArrayList<Integer> formatoJPEG2 = new ArrayList<>();
        formatoJPEG2.add(Integer.parseInt("FF", 16));
        formatoJPEG2.add(Integer.parseInt("D8", 16));
        formatoJPEG2.add(Integer.parseInt("FF", 16));
        formatoJPEG2.add(Integer.parseInt("E1", 16));

        ArrayList<Integer> formatoPNG = new ArrayList<>();
        formatoPNG.add(Integer.parseInt("89", 16));
        formatoPNG.add(Integer.parseInt("50", 16));
        formatoPNG.add(Integer.parseInt("4E", 16));
        formatoPNG.add(Integer.parseInt("47", 16));

        ArrayList<Integer> formatoZIP = new ArrayList<>();
        formatoZIP.add(Integer.parseInt("50", 16));
        formatoZIP.add(Integer.parseInt("4B", 16));
        formatoZIP.add(Integer.parseInt("03", 16));
        formatoZIP.add(Integer.parseInt("04", 16));

        ArrayList<Integer> formatoMP4 = new ArrayList<>();
        formatoMP4.add(Integer.parseInt("66", 16));
        formatoMP4.add(Integer.parseInt("74", 16));
        formatoMP4.add(Integer.parseInt("79", 16));
        formatoMP4.add(Integer.parseInt("70", 16));

        ArrayList<Integer> formatoGIF = new ArrayList<>();
        formatoGIF.add(Integer.parseInt("47", 16));
        formatoGIF.add(Integer.parseInt("49", 16));
        formatoGIF.add(Integer.parseInt("46", 16));
        formatoGIF.add(Integer.parseInt("38", 16));

        // Hacemos ArrayList de los valores de ochoBytes para comparar
        ArrayList<Integer> valor = new ArrayList<>();
        for (int i = 0 ; i < 4 ; i++) {
            valor.add(ochoBytes.get(i));
        }

        // Comparamos con todos los formatos
        if (valor.equals(formatoPDF)) {
            formato = "PDF";
        } else if (valor.equals(formatoJPEG)) {
            formato = "JPEG";
        } else if (valor.equals(formatoJPEG2)) {
            formato = "JPEG";
        } else if (valor.equals(formatoPNG)) {
            formato = "PNG";
        } else if (valor.equals(formatoZIP)) {
            formato = "ZIP / DOCX / XLSX / JAR";
        } else if (valor.equals(formatoMP4)) {
            formato = "MP4";
        } else if (valor.equals(formatoGIF)) {
            formato = "GIF";
        } else {
            formato = "Desconocido";
        }


        return formato;
    }
}
