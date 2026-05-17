package com.example.ejerciciosarchivos.Controlador;

import com.example.ejerciciosarchivos.Vista.CaracteresVista;
import com.example.ejerciciosarchivos.Utilerias.ManejoFlujoCaracteres;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CaracteresControlador {
    private CaracteresVista vista;
    private Stage stagePrincipal;
    private ManejoFlujoCaracteres modelo;

    public CaracteresControlador(CaracteresVista vista, Stage stage) {
        this.vista = vista;
        this.stagePrincipal = stage;
        this.modelo = new ManejoFlujoCaracteres(); // Instanciamos la utileria

        configurarNavegacion();
        configurarAcciones();
    }

    private void configurarNavegacion() {
        vista.item1.setOnAction(e -> vista.mostrarVistaEditor());
        vista.item2.setOnAction(e -> vista.mostrarVistaEstadisticas());
        vista.item3.setOnAction(e -> vista.mostrarVistaCSV());
    }

    private void configurarAcciones() {
        // Ejercicio 1 (cargar)
        vista.btnCargarNota.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            File archivo = chooser.showOpenDialog(stagePrincipal);
            if (archivo != null) {
                try {
                    String texto = modelo.cargarArchivo(archivo);
                    vista.txtAreaEditor.setText(texto);
                } catch (IOException ex) {
                    new Alert(Alert.AlertType.ERROR, "Error al cargar archivo:\n" + ex.getMessage()).show();
                }
            }
        });

        // Ejercicio 1 (guardar)
        vista.btnGuardarNota.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            File archivo = chooser.showSaveDialog(stagePrincipal);
            if (archivo != null) {
                try {
                    modelo.guardarArchivo(archivo, vista.txtAreaEditor.getText());
                    new Alert(Alert.AlertType.INFORMATION, "¡Archivo guardado con éxito!").show();
                } catch (IOException ex) {
                    new Alert(Alert.AlertType.ERROR, "Error al guardar archivo:\n" + ex.getMessage()).show();
                }
            }
        });

        // Ejercicio 2
        vista.btnCalcularEstadisticas.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            File archivo = chooser.showOpenDialog(stagePrincipal);
            if (archivo != null) {
                try {
                    int[] estadisticas = modelo.calcularEstadisticas(archivo);

                    // Construimos la cadena exacta que pediste
                    String tablaSimple = " Metrica    | Cantidad \n" +
                            "------------+----------\n" +
                            " líneas     | " + estadisticas[0] + "\n" +
                            " palabras   | " + estadisticas[1] + "\n" +
                            " caracteres | " + estadisticas[2] + "\n";

                    // Lo mostramos en el TextArea
                    vista.txtResultadoEstadisticas.setText(tablaSimple);

                } catch (IOException ex) {
                    new Alert(Alert.AlertType.ERROR, "Error al leer estadísticas:\n" + ex.getMessage()).show();
                }
            }
        });

        // Ejercicio 3
        vista.btnCargarCSV.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos CSV", "*.csv"));
            File archivo = chooser.showOpenDialog(stagePrincipal);

            if (archivo != null) {
                try {
                    ArrayList<String[]> lineasCSV = modelo.leerCSV(archivo);

                    vista.tablaCSV.getColumns().clear();
                    vista.tablaCSV.getItems().clear();

                    if (lineasCSV.isEmpty()) return;

                    // Tomamos la primera fila como cabecera para crear columnas dinamicas
                    String[] cabecera = lineasCSV.get(0);
                    for (int i = 0; i < cabecera.length; i++) {
                        final int index = i;
                        TableColumn<String[], String> columna = new TableColumn<>(cabecera[i]);
                        columna.setCellValueFactory(param -> {
                            String[] fila = param.getValue();
                            return new javafx.beans.property.SimpleStringProperty((index < fila.length) ? fila[index] : "");
                        });
                        vista.tablaCSV.getColumns().add(columna);
                    }

                    // Metemos el resto de las filas en la tabla
                    for (int i = 1; i < lineasCSV.size(); i++) {
                        vista.tablaCSV.getItems().add(lineasCSV.get(i));
                    }

                } catch (IOException ex) {
                    new Alert(Alert.AlertType.ERROR, "Error al abrir CSV:\n" + ex.getMessage()).show();
                }
            }
        });
    }
}
