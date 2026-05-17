package com.example.ejerciciosarchivos.Controlador;

import com.example.ejerciciosarchivos.Utilerias.ManejoFlujoBytes;
import com.example.ejerciciosarchivos.Vista.BytesVista;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class BytesControlador {
    private BytesVista vista;
    private Stage stagePrincipal;

    public BytesControlador(BytesVista vista, Stage stage) {
        this.vista = vista;
        this.stagePrincipal = stage;

        // Registrar eventos de clics con lambdas
        configurarEventosNavegacion();
        configurarEventosAcciones();
    }

    private void configurarEventosNavegacion() {
        // Controla los cambios de pantalla en la vista
        vista.item4.setOnAction(e -> vista.mostrarVistaClonador());
        vista.item5.setOnAction(e -> vista.mostrarVistaXOR());
        vista.item6.setOnAction(e -> vista.mostrarVistaIdentificador());
    }

    private void configurarEventosAcciones() {
        // Clonador de imagenes
        vista.btnClonar.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File origen = fileChooser.showOpenDialog(stagePrincipal);

            if (origen != null) {
                javafx.stage.DirectoryChooser directoryChooser = new javafx.stage.DirectoryChooser();
                directoryChooser.setTitle("Selecciona la carpeta de destino para la copia");
                File carpetaDestino = directoryChooser.showDialog(stagePrincipal);

                if (carpetaDestino != null) {

                    new Thread(() -> {
                        File destino = new File(carpetaDestino, "copia_" + origen.getName());
                        try (FileInputStream in = new FileInputStream(origen);
                             FileOutputStream out = new FileOutputStream(destino)) {

                            long tamanoTotal = origen.length();
                            long bytesAcumulados = 0;
                            byte[] buffer = new byte[1024];
                            int bytesLeidos;

                            while ((bytesLeidos = in.read(buffer)) != -1) {
                                out.write(buffer, 0, bytesLeidos);
                                bytesAcumulados += bytesLeidos;

                                double progreso = (double) bytesAcumulados / tamanoTotal;
                                Platform.runLater(() -> vista.progressBar.setProgress(progreso));
                            }

                            Platform.runLater(() -> new Alert(Alert.AlertType.INFORMATION, "Imagen clonada con exito!").show());

                        } catch (IOException ex) {
                            System.err.println("Error al clonar: " + ex.getMessage());
                        }
                    }).start();
                }
            }
        });

        // Mascara XOR
        vista.btnProcesarXOR.setOnAction(e -> {

            String entradaClave = vista.txtClave.getText().trim();

            if (entradaClave.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Por favor escribe una clave numerica.").show();
                return;
            }

            int clave;
            try {
                clave = Integer.parseInt(entradaClave);
            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "La clave debe ser un numero entero valido (no se aceptan letras).").show();
                return;
            }



            FileChooser fileChooser = new FileChooser();
            File origen = fileChooser.showOpenDialog(stagePrincipal);

            if (origen != null) {
                File destino = new File(origen.getParent(), "encriptado_" + origen.getName());

                // Llama a la utileria
                ManejoFlujoBytes utileria = new ManejoFlujoBytes(origen.getAbsolutePath());
                utileria.encriptarXOR(origen, destino, clave);

                new Alert(Alert.AlertType.INFORMATION, "Proceso XOR completado en: " + destino.getName()).show();
            }
        });

        // Identificador de tipos
        vista.btnIdentificar.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File archivo = fileChooser.showOpenDialog(stagePrincipal);

            if (archivo != null) {
                // Llama a la utileria
                ManejoFlujoBytes utileria = new ManejoFlujoBytes(archivo.getAbsolutePath());

                ArrayList<Integer> primerosBytes = utileria.leer8Bytes();
                String resultado = utileria.verificarFormato(primerosBytes);

                // Le devolvemos el resultado filtrado a la vista
                vista.lblResultado.setText("Tipo de archivo detectado: " + resultado);
            }
        });
    }
}
